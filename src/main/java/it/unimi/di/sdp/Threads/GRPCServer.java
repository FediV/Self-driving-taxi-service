package it.unimi.di.sdp.Threads;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import it.unimi.di.sdp.GRPC.*;
import it.unimi.di.sdp.Taxi;

import java.io.IOException;

public class GRPCServer extends Thread{
    private final Taxi taxi;
    private final Object lock = new Object();

    public GRPCServer(Taxi taxi) {
        this.taxi = taxi;
    }

    public void run(){
        Server server = ServerBuilder.forPort(taxi.getPortNumber())
                .addService(new NewTaxiService(taxi))
                .addService(new RideElectionService(taxi))
                .addService(new RechargeService(taxi))
                .addService(new FinishRechargingService(taxi))
                .addService(new ExitTaxiService(taxi))
                .build();

        try {

            server.start();
            System.out.println("\n[Thread GRPC_SERVER] GRPC server started on port " + taxi.getPortNumber());

            synchronized (lock) {
                lock.notify();
            }

            for (Taxi t : taxi.getLocalSmartCity().getTaxiList()) {
                if (t.getId() != taxi.getId()) {
                    NewTaxiClient client = new NewTaxiClient(t,taxi);
                    client.start();
                }
            }

            synchronized (taxi) {
                taxi.wait();
            }

            server.shutdown();
            System.out.println("\n[GRPC] GRPC server stopped ");

        } catch (IOException e) {
            System.out.println("\n[GRPC] error while starting GRPC server on port "+taxi.getPortNumber()+" : "+e.getMessage());
            StdInput.safeExit(taxi);
            server.shutdown();

            synchronized (lock) {
                lock.notify();
            }
        } catch (InterruptedException e) {
            System.out.println("\nGRPC server stopped ");
            StdInput.safeExit(taxi);
            server.shutdown();
        }
    }

    public void onConnect() throws InterruptedException {
        synchronized (lock) {
            lock.wait();
        }
    }
}
