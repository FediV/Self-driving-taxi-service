package it.unimi.di.sdp.GRPC;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import it.unimi.di.sdp.Taxi;
import it.unimi.di.sdp.proto.FinishRechargeGrpc;
import it.unimi.di.sdp.proto.FinishRechargeGrpc.*;
import it.unimi.di.sdp.proto.Recharge.*;

import java.util.concurrent.TimeUnit;

public class FinishRechargingClient extends Thread{
    private final int serverTaxiPort;
    private final Taxi clientTaxi;

    public FinishRechargingClient(int port, Taxi clientTaxi) {
        this.serverTaxiPort = port;
        this.clientTaxi = clientTaxi;
    }

    public void run(){

        final ManagedChannel channel =
                ManagedChannelBuilder.forTarget("localhost" + ":" + serverTaxiPort)
                        .usePlaintext().build();

        FinishRechargeStub stub = FinishRechargeGrpc.newStub(channel);

        RechargeResponse req = RechargeResponse.newBuilder()
                .setResponse("ok")
                .build();

        System.out.println("[Thread FINISH RECHARGING] taxi "+clientTaxi.getId()+" is sending ok "
                +"to taxi "+serverTaxiPort);

        stub.sendFinishRecharge(req, new StreamObserver<RechargeResponse>() {

            @Override
            public void onNext(RechargeResponse value) {
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
