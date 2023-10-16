package it.unimi.di.sdp.Helper;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import it.unimi.di.sdp.Taxi;

public class RestCalls {

    public static ClientResponse deleteRequest(Client client, String url){
        WebResource webResource = client.resource(url);
        try {
            return webResource.type("application/json").delete(ClientResponse.class);
        } catch (ClientHandlerException e) {
            System.out.println("Server non disponibile");
            return null;
        }
    }

    public static void postRequestStatistic(Client client, String url, Statistic s){
        WebResource webResource = client.resource(url);
        String input = new Gson().toJson(s);

        try {
            webResource.type("application/json").post(ClientResponse.class, input);
        } catch (ClientHandlerException e) {
            System.out.println("Server non disponibile");
        }
    }

    public static ClientResponse postRequestTaxi(Client client, String url, Taxi t){
        WebResource webResource = client.resource(url);
        String input = new Gson().toJson(t);

        try {
            return webResource.type("application/json").post(ClientResponse.class, input);
        } catch (ClientHandlerException e) {
            System.out.println("Server non disponibile");
            return null;
        }
    }

    public static ClientResponse getRequest(Client client, String url){
        WebResource webResource = client.resource(url);
        try {
            return webResource.type("application/json").get(ClientResponse.class);
        } catch (ClientHandlerException e) {
            System.out.println("Server non disponibile");
            return null;
        }
    }
}
