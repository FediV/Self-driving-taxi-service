package it.unimi.di.sdp.GRPC;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import it.unimi.di.sdp.Taxi;
import it.unimi.di.sdp.proto.ExitTaxi.*;
import it.unimi.di.sdp.proto.ExitServiceGrpc.*;

import java.util.concurrent.TimeUnit;

import static it.unimi.di.sdp.proto.ExitServiceGrpc.newStub;

public class ExitTaxiClient extends Thread {
    private final Taxi serverTaxi;
    private final Taxi clientTaxi;

    public ExitTaxiClient(Taxi serverTaxi, Taxi clientTaxi) {
        this.serverTaxi = serverTaxi;
        this.clientTaxi = clientTaxi;
    }

    public void run(){

        final ManagedChannel channel =
                ManagedChannelBuilder.forTarget(serverTaxi.getServerAddress() + ":" + serverTaxi.getPortNumber())
                        .usePlaintext().build();

        ExitServiceStub stub = newStub(channel);

        TaxiDetails req = TaxiDetails.newBuilder()
                .setPortNumber(clientTaxi.getPortNumber())
                .setId(clientTaxi.getId())
                .build();

        stub.exit(req, new StreamObserver<ExitResponse>() {

            @Override
            public void onNext(ExitResponse exitResponse) {
            }

            @Override
            public void onError(Throwable t) {
                channel.shutdown();
            }

            @Override
            public void onCompleted() {
                channel.shutdown();
            }

        });

        try {
            channel.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("\n" + e.getCause() + " : " + e.getMessage());
        }

    }
}
