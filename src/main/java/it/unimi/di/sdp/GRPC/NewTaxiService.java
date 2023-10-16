package it.unimi.di.sdp.GRPC;

import io.grpc.stub.StreamObserver;
import it.unimi.di.sdp.Taxi;
import it.unimi.di.sdp.proto.NewTaxi.*;
import it.unimi.di.sdp.proto.NewTaxiServiceGrpc.*;

public class NewTaxiService extends NewTaxiServiceImplBase {
    private final Taxi serverTaxi;

    public NewTaxiService(Taxi serverTaxi) {
        this.serverTaxi = serverTaxi;
    }

    @Override public void sendNewTaxi(NewTaxiDetails request, final StreamObserver<TaxiResponse> responseObserver){

        serverTaxi.getLocalSmartCity().add(new Taxi(request.getId(),request.getPortNumber()));
        TaxiResponse response = TaxiResponse.newBuilder().setResponse("ACK").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

}
