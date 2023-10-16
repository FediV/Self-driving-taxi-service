package it.unimi.di.sdp.Threads;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import it.unimi.di.sdp.Helper.Position;
import it.unimi.di.sdp.SmartCity;
import it.unimi.di.sdp.Taxi;

import java.util.List;
import java.util.Objects;

import static it.unimi.di.sdp.Helper.RestCalls.getRequest;
import static it.unimi.di.sdp.Helper.RestCalls.postRequestTaxi;


public class CommunicationWithServer extends Thread{
    private final Taxi taxi;
    private final int id;

    public CommunicationWithServer(Taxi taxi) {
        this.taxi = taxi;
        this.id = this.taxi.getId();
    }

    @Override
    public void run() {

        try {

            ClientConfig clientConfig = new DefaultClientConfig();
            clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
            Client client = Client.create(clientConfig);

            String serverAddress = "http://localhost:1337";
            ClientResponse clientResponse;

            clientResponse = postRequestTaxi(client,serverAddress+"/taxi/add", taxi);

            if (Objects.requireNonNull(clientResponse).getStatus() != 200) {
                throw new IllegalArgumentException("Failed to add taxi : id is already present");
            } else {
                Position position = Objects.requireNonNull(clientResponse).getEntity(Position.class);
                taxi.setPosition(position);

                System.out.println("\n[Thread CVS] taxi "+id+" was assigned to district: " + position.getDistrict()
                        + " (" + position.getX() + "," + position.getY() + ")");

                System.out.println("\n[Thread CVS] Taxi " + taxi.getId() + " is running!");

                clientResponse = getRequest(client,serverAddress+"/taxi");

                SmartCity smartCity = Objects.requireNonNull(clientResponse).getEntity(SmartCity.class);
                List<Taxi> taxiList = smartCity.getTaxiList();
                taxi.getLocalSmartCity().setTaxiList(taxiList);
            }

        } catch (IllegalArgumentException e) {
            System.out.println("\n" + e.getMessage());
        }

    }
}
