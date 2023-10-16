package it.unimi.di.sdp.GRPC;

import io.grpc.stub.StreamObserver;
import it.unimi.di.sdp.Taxi;
import it.unimi.di.sdp.proto.ExitServiceGrpc.*;
import it.unimi.di.sdp.proto.ExitTaxi.*;

public class ExitTaxiService extends ExitServiceImplBase {
    private final Taxi serverTaxi;

    public ExitTaxiService(Taxi serverTaxi) {
        this.serverTaxi = serverTaxi;
    }

    @Override public void exit (TaxiDetails request, final StreamObserver<ExitResponse> responseObserver){

        serverTaxi.getLocalSmartCity().delete(request.getId());

        ExitResponse response = ExitResponse.newBuilder().setResponse("removed").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

}
