package it.unimi.di.sdp;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import it.unimi.di.sdp.DataStucture.ActiveRidesQueue;
import it.unimi.di.sdp.DataStucture.AvailableQueue;
import it.unimi.di.sdp.DataStucture.PersistentRidesQueue;
import it.unimi.di.sdp.Helper.Position;
import it.unimi.di.sdp.Helper.Ride;
import it.unimi.di.sdp.Threads.PersistentRides;
import it.unimi.di.sdp.Threads.StopSETA;
import org.eclipse.paho.client.mqttv3.*;

import java.util.*;

import static it.unimi.di.sdp.Helper.RestCalls.getRequest;

public class SETA {
    public static final PersistentRidesQueue persistentQueues = new PersistentRidesQueue();
    private static final AvailableQueue availableTaxi = new AvailableQueue();

    public static boolean stop = false;

    public static void main(String[] args) {

        String broker ="tcp://localhost:1883";
        String clientId = MqttClient.generateClientId();
        MqttClient client;

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client clientRest = Client.create(clientConfig);

        String getPath = "/taxi";
        String serverAddress = "http://localhost:1337";

        ClientResponse clientResponse = getRequest(clientRest,serverAddress+getPath);
        SmartCity smartCity = Objects.requireNonNull(clientResponse).getEntity(SmartCity.class);

        for (Taxi t : smartCity.getTaxiList()) {
            int idDistrict = t.getPosition().getDistrict();
            availableTaxi.add(idDistrict);
        }

        createThreads();

        try {

            client = new MqttClient(broker, clientId);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            client.connect(connOpts);

            client.setCallback(new MqttCallback() {

                public void messageArrived(String topic, MqttMessage message) {

                    String receivedMessage = new String(message.getPayload());

                    if (topic.equals("seta/smartcity/availableTaxi")) {
                        boolean available = receivedMessage.split(" ")[0].equals("available");
                        int taxiDistrict = Integer.parseInt(receivedMessage.split(" ")[1]);

                        if (available) {
                            availableTaxi.add(taxiDistrict);
                            persistentQueues.get(taxiDistrict-1).wakeUp();
                        } else {
                            availableTaxi.remove(taxiDistrict);
                        }
                    }

                    if(topic.equals("seta/smartcity/finishRide")) {
                        Ride ride = new Gson().fromJson(receivedMessage, Ride.class);
                        persistentQueues.get(ride.getStartPosition().getDistrict()-1).removeActiveRide(ride.getId());
                    }

                    if(topic.equals("seta/smartcity/activeRides")) {
                        int district = Integer.parseInt(receivedMessage.split(" ")[0]);
                        int idTaxi = Integer.parseInt(receivedMessage.split(" ")[1]);

                        ActiveRidesQueue activeRidesQueue = new ActiveRidesQueue(persistentQueues.get(district-1).getActiveRides());

                        try {
                            String payload = new Gson().toJson(activeRidesQueue);
                            message = new MqttMessage(payload.getBytes());
                            message.setQos(1);
                            client.publish("seta/smartcity/returnActiveRides/"+idTaxi,message);
                        } catch (MqttException me) {
                            me.printStackTrace();
                        }

                    }

                    if(topic.equals("seta/smartcity/exitActiveRides")) {
                        int district = Integer.parseInt(receivedMessage);
                        List<Ride> rides = persistentQueues.get(district-1).getActiveRides();

                        for (Ride r : rides) {
                            availableTaxi.add(district);
                            persistentQueues.get(district-1).addRideOnTop(r);
                        }
                    }

                }

                public void connectionLost(Throwable cause) {
                    System.out.println("[SETA] Connectionlost! cause:" + cause.getMessage() + "-  Thread PID: " + Thread.currentThread().getId());
                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                }

            });


            List<String> topics = new ArrayList<String>() {{
                add("seta/smartcity/availableTaxi");
                add("seta/smartcity/finishRide");
                add("seta/smartcity/activeRides");
                add("seta/smartcity/exitActiveRides");
            }};
            for (String s : topics) { client.subscribe(s,2); }

            StopSETA stopSETA = new StopSETA();
            stopSETA.start();

            createRides();

            client.disconnect();

            System.out.println("\n[SETA] stopped");

        } catch (MqttException | InterruptedException e) {
            System.out.println(e.getCause() + " : " + e.getMessage());
        }


    }

    private static void createRides() throws InterruptedException {
        int x_start, y_start, start_district, x_finish, y_finish, id = 0;
        Ride ride;
        Position start_pos, finish_pos;

        while (!stop) {

            for (int i = 0; i < 2; i++) {
                x_start = (int) (0 + Math.random() * 9);
                y_start = (int) (0 + Math.random() * 9);
                start_pos = new Position(x_start,y_start);
                start_district = start_pos.getDistrict();

                do {
                    x_finish = (int) (0 + Math.random() * 9);
                    y_finish = (int) (0 + Math.random() * 9);
                } while (x_finish!=x_start && y_finish!=y_start);

                finish_pos = new Position(x_finish,y_finish);

                id = id + 1;

                ride = new Ride(id,start_pos,finish_pos);

                String payload = new Gson().toJson(ride);
                MqttMessage message = new MqttMessage(payload.getBytes());

                message.setQos(2);

                persistentQueues.get(start_district-1).addRide(ride);
                persistentQueues.get(start_district-1).printRideList();
                persistentQueues.get(start_district-1).wakeUp();
            }

            Thread.sleep(5000);

        }
    }

    private static void createThreads() {
        for (int i=1; i<5; i++) {
            persistentQueues.addThread(new PersistentRides(i,availableTaxi));
        }
        persistentQueues.start();
    }
}
