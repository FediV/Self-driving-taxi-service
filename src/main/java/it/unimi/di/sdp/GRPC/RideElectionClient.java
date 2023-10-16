package it.unimi.di.sdp.GRPC;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import it.unimi.di.sdp.Helper.Ride;
import it.unimi.di.sdp.Taxi;
import it.unimi.di.sdp.proto.ProtoRide;
import it.unimi.di.sdp.proto.ProtoRide.*;
import it.unimi.di.sdp.proto.RideServiceGrpc.*;

import java.util.concurrent.TimeUnit;

import static it.unimi.di.sdp.proto.RideServiceGrpc.newStub;

public class RideElectionClient extends Thread{
    private final Taxi serverTaxi;
    private final Taxi clientTaxi;
    private final Ride ride;
    private final Object rideLock;

    public RideElectionClient(Taxi serverTaxi, Taxi clientTaxi, Ride ride) {
        this.serverTaxi = serverTaxi;
        this.clientTaxi = clientTaxi;
        this.ride = ride;
        this.rideLock = clientTaxi.getRidesResponses().getLock();
    }

    public void run(){

        final ManagedChannel channel =
                ManagedChannelBuilder.forTarget(serverTaxi.getServerAddress() + ":" + serverTaxi.getPortNumber())
                        .usePlaintext().build();


        RideServiceStub stub = newStub(channel);

        ProtoRide.Ride protoRide = ProtoRide.Ride.newBuilder()
                .setDistrict(ride.getStartPosition().getDistrict())
                .setPosX(ride.getStartPosition().getX())
                .setPosY(ride.getStartPosition().getY())
                .setRideId(ride.getId())
                .build();

        double distance = computeDistance(ride);

        RideRequest req = RideRequest.newBuilder()
                .setRide(protoRide)
                .setIdTaxi(clientTaxi.getId())
                .setDistance(distance)
                .setBatteryLevel(clientTaxi.getBatteryLevel())
                .build();

        stub.sendRequestToGetRide(req, new StreamObserver<RideResponse>() {

            @Override
            public void onNext(RideResponse value) {

                clientTaxi.getRidesResponses().add(ride.getId(), value.getResponse());

                /*System.out.println("[Thread RideElection] taxi "+clientTaxi.getId()+" received a response: "+value.getResponse()+
                        " from taxi "+serverTaxi.getId() +
                        " for ride "+ride.getId());*/

                synchronized (rideLock) {
                    rideLock.notifyAll();
                }

            }

            @Override
            public void onError(Throwable t) {
                if(((StatusRuntimeException) t).getStatus().getCode() == Status.Code.UNAVAILABLE) {
                    clientTaxi.getRidesResponses().add(ride.getId(), "ok");
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

    private double computeDistance(Ride ride) {
        return Math.sqrt(Math.pow(ride.getStartPosition().getX()-clientTaxi.getPosition().getX(),2) + Math.pow(ride.getStartPosition().getY()-clientTaxi.getPosition().getY(),2));
    }
}
