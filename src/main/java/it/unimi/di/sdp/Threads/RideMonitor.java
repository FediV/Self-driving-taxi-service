package it.unimi.di.sdp.Threads;

import com.google.gson.Gson;
import it.unimi.di.sdp.DataStucture.ActiveRidesQueue;
import it.unimi.di.sdp.Helper.Ride;
import it.unimi.di.sdp.DataStucture.RidesQueue;
import it.unimi.di.sdp.Taxi;
import org.eclipse.paho.client.mqttv3.*;

public class RideMonitor extends Thread{
    private final Taxi taxi;
    private final int districtTaxi;
    private final RidesQueue rideQueue;
    private ActiveRidesQueue activeRidesQueue;
    private final Object lock = new Object();

    public RideMonitor(Taxi taxi) {
        this.taxi = taxi;
        districtTaxi = taxi.getPosition().getDistrict();
        rideQueue = taxi.getRideQueue();
    }

    @Override
    public void run() {
        MqttClient client;
        String broker = "tcp://localhost:1883";
        String clientId = MqttClient.generateClientId();
        String newRideTopic = "seta/smartcity/rides/district"+districtTaxi;
        int qos = 2;

        try {

            client = new MqttClient(broker, clientId);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            client.connect(connOpts);

            client.setCallback(new MqttCallback() {

                public void messageArrived(String topic, MqttMessage message) {

                    String receivedMessage = new String(message.getPayload());

                    if(topic.equals("seta/smartcity/returnActiveRides/"+taxi.getId())) {
                        activeRidesQueue = new Gson().fromJson(receivedMessage, ActiveRidesQueue.class);

                        synchronized (lock) {
                            lock.notifyAll();
                        }

                    } else {
                        Ride ride = new Gson().fromJson(receivedMessage,Ride.class);
                        if (taxi.getRidesServed().stream().noneMatch(r -> r.getId() == ride.getId())) {
                            rideQueue.add(ride);
                        }
                    }
                }

                public void connectionLost(Throwable cause) {
                    System.out.println("\n[Thread RIDE_MONITOR] taxi " + taxi.getId() + " Connectionlost! cause:" + cause.getMessage()+ "-  Thread PID: " + Thread.currentThread().getId());
                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Not used here
                }

            });

            client.subscribe("seta/smartcity/returnActiveRides/"+taxi.getId(),qos);

            String payload = String.format("%d %d",districtTaxi,taxi.getId());
            MqttMessage message = new MqttMessage(payload.getBytes());
            message.setQos(1);

            client.publish("seta/smartcity/activeRides", message);

            synchronized (lock) {
                lock.wait();
            }

            for(Ride r : activeRidesQueue.getRides()) {
                if (!rideQueue.contains(r.getId())) rideQueue.add(r);
            }

            client.subscribe(newRideTopic,qos);
            System.out.println("\n[Thread RIDE_MONITOR] taxi "  + taxi.getId() + " subscribed to topics : " + newRideTopic);

            StartElection startElection = new StartElection(taxi,rideQueue,client);
            startElection.start();

            synchronized (taxi) {
                taxi.wait();
            }

            client.unsubscribe("seta/smartcity/rides/district"+taxi.getPosition().getDistrict());
            System.out.println("\n[Thread RIDE_MONITOR] taxi "  + taxi.getId() + " unsubscribed to topics : " + "seta/smartcity/rides/district" + taxi.getPosition().getDistrict());

            client.disconnect();

        } catch (MqttException | InterruptedException me ) {
            System.out.println(me.getCause() + " : " + me.getMessage());
            StdInput.safeExit(taxi);
        }

    }
}
