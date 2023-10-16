package it.unimi.di.sdp.Threads;

import org.eclipse.paho.client.mqttv3.*;


public class ExitTaxi extends Thread {
    private final int districtTaxi;

    public ExitTaxi(int district) {
        this.districtTaxi = district;
    }

    @Override
    public void run() {
        MqttClient client;
        String broker = "tcp://localhost:1883";
        String clientId = MqttClient.generateClientId();

        try {

            client = new MqttClient(broker, clientId);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            client.connect(connOpts);

            String payload = String.format("%d",districtTaxi);
            MqttMessage message = new MqttMessage(payload.getBytes());
            message.setQos(1);

            client.publish("seta/smartcity/exitActiveRides", message);

            client.disconnect();

        } catch (MqttException me ) {
            System.out.println("[EXIT TAXI] "+me.getCause()+" : "+me.getMessage());
        }
    }
}
