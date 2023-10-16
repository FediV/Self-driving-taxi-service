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
    comments = "Source: Recharge.proto")
public final class RechargeServiceGrpc {

  private RechargeServiceGrpc() {}

  public static final String SERVICE_NAME = "it.unimi.di.sdp.proto.RechargeService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<it.unimi.di.sdp.proto.Recharge.RechargeRequest,
      it.unimi.di.sdp.proto.Recharge.RechargeResponse> getSendRequestToRechargeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendRequestToRecharge",
      requestType = it.unimi.di.sdp.proto.Recharge.RechargeRequest.class,
      responseType = it.unimi.di.sdp.proto.Recharge.RechargeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<it.unimi.di.sdp.proto.Recharge.RechargeRequest,
      it.unimi.di.sdp.proto.Recharge.RechargeResponse> getSendRequestToRechargeMethod() {
    io.grpc.MethodDescriptor<it.unimi.di.sdp.proto.Recharge.RechargeRequest, it.unimi.di.sdp.proto.Recharge.RechargeResponse> getSendRequestToRechargeMethod;
    if ((getSendRequestToRechargeMethod = RechargeServiceGrpc.getSendRequestToRechargeMethod) == null) {
      synchronized (RechargeServiceGrpc.class) {
        if ((getSendRequestToRechargeMethod = RechargeServiceGrpc.getSendRequestToRechargeMethod) == null) {
          RechargeServiceGrpc.getSendRequestToRechargeMethod = getSendRequestToRechargeMethod =
              io.grpc.MethodDescriptor.<it.unimi.di.sdp.proto.Recharge.RechargeRequest, it.unimi.di.sdp.proto.Recharge.RechargeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendRequestToRecharge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  it.unimi.di.sdp.proto.Recharge.RechargeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  it.unimi.di.sdp.proto.Recharge.RechargeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RechargeServiceMethodDescriptorSupplier("sendRequestToRecharge"))
              .build();
        }
      }
    }
    return getSendRequestToRechargeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RechargeServiceStub newStub(io.grpc.Channel channel) {
    return new RechargeServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RechargeServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RechargeServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RechargeServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RechargeServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class RechargeServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sendRequestToRecharge(it.unimi.di.sdp.proto.Recharge.RechargeRequest request,
        io.grpc.stub.StreamObserver<it.unimi.di.sdp.proto.Recharge.RechargeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendRequestToRechargeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendRequestToRechargeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                it.unimi.di.sdp.proto.Recharge.RechargeRequest,
                it.unimi.di.sdp.proto.Recharge.RechargeResponse>(
                  this, METHODID_SEND_REQUEST_TO_RECHARGE)))
          .build();
    }
  }

  /**
   */
  public static final class RechargeServiceStub extends io.grpc.stub.AbstractStub<RechargeServiceStub> {
    private RechargeServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RechargeServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RechargeServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RechargeServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendRequestToRecharge(it.unimi.di.sdp.proto.Recharge.RechargeRequest request,
        io.grpc.stub.StreamObserver<it.unimi.di.sdp.proto.Recharge.RechargeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendRequestToRechargeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RechargeServiceBlockingStub extends io.grpc.stub.AbstractStub<RechargeServiceBlockingStub> {
    private RechargeServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RechargeServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RechargeServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RechargeServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public it.unimi.di.sdp.proto.Recharge.RechargeResponse sendRequestToRecharge(it.unimi.di.sdp.proto.Recharge.RechargeRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendRequestToRechargeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RechargeServiceFutureStub extends io.grpc.stub.AbstractStub<RechargeServiceFutureStub> {
    private RechargeServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RechargeServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RechargeServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RechargeServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<it.unimi.di.sdp.proto.Recharge.RechargeResponse> sendRequestToRecharge(
        it.unimi.di.sdp.proto.Recharge.RechargeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendRequestToRechargeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_REQUEST_TO_RECHARGE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RechargeServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RechargeServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_REQUEST_TO_RECHARGE:
          serviceImpl.sendRequestToRecharge((it.unimi.di.sdp.proto.Recharge.RechargeRequest) request,
              (io.grpc.stub.StreamObserver<it.unimi.di.sdp.proto.Recharge.RechargeResponse>) responseObserver);
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

  private static abstract class RechargeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RechargeServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return it.unimi.di.sdp.proto.Recharge.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RechargeService");
    }
  }

  private static final class RechargeServiceFileDescriptorSupplier
      extends RechargeServiceBaseDescriptorSupplier {
    RechargeServiceFileDescriptorSupplier() {}
  }

  private static final class RechargeServiceMethodDescriptorSupplier
      extends RechargeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RechargeServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (RechargeServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RechargeServiceFileDescriptorSupplier())
              .addMethod(getSendRequestToRechargeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
