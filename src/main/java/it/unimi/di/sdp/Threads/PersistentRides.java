package it.unimi.di.sdp.Threads;

import com.google.gson.Gson;
import it.unimi.di.sdp.DataStucture.ActiveRidesQueue;
import it.unimi.di.sdp.DataStucture.AvailableQueue;
import it.unimi.di.sdp.Helper.Ride;
import org.eclipse.paho.client.mqttv3.*;

import java.util.ArrayList;
import java.util.List;

public class PersistentRides extends Thread{
    private final int district;
    private final ActiveRidesQueue activeRides = new ActiveRidesQueue();
    private final AvailableQueue availableTaxi;
    private final List<Ride> rideList = new ArrayList<>();
    private final Object lock = new Object();
    private final Object activeLock = new Object();
    private final Object ridesLock = new Object();

    private boolean stop = false;

    public PersistentRides(int district, AvailableQueue availableTaxi) {
        this.district = district;
        this.availableTaxi = availableTaxi;
    }

    public List<Ride> getActiveRides() {
        synchronized (activeLock) {
            return activeRides.getRides();
        }
    }

    public void removeActiveRide(int rideId) {
        synchronized (activeLock) {
            activeRides.remove(rideId);
        }
    }

    public void addActiveRide(Ride ride) {
        synchronized (activeLock) {
            activeRides.add(ride);
        }
    }

    public void addRide(Ride ride) {
        synchronized (ridesLock){
            rideList.add(ride);
        }
    }

    public void addRideOnTop(Ride ride) {
        synchronized (ridesLock) {
            rideList.add(0,ride);
        }
    }

    public int getNumberOfRides() {
        synchronized (ridesLock) {
            return rideList.size();
        }
    }

    public Ride addAndRemove() {
        synchronized (ridesLock) {
            Ride ride = rideList.get(0);
            rideList.remove(0);
            return ride;
        }
    }

    public void printRideList() {
        synchronized (ridesLock) {
            System.out.println("\nCreate new ride at district "+district);
            System.out.print("There are "+ rideList.size()+" ride in district "+district+" [ ");
            rideList.forEach(r->System.out.print(r.getId()+" "));
            System.out.println("]");
        }
    }

    public void wakeUp() {
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public void finish() {
        synchronized (lock) {
            stop = true;
            lock.notifyAll();
        }
    }

    @Override
    public void run() {
        String broker = "tcp://localhost:1883";
        String clientId = MqttClient.generateClientId();
        MqttClient client;

        try {
            client = new MqttClient(broker, clientId);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            client.connect(connOpts);

            while (!stop) {
                sendRideMessage(client);
            }

            System.out.println("\nNumber of rides left in district "+district+" : "+getNumberOfRides());

            client.disconnect();

        } catch (MqttException me) {
            System.out.println("\n" + me.getCause() + " : " + me.getMessage());
        }


    }

    private void sendRideMessage(MqttClient client) {

        try {

            synchronized (lock) {
                while ((availableTaxi.numberOfTaxi(district) <= 0 || getNumberOfRides() == 0) && !stop) {
                    lock.wait();
                }
            }

            if(!stop) {

                Ride ride = addAndRemove();

                addActiveRide(ride);

                AvailabilitySETA noAvailable = new AvailabilitySETA(false,district);
                noAvailable.start();

                noAvailable.join();

                String payload = new Gson().toJson(ride);
                MqttMessage message = new MqttMessage(payload.getBytes());
                message.setQos(2);

                client.publish(String.format("seta/smartcity/rides/district%d",district), message);

                System.out.println("\n[SETA] publish to "+String.format("seta/smartcity/rides/district%d",district)+" ride "+ride.getId());
            }

        } catch (MqttException | InterruptedException e) {
            System.out.println("\n" + e.getCause() + " : " + e.getMessage());
        }

    }

}
