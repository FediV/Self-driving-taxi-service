package it.unimi.di.sdp.Threads;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import it.unimi.di.sdp.GRPC.ExitTaxiClient;
import it.unimi.di.sdp.Taxi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import static it.unimi.di.sdp.Helper.RestCalls.deleteRequest;

public class StdInput extends Thread{
    private final Taxi taxi;

    public StdInput(Taxi taxi) {
        this.taxi = taxi;
    }

    @Override
    public void run() {

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("\n[Thread SI] Hit QUIT to stop or RECHARGE to recharge");

            while(!taxi.getStopCondition()) {
                String message = br.readLine();

                if ( message.equalsIgnoreCase("recharge") ) {

                    taxi.setNeedToRecharge(true);
                    taxi.getRideQueue().recharge();

                }

                if ( message.equalsIgnoreCase("quit")) {
                    safeExit(taxi);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getCause() + " : " + e.getMessage());
        }

    }

    protected static void safeExit(Taxi taxi) {
        Client client = Client.create();
        String serverAddress = "http://localhost:1337";
        ClientResponse clientResponse;
        String deletePath = "/taxi/delete/" + taxi.getId();

        taxi.setStopCondition(true);
        taxi.getRideQueue().stop();

        for (Taxi t : taxi.getLocalSmartCity().getTaxiList()) {
            if (t.getId() != taxi.getId()) {
                ExitTaxiClient exitTaxiClient = new ExitTaxiClient(t, taxi);
                exitTaxiClient.start();
            }
        }

        clientResponse = deleteRequest(client, serverAddress + deletePath);

        if (Objects.requireNonNull(clientResponse).getStatus() == 200) {
            System.out.println("\n[Thread SI] Taxi " + taxi.getId() + " removed from smart city");
        }

        System.out.println("\n[Thread SI] Taxi is shutting down! Number of rides served ["+
                taxi.getRidesServed().size() + "]");

    }
}
