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
public final class FinishRechargeGrpc {

  private FinishRechargeGrpc() {}

  public static final String SERVICE_NAME = "it.unimi.di.sdp.proto.FinishRecharge";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<it.unimi.di.sdp.proto.Recharge.RechargeResponse,
      it.unimi.di.sdp.proto.Recharge.RechargeResponse> getSendFinishRechargeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendFinishRecharge",
      requestType = it.unimi.di.sdp.proto.Recharge.RechargeResponse.class,
      responseType = it.unimi.di.sdp.proto.Recharge.RechargeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<it.unimi.di.sdp.proto.Recharge.RechargeResponse,
      it.unimi.di.sdp.proto.Recharge.RechargeResponse> getSendFinishRechargeMethod() {
    io.grpc.MethodDescriptor<it.unimi.di.sdp.proto.Recharge.RechargeResponse, it.unimi.di.sdp.proto.Recharge.RechargeResponse> getSendFinishRechargeMethod;
    if ((getSendFinishRechargeMethod = FinishRechargeGrpc.getSendFinishRechargeMethod) == null) {
      synchronized (FinishRechargeGrpc.class) {
        if ((getSendFinishRechargeMethod = FinishRechargeGrpc.getSendFinishRechargeMethod) == null) {
          FinishRechargeGrpc.getSendFinishRechargeMethod = getSendFinishRechargeMethod =
              io.grpc.MethodDescriptor.<it.unimi.di.sdp.proto.Recharge.RechargeResponse, it.unimi.di.sdp.proto.Recharge.RechargeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendFinishRecharge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  it.unimi.di.sdp.proto.Recharge.RechargeResponse.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  it.unimi.di.sdp.proto.Recharge.RechargeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FinishRechargeMethodDescriptorSupplier("sendFinishRecharge"))
              .build();
        }
      }
    }
    return getSendFinishRechargeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FinishRechargeStub newStub(io.grpc.Channel channel) {
    return new FinishRechargeStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FinishRechargeBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new FinishRechargeBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FinishRechargeFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new FinishRechargeFutureStub(channel);
  }

  /**
   */
  public static abstract class FinishRechargeImplBase implements io.grpc.BindableService {

    /**
     */
    public void sendFinishRecharge(it.unimi.di.sdp.proto.Recharge.RechargeResponse request,
        io.grpc.stub.StreamObserver<it.unimi.di.sdp.proto.Recharge.RechargeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendFinishRechargeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendFinishRechargeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                it.unimi.di.sdp.proto.Recharge.RechargeResponse,
                it.unimi.di.sdp.proto.Recharge.RechargeResponse>(
                  this, METHODID_SEND_FINISH_RECHARGE)))
          .build();
    }
  }

  /**
   */
  public static final class FinishRechargeStub extends io.grpc.stub.AbstractStub<FinishRechargeStub> {
    private FinishRechargeStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FinishRechargeStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FinishRechargeStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FinishRechargeStub(channel, callOptions);
    }

    /**
     */
    public void sendFinishRecharge(it.unimi.di.sdp.proto.Recharge.RechargeResponse request,
        io.grpc.stub.StreamObserver<it.unimi.di.sdp.proto.Recharge.RechargeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendFinishRechargeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FinishRechargeBlockingStub extends io.grpc.stub.AbstractStub<FinishRechargeBlockingStub> {
    private FinishRechargeBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FinishRechargeBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FinishRechargeBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FinishRechargeBlockingStub(channel, callOptions);
    }

    /**
     */
    public it.unimi.di.sdp.proto.Recharge.RechargeResponse sendFinishRecharge(it.unimi.di.sdp.proto.Recharge.RechargeResponse request) {
      return blockingUnaryCall(
          getChannel(), getSendFinishRechargeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FinishRechargeFutureStub extends io.grpc.stub.AbstractStub<FinishRechargeFutureStub> {
    private FinishRechargeFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FinishRechargeFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FinishRechargeFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FinishRechargeFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<it.unimi.di.sdp.proto.Recharge.RechargeResponse> sendFinishRecharge(
        it.unimi.di.sdp.proto.Recharge.RechargeResponse request) {
      return futureUnaryCall(
          getChannel().newCall(getSendFinishRechargeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_FINISH_RECHARGE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FinishRechargeImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FinishRechargeImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_FINISH_RECHARGE:
          serviceImpl.sendFinishRecharge((it.unimi.di.sdp.proto.Recharge.RechargeResponse) request,
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

  private static abstract class FinishRechargeBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FinishRechargeBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return it.unimi.di.sdp.proto.Recharge.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FinishRecharge");
    }
  }

  private static final class FinishRechargeFileDescriptorSupplier
      extends FinishRechargeBaseDescriptorSupplier {
    FinishRechargeFileDescriptorSupplier() {}
  }

  private static final class FinishRechargeMethodDescriptorSupplier
      extends FinishRechargeBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FinishRechargeMethodDescriptorSupplier(String methodName) {
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
      synchronized (FinishRechargeGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FinishRechargeFileDescriptorSupplier())
              .addMethod(getSendFinishRechargeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
