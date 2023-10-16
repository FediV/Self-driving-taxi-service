package it.unimi.di.sdp.GRPC;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import it.unimi.di.sdp.Taxi;
import it.unimi.di.sdp.proto.Recharge.*;
import it.unimi.di.sdp.proto.RechargeServiceGrpc.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static it.unimi.di.sdp.proto.RechargeServiceGrpc.newStub;

public class RechargeClient extends Thread{
    private final Taxi serverTaxi;
    private final Taxi clientTaxi;
    private final int clock;
    private final Object rechargeLock;

    public RechargeClient(Taxi serverTaxi, Taxi clientTaxi, int clock) {
        this.serverTaxi = serverTaxi;
        this.clientTaxi = clientTaxi;
        this.rechargeLock = clientTaxi.getRechargeResponses().getLock();
        this.clock = clock;
    }

    public void run(){

        Random random = new Random();

        final ManagedChannel channel =
                ManagedChannelBuilder.forTarget(serverTaxi.getServerAddress() + ":" + serverTaxi.getPortNumber())
                        .usePlaintext().build();

        RechargeServiceStub stub = newStub(channel);

        double timestamp = System.currentTimeMillis() + random.nextDouble();

        RechargeRequest req = RechargeRequest.newBuilder()
                .setIdTaxi(clientTaxi.getId())
                .setDistrict(clientTaxi.getPosition().getDistrict())
                .setPortNumber(clientTaxi.getPortNumber())
                .setTimestamp(timestamp)
                .setClock(clock)
                .build();

        stub.sendRequestToRecharge(req, new StreamObserver<RechargeResponse>() {

            @Override
            public void onNext(RechargeResponse value) {

                if (value.getResponse().equals("ok")) {
                    clientTaxi.getRechargeResponses().add(clientTaxi.getPosition().getDistrict(),value.getResponse());

                    synchronized (rechargeLock) {
                        rechargeLock.notifyAll();
                    }
                }

            }

            @Override
            public void onError(Throwable t) {
                if(((StatusRuntimeException) t).getStatus().getCode() == Status.Code.UNAVAILABLE) {
                    clientTaxi.getRechargeResponses().add(clientTaxi.getPosition().getDistrict(),"ok");
                }

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
