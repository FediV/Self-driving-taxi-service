package it.unimi.di.sdp.Threads;

import com.google.gson.Gson;
import it.unimi.di.sdp.Helper.Ride;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class RemoveRideSETA extends Thread{
    private final Ride ride;

    public RemoveRideSETA(Ride ride) {
        this.ride = ride;
    }

    public void run() {
        String broker ="tcp://localhost:1883";
        String clientId = MqttClient.generateClientId();
        MqttClient client;
        int pubQos = 2;

        try {

            client = new MqttClient(broker, clientId);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            client.connect(connOpts);

            String payload = new Gson().toJson(ride);

            MqttMessage message = new MqttMessage(payload.getBytes());

            message.setQos(pubQos);

            client.publish("seta/smartcity/finishRide", message);

            client.disconnect();

        } catch (MqttException me) {
            System.out.println("[REMOVE RIDE] "+me.getCause()+" : "+me.getMessage());
        }
    }
}
