package it.unimi.di.sdp.Threads;

import org.eclipse.paho.client.mqttv3.*;

public class AvailabilitySETA extends Thread{
    private final boolean isAvailable;
    private final int district;

    public AvailabilitySETA(boolean isAvailable, int district) {
        this.isAvailable = isAvailable;
        this.district = district;
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

            String payload;

            if (isAvailable) payload = String.format("%s %d","available",district);
            else payload = String.format("%s %d","notAvailable",district);

            MqttMessage message = new MqttMessage(payload.getBytes());

            message.setQos(pubQos);

            client.publish("seta/smartcity/availableTaxi", message);

            client.disconnect();

        } catch (MqttException me) {
            System.out.println("[AVAILABILITY thread] : "+me.getMessage());
        }
    }

}
