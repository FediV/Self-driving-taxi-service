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
    comments = "Source: NewTaxi.proto")
public final class NewTaxiServiceGrpc {

  private NewTaxiServiceGrpc() {}

  public static final String SERVICE_NAME = "it.unimi.di.sdp.proto.NewTaxiService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<it.unimi.di.sdp.proto.NewTaxi.NewTaxiDetails,
      it.unimi.di.sdp.proto.NewTaxi.TaxiResponse> getSendNewTaxiMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendNewTaxi",
      requestType = it.unimi.di.sdp.proto.NewTaxi.NewTaxiDetails.class,
      responseType = it.unimi.di.sdp.proto.NewTaxi.TaxiResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<it.unimi.di.sdp.proto.NewTaxi.NewTaxiDetails,
      it.unimi.di.sdp.proto.NewTaxi.TaxiResponse> getSendNewTaxiMethod() {
    io.grpc.MethodDescriptor<it.unimi.di.sdp.proto.NewTaxi.NewTaxiDetails, it.unimi.di.sdp.proto.NewTaxi.TaxiResponse> getSendNewTaxiMethod;
    if ((getSendNewTaxiMethod = NewTaxiServiceGrpc.getSendNewTaxiMethod) == null) {
      synchronized (NewTaxiServiceGrpc.class) {
        if ((getSendNewTaxiMethod = NewTaxiServiceGrpc.getSendNewTaxiMethod) == null) {
          NewTaxiServiceGrpc.getSendNewTaxiMethod = getSendNewTaxiMethod =
              io.grpc.MethodDescriptor.<it.unimi.di.sdp.proto.NewTaxi.NewTaxiDetails, it.unimi.di.sdp.proto.NewTaxi.TaxiResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendNewTaxi"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  it.unimi.di.sdp.proto.NewTaxi.NewTaxiDetails.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  it.unimi.di.sdp.proto.NewTaxi.TaxiResponse.getDefaultInstance()))
              .setSchemaDescriptor(new NewTaxiServiceMethodDescriptorSupplier("sendNewTaxi"))
              .build();
        }
      }
    }
    return getSendNewTaxiMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NewTaxiServiceStub newStub(io.grpc.Channel channel) {
    return new NewTaxiServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NewTaxiServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new NewTaxiServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NewTaxiServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new NewTaxiServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class NewTaxiServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sendNewTaxi(it.unimi.di.sdp.proto.NewTaxi.NewTaxiDetails request,
        io.grpc.stub.StreamObserver<it.unimi.di.sdp.proto.NewTaxi.TaxiResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendNewTaxiMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendNewTaxiMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                it.unimi.di.sdp.proto.NewTaxi.NewTaxiDetails,
                it.unimi.di.sdp.proto.NewTaxi.TaxiResponse>(
                  this, METHODID_SEND_NEW_TAXI)))
          .build();
    }
  }

  /**
   */
  public static final class NewTaxiServiceStub extends io.grpc.stub.AbstractStub<NewTaxiServiceStub> {
    private NewTaxiServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NewTaxiServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NewTaxiServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NewTaxiServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendNewTaxi(it.unimi.di.sdp.proto.NewTaxi.NewTaxiDetails request,
        io.grpc.stub.StreamObserver<it.unimi.di.sdp.proto.NewTaxi.TaxiResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendNewTaxiMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class NewTaxiServiceBlockingStub extends io.grpc.stub.AbstractStub<NewTaxiServiceBlockingStub> {
    private NewTaxiServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NewTaxiServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NewTaxiServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NewTaxiServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public it.unimi.di.sdp.proto.NewTaxi.TaxiResponse sendNewTaxi(it.unimi.di.sdp.proto.NewTaxi.NewTaxiDetails request) {
      return blockingUnaryCall(
          getChannel(), getSendNewTaxiMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class NewTaxiServiceFutureStub extends io.grpc.stub.AbstractStub<NewTaxiServiceFutureStub> {
    private NewTaxiServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NewTaxiServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NewTaxiServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NewTaxiServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<it.unimi.di.sdp.proto.NewTaxi.TaxiResponse> sendNewTaxi(
        it.unimi.di.sdp.proto.NewTaxi.NewTaxiDetails request) {
      return futureUnaryCall(
          getChannel().newCall(getSendNewTaxiMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_NEW_TAXI = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NewTaxiServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(NewTaxiServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_NEW_TAXI:
          serviceImpl.sendNewTaxi((it.unimi.di.sdp.proto.NewTaxi.NewTaxiDetails) request,
              (io.grpc.stub.StreamObserver<it.unimi.di.sdp.proto.NewTaxi.TaxiResponse>) responseObserver);
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

  private static abstract class NewTaxiServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    NewTaxiServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return it.unimi.di.sdp.proto.NewTaxi.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("NewTaxiService");
    }
  }

  private static final class NewTaxiServiceFileDescriptorSupplier
      extends NewTaxiServiceBaseDescriptorSupplier {
    NewTaxiServiceFileDescriptorSupplier() {}
  }

  private static final class NewTaxiServiceMethodDescriptorSupplier
      extends NewTaxiServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    NewTaxiServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (NewTaxiServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NewTaxiServiceFileDescriptorSupplier())
              .addMethod(getSendNewTaxiMethod())
              .build();
        }
      }
    }
    return result;
  }
}
