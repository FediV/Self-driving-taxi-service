package it.unimi.di.sdp.GRPC;

import io.grpc.stub.StreamObserver;
import it.unimi.di.sdp.Taxi;
import it.unimi.di.sdp.proto.FinishRechargeGrpc.*;
import it.unimi.di.sdp.proto.Recharge.*;


public class FinishRechargingService extends FinishRechargeImplBase {
    private final Taxi serverTaxi;

    public FinishRechargingService(Taxi serverTaxi) {
        this.serverTaxi = serverTaxi;
    }

    @Override
    public void sendFinishRecharge(RechargeResponse request, StreamObserver<RechargeResponse> responseObserver) {

        serverTaxi.getRechargeResponses().add(serverTaxi.getPosition().getDistrict(),request.getResponse());

        RechargeResponse response = RechargeResponse.newBuilder().setResponse("ACK").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}
