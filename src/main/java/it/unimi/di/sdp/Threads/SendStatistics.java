package it.unimi.di.sdp.Threads;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import it.unimi.di.sdp.Helper.Statistic;
import it.unimi.di.sdp.Taxi;

import java.util.ArrayList;
import java.util.List;

import static it.unimi.di.sdp.Helper.RestCalls.postRequestStatistic;

public class SendStatistics extends Thread{
    private final Taxi taxi;
    private static final List<Double> averages = new ArrayList<>();
    private final Object lock = new Object();

    public SendStatistics(Taxi taxi) {
        this.taxi = taxi;
    }

    @Override
    public void run() {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);

        String serverAddress = "http://localhost:1337";
        String postPath = "/taxi/statistics/add";

        PollutionSensor sensor = new PollutionSensor(taxi.getBuffer(), averages, lock, taxi.getStopCondition());
        sensor.start();

        Statistic statistic;
        int oldRidesServed = 0, latestIndex = 0;
        double oldKilometers = 0;

        while(!taxi.getStopCondition()) {

            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                System.out.println(e.getCause() + " : " + e.getMessage());
            }

            double newKilometers = taxi.getKilometers();
            int newRidesServed = taxi.getRidesServed().size();

            double timestamp = System.currentTimeMillis();

            synchronized (lock) {
                statistic = new Statistic(newKilometers - oldKilometers,
                        newRidesServed - oldRidesServed,
                        averages.subList(latestIndex, averages.size() - 1 ),
                        taxi.getId(),
                        timestamp,
                        taxi.getBatteryLevel());

                latestIndex = averages.size() != 0 ? averages.size() - 1  : 0;

                postRequestStatistic(client, serverAddress + postPath, statistic);
            }

            oldKilometers = newKilometers;
            oldRidesServed = newRidesServed;

            System.out.println("\n[Thread STATISTICS] taxi "+taxi.getId()+" has sent its statistics at ts "+timestamp);
        }

        sensor.stopSensor();

    }

}
