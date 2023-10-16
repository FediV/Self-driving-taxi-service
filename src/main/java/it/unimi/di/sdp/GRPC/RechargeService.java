package it.unimi.di.sdp.GRPC;

import io.grpc.stub.StreamObserver;
import it.unimi.di.sdp.Taxi;
import it.unimi.di.sdp.proto.Recharge.*;
import it.unimi.di.sdp.proto.RechargeServiceGrpc;

import java.util.List;

public class RechargeService extends RechargeServiceGrpc.RechargeServiceImplBase {
    private final Taxi serverTaxi;
    public static double timestamp = Double.MAX_VALUE;

    public RechargeService(Taxi serverTaxi) {
        this.serverTaxi = serverTaxi;
    }

    @Override
    public void sendRequestToRecharge(RechargeRequest request, StreamObserver<RechargeResponse> responseObserver) {

        RechargeResponse response = RechargeResponse.newBuilder().setResponse("ok").build();
        RechargeResponse noResponse = RechargeResponse.newBuilder().setResponse("no").build();

        int taxiDistrict = serverTaxi.getPosition().getDistrict();

        if (request.getIdTaxi()==serverTaxi.getId()) {

            timestamp = request.getClock();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } else if ( taxiDistrict == request.getDistrict() && serverTaxi.getNeedToRecharge()) {

            serverTaxi.getClock().setCounter((int) (Math.max(serverTaxi.getClock().getCounter(),request.getClock())) + 1);

            if (timestamp >= request.getClock() && !serverTaxi.isRecharging() ) {
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } else {
                List<Integer> p = serverTaxi.getRechargeQueue().get(taxiDistrict);
                p.add(request.getPortNumber());
                serverTaxi.getRechargeQueue().put(taxiDistrict,p);

                responseObserver.onNext(noResponse);
                responseObserver.onCompleted();
            }

        } else {
            serverTaxi.getClock().setCounter((int) (Math.max(serverTaxi.getClock().getCounter(),request.getClock())) + 1);

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

    }
}
