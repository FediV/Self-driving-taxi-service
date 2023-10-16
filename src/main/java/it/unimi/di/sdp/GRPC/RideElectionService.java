package it.unimi.di.sdp.GRPC;

import io.grpc.stub.StreamObserver;
import it.unimi.di.sdp.Taxi;
import it.unimi.di.sdp.proto.ProtoRide.*;
import it.unimi.di.sdp.proto.RideServiceGrpc.RideServiceImplBase;

public class RideElectionService extends RideServiceImplBase {
    private final Taxi serverTaxi;

    public RideElectionService(Taxi taxi) {
        this.serverTaxi = taxi;
    }

    @Override
    public void sendRequestToGetRide(RideRequest request, StreamObserver<RideResponse> responseObserver) {

        RideResponse response = RideResponse.newBuilder().setResponse("ok").build();
        RideResponse noResponse = RideResponse.newBuilder().setResponse("no").build();

        double distance = computeDistance(request.getRide());

        boolean isAlreadyServed = serverTaxi.getRidesServed().stream().filter(r -> r.getId() == request.getRide().getRideId()).count() == 1;
        if (isAlreadyServed) {
            responseObserver.onNext(noResponse);
            responseObserver.onCompleted();
            return;
        }

        if ( serverTaxi.getPosition().getDistrict() == request.getRide().getDistrict() ) {

            if (!serverTaxi.isAvailable() || !serverTaxi.getRidesResponses().contains(request.getRide().getRideId())) {
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } else {
                if (distance > request.getDistance()) {
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                } else if (distance == request.getDistance()) {
                    if (serverTaxi.getBatteryLevel() < request.getBatteryLevel()) {
                        responseObserver.onNext(response);
                        responseObserver.onCompleted();
                    } else if (serverTaxi.getBatteryLevel() == request.getBatteryLevel()) {
                        if (serverTaxi.getId() < request.getIdTaxi()) {
                            responseObserver.onNext(response);
                            responseObserver.onCompleted();
                        } else {
                            responseObserver.onNext(noResponse);
                            responseObserver.onCompleted();
                        }
                    } else {
                        responseObserver.onNext(noResponse);
                        responseObserver.onCompleted();
                    }
                } else {
                    responseObserver.onNext(noResponse);
                    responseObserver.onCompleted();
                }
            }

        } else {
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

    }

    private double computeDistance(Ride ride) {
        double result;
        result = Math.sqrt(Math.pow(ride.getPosX()-serverTaxi.getPosition().getX(),2) + Math.pow(ride.getPosY()-serverTaxi.getPosition().getY(),2));

        return result;
    }

}
