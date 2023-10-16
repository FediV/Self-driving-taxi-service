package it.unimi.di.sdp.GRPC;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import it.unimi.di.sdp.Taxi;
import it.unimi.di.sdp.proto.NewTaxiServiceGrpc.*;
import it.unimi.di.sdp.proto.NewTaxiServiceGrpc;
import it.unimi.di.sdp.proto.NewTaxi.*;

import java.util.concurrent.TimeUnit;

public class NewTaxiClient extends Thread{
    private final Taxi serverTaxi;
    private final Taxi clientTaxi;

    public NewTaxiClient(Taxi serverTaxi, Taxi clientTaxi) {
        this.serverTaxi = serverTaxi;
        this.clientTaxi = clientTaxi;
    }

    public void run(){

        final ManagedChannel channel =
                ManagedChannelBuilder.forTarget(serverTaxi.getServerAddress() + ":" + serverTaxi.getPortNumber())
                        .usePlaintext().build();

        NewTaxiServiceStub stub = NewTaxiServiceGrpc.newStub(channel);

        NewTaxiDetails req = NewTaxiDetails.newBuilder()
                .setPortNumber(clientTaxi.getPortNumber())
                .setId(clientTaxi.getId())
                .setPosX(clientTaxi.getPosition().getX())
                .setPosY(clientTaxi.getPosition().getY())
                .build();

        stub.sendNewTaxi(req, new StreamObserver<TaxiResponse>() {

            @Override
            public void onNext(TaxiResponse value) {
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
