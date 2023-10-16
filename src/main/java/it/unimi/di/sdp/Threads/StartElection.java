package it.unimi.di.sdp.Threads;

import it.unimi.di.sdp.GRPC.RideElectionClient;
import it.unimi.di.sdp.Helper.Ride;
import it.unimi.di.sdp.DataStucture.RidesQueue;
import it.unimi.di.sdp.SmartCity;
import it.unimi.di.sdp.Taxi;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.ArrayList;
import java.util.List;

public class StartElection extends Thread{
    private final Taxi taxi;
    private final RidesQueue rideQueue;
    private final MqttClient client;
    private final Object rideLock;
    private final List<Thread> threadList;

    public StartElection(Taxi taxi, RidesQueue rideQueue, MqttClient client) {
        this.taxi = taxi;
        this.rideQueue = rideQueue;
        this.client = client;
        this.rideLock = taxi.getRidesResponses().getLock();
        this.threadList = new ArrayList<>();
    }

    @Override
    public void run() {

        Ride ride;

        try {

            do {
                ride = rideQueue.take();
            } while (ride.getId()==0);

            boolean remove = true;
            boolean win = false;

            while (!taxi.getStopCondition()) {

                remove = false;
                win = false;

                int start = taxi.getPosition().getDistrict();
                int finish = ride.getFinishPosition().getDistrict();

                Integer idRide = ride.getId();

                taxi.getRidesResponses().put(idRide);
                SmartCity localSmartCity = taxi.getLocalSmartCity();
                int numberOfTaxi = localSmartCity.getTaxiList().size();

                System.out.println("\n[Thread ELECTION] taxi " + taxi.getId() + " starts election for ride " + ride.getId());

                for (Taxi t : localSmartCity.getTaxiList()) {
                    if (t.getId() != taxi.getId()) {
                        RideElectionClient electionClient = new RideElectionClient(t, taxi, ride);
                        electionClient.start();
                    }
                }

                while (taxi.getRidesResponses().getResponses(idRide).size() != (numberOfTaxi - 1)) {

                    synchronized (rideLock) {
                        rideLock.wait();
                    }

                    if (numberOfTaxi == localSmartCity.getTaxiList().size() - 1) {
                        RideElectionClient electionClient = new RideElectionClient(localSmartCity.getTaxiList().get(numberOfTaxi), taxi, ride);
                        electionClient.start();
                        numberOfTaxi += 1;
                    }

                }

                int numberOfOK = (int) taxi.getRidesResponses().getResponses(idRide).stream().filter(s -> s.equals("ok")).count();

                if (numberOfOK == (numberOfTaxi - 1)) {
                    win = true;

                    RemoveRideSETA removeRideSETA = new RemoveRideSETA(ride);
                    removeRideSETA.start();

                    taxi.addRideServed(ride);
                    taxi.setAvailable(false);

                    StartNewRide startNewRide = new StartNewRide(ride, taxi);
                    startNewRide.start();

                    startNewRide.join();

                    System.out.println("\n[Thread START ELECTION] taxi " + taxi.getId() + " finish serving ride " + ride.getId());

                    if (taxi.getBatteryLevel() < 30) taxi.setNeedToRecharge(true);

                    if (taxi.getNeedToRecharge() && !taxi.getStopCondition()) {
                        System.out.println("\n[Thread START ELECTION] taxi needs to recharge in district " + finish);

                        Recharge recharge = new Recharge(taxi);
                        recharge.start();

                        recharge.join();
                    }

                    if (finish != start) {
                        client.unsubscribe("seta/smartcity/rides/district" + start);
                        client.subscribe("seta/smartcity/rides/district" + finish);

                        System.out.println("\n[Thread START ELECTION] taxi " + taxi.getId() + " is now in district : " + finish);
                    }

                    if (!taxi.getStopCondition()) {
                        AvailabilitySETA available = new AvailabilitySETA(true, finish);
                        available.start();

                        remove = true;
                    }

                }

                rideQueue.remove(ride);

                do {
                    ride = rideQueue.take();

                    if (taxi.getNeedToRecharge()) {

                        System.out.println("\n[Thread START ELECTION] taxi needs to recharge in district " + taxi.getPosition().getDistrict());

                        Recharge recharge = new Recharge(taxi);
                        recharge.start();

                        recharge.join();

                    }
                } while(ride.getId()==0 && !taxi.getStopCondition());

            }

            synchronized (taxi) {
                taxi.notifyAll();
            }

            if (remove || !win) {
                AvailabilitySETA noAvailable = new AvailabilitySETA(false, taxi.getPosition().getDistrict());
                noAvailable.start();

                if (remove) {
                    ExitTaxi exitTaxi = new ExitTaxi(taxi.getPosition().getDistrict());
                    exitTaxi.start();
                }
            }

        } catch (MqttException | InterruptedException e) {
            System.out.println("\n" + e.getCause() + " : " + e.getMessage());
        }
    }
}
