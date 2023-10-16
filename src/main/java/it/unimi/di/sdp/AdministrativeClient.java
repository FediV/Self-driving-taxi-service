package it.unimi.di.sdp;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import it.unimi.di.sdp.Helper.Statistic;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

import static it.unimi.di.sdp.Helper.RestCalls.getRequest;

public class AdministrativeClient {

    public static void main(String[] args) {

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);

        String serverAddress = "http://localhost:1337";
        ClientResponse clientResponse;

        int choice = 0;
        String path;
        Scanner stdInput = new Scanner(System.in);

        System.out.println("Welcome");

        do {

            System.out.println("\nPlease choose an option: ");
            System.out.println("1) Get list of taxi in the smart city ");
            System.out.println("2) Get last statistics of a taxi ");
            System.out.println("3) Get statistics of all taxi in an interval ");
            System.out.println("4) Quit \n");
            

            while (stdInput.hasNext()){
                if (stdInput.hasNextInt()){
                    choice = stdInput.nextInt();
                    break;
                } else {
                    stdInput.next();
                }
            }

            switch (choice) {
                case 1:
                    clientResponse = getRequest(client, serverAddress + "/taxi");

                    SmartCity smartCity = Objects.requireNonNull(clientResponse).getEntity(SmartCity.class);

                    System.out.println("\nList of taxi:");
                    for (Taxi t : smartCity.getTaxiList()) System.out.println(t);

                    break;
                case 2:
                    try {
                        System.out.println("\nInsert the ID of the taxi:");
                        int id = stdInput.nextInt();
                        System.out.println("Insert the number of statistics to compute the average of:");
                        int n = stdInput.nextInt();

                        path = "/taxi/statistics/" + id + "/" + n;
                        clientResponse = getRequest(client, serverAddress + path);

                        Statistic statistic = Objects.requireNonNull(clientResponse).getEntity(Statistic.class);
                        System.out.println("\n" + statistic.printStatisticSingleTaxi());
                    } catch (InputMismatchException e) {
                        System.out.println("\nInput values are not correct");
                        break;
                    }

                    break;

                case 3:
                    double t1;
                    double t2;

                    try {
                        do {
                            System.out.println("\nInsert the start of the interval:");
                            t1 = stdInput.nextDouble();
                            System.out.println("Insert the end of the interval:");
                            t2 = stdInput.nextDouble();
                        } while (t1 >= t2);

                        path = "/taxi/allStatistics?t1=" + t1 + "&t2=" + t2;
                        clientResponse = getRequest(client, serverAddress + path);

                        Statistic allStatistics = Objects.requireNonNull(clientResponse).getEntity(Statistic.class);
                        System.out.println("\n" + allStatistics.printStatistics());

                    } catch (InputMismatchException e) {
                        System.out.println("\nInput values are not correct");
                        break;
                    }

                    break;

                case 4:
                    System.out.println("Thanks for using our service!");
                    break;

                default:
                    System.out.println("This option doesn't exist, please choose a valid one");
                    break;
            }

        } while (choice != 4);


    }

}
