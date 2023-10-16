package it.unimi.di.sdp.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.25.0)",
    comments = "Source: ProtoRide.proto")
public final class RideServiceGrpc {

  private RideServiceGrpc() {}

  public static final String SERVICE_NAME = "it.unimi.di.sdp.proto.RideService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<it.unimi.di.sdp.proto.ProtoRide.RideRequest,
      it.unimi.di.sdp.proto.ProtoRide.RideResponse> getSendRequestToGetRideMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendRequestToGetRide",
      requestType = it.unimi.di.sdp.proto.ProtoRide.RideRequest.class,
      responseType = it.unimi.di.sdp.proto.ProtoRide.RideResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<it.unimi.di.sdp.proto.ProtoRide.RideRequest,
      it.unimi.di.sdp.proto.ProtoRide.RideResponse> getSendRequestToGetRideMethod() {
    io.grpc.MethodDescriptor<it.unimi.di.sdp.proto.ProtoRide.RideRequest, it.unimi.di.sdp.proto.ProtoRide.RideResponse> getSendRequestToGetRideMethod;
    if ((getSendRequestToGetRideMethod = RideServiceGrpc.getSendRequestToGetRideMethod) == null) {
      synchronized (RideServiceGrpc.class) {
        if ((getSendRequestToGetRideMethod = RideServiceGrpc.getSendRequestToGetRideMethod) == null) {
          RideServiceGrpc.getSendRequestToGetRideMethod = getSendRequestToGetRideMethod =
              io.grpc.MethodDescriptor.<it.unimi.di.sdp.proto.ProtoRide.RideRequest, it.unimi.di.sdp.proto.ProtoRide.RideResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendRequestToGetRide"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  it.unimi.di.sdp.proto.ProtoRide.RideRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  it.unimi.di.sdp.proto.ProtoRide.RideResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RideServiceMethodDescriptorSupplier("sendRequestToGetRide"))
              .build();
        }
      }
    }
    return getSendRequestToGetRideMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RideServiceStub newStub(io.grpc.Channel channel) {
    return new RideServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RideServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RideServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RideServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RideServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class RideServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sendRequestToGetRide(it.unimi.di.sdp.proto.ProtoRide.RideRequest request,
        io.grpc.stub.StreamObserver<it.unimi.di.sdp.proto.ProtoRide.RideResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendRequestToGetRideMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendRequestToGetRideMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                it.unimi.di.sdp.proto.ProtoRide.RideRequest,
                it.unimi.di.sdp.proto.ProtoRide.RideResponse>(
                  this, METHODID_SEND_REQUEST_TO_GET_RIDE)))
          .build();
    }
  }

  /**
   */
  public static final class RideServiceStub extends io.grpc.stub.AbstractStub<RideServiceStub> {
    private RideServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RideServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RideServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RideServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendRequestToGetRide(it.unimi.di.sdp.proto.ProtoRide.RideRequest request,
        io.grpc.stub.StreamObserver<it.unimi.di.sdp.proto.ProtoRide.RideResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendRequestToGetRideMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RideServiceBlockingStub extends io.grpc.stub.AbstractStub<RideServiceBlockingStub> {
    private RideServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RideServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RideServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RideServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public it.unimi.di.sdp.proto.ProtoRide.RideResponse sendRequestToGetRide(it.unimi.di.sdp.proto.ProtoRide.RideRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendRequestToGetRideMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RideServiceFutureStub extends io.grpc.stub.AbstractStub<RideServiceFutureStub> {
    private RideServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RideServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RideServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RideServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<it.unimi.di.sdp.proto.ProtoRide.RideResponse> sendRequestToGetRide(
        it.unimi.di.sdp.proto.ProtoRide.RideRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendRequestToGetRideMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_REQUEST_TO_GET_RIDE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RideServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RideServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_REQUEST_TO_GET_RIDE:
          serviceImpl.sendRequestToGetRide((it.unimi.di.sdp.proto.ProtoRide.RideRequest) request,
              (io.grpc.stub.StreamObserver<it.unimi.di.sdp.proto.ProtoRide.RideResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RideServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RideServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return it.unimi.di.sdp.proto.ProtoRide.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RideService");
    }
  }

  private static final class RideServiceFileDescriptorSupplier
      extends RideServiceBaseDescriptorSupplier {
    RideServiceFileDescriptorSupplier() {}
  }

  private static final class RideServiceMethodDescriptorSupplier
      extends RideServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RideServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RideServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RideServiceFileDescriptorSupplier())
              .addMethod(getSendRequestToGetRideMethod())
              .build();
        }
      }
    }
    return result;
  }
}
