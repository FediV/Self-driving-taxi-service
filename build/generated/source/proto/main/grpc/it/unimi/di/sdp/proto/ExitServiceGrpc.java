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
    comments = "Source: ExitTaxi.proto")
public final class ExitServiceGrpc {

  private ExitServiceGrpc() {}

  public static final String SERVICE_NAME = "it.unimi.di.sdp.proto.ExitService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<it.unimi.di.sdp.proto.ExitTaxi.TaxiDetails,
      it.unimi.di.sdp.proto.ExitTaxi.ExitResponse> getExitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "exit",
      requestType = it.unimi.di.sdp.proto.ExitTaxi.TaxiDetails.class,
      responseType = it.unimi.di.sdp.proto.ExitTaxi.ExitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<it.unimi.di.sdp.proto.ExitTaxi.TaxiDetails,
      it.unimi.di.sdp.proto.ExitTaxi.ExitResponse> getExitMethod() {
    io.grpc.MethodDescriptor<it.unimi.di.sdp.proto.ExitTaxi.TaxiDetails, it.unimi.di.sdp.proto.ExitTaxi.ExitResponse> getExitMethod;
    if ((getExitMethod = ExitServiceGrpc.getExitMethod) == null) {
      synchronized (ExitServiceGrpc.class) {
        if ((getExitMethod = ExitServiceGrpc.getExitMethod) == null) {
          ExitServiceGrpc.getExitMethod = getExitMethod =
              io.grpc.MethodDescriptor.<it.unimi.di.sdp.proto.ExitTaxi.TaxiDetails, it.unimi.di.sdp.proto.ExitTaxi.ExitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "exit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  it.unimi.di.sdp.proto.ExitTaxi.TaxiDetails.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  it.unimi.di.sdp.proto.ExitTaxi.ExitResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ExitServiceMethodDescriptorSupplier("exit"))
              .build();
        }
      }
    }
    return getExitMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ExitServiceStub newStub(io.grpc.Channel channel) {
    return new ExitServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ExitServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ExitServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ExitServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ExitServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ExitServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void exit(it.unimi.di.sdp.proto.ExitTaxi.TaxiDetails request,
        io.grpc.stub.StreamObserver<it.unimi.di.sdp.proto.ExitTaxi.ExitResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getExitMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getExitMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                it.unimi.di.sdp.proto.ExitTaxi.TaxiDetails,
                it.unimi.di.sdp.proto.ExitTaxi.ExitResponse>(
                  this, METHODID_EXIT)))
          .build();
    }
  }

  /**
   */
  public static final class ExitServiceStub extends io.grpc.stub.AbstractStub<ExitServiceStub> {
    private ExitServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ExitServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExitServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ExitServiceStub(channel, callOptions);
    }

    /**
     */
    public void exit(it.unimi.di.sdp.proto.ExitTaxi.TaxiDetails request,
        io.grpc.stub.StreamObserver<it.unimi.di.sdp.proto.ExitTaxi.ExitResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExitMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ExitServiceBlockingStub extends io.grpc.stub.AbstractStub<ExitServiceBlockingStub> {
    private ExitServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ExitServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExitServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ExitServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public it.unimi.di.sdp.proto.ExitTaxi.ExitResponse exit(it.unimi.di.sdp.proto.ExitTaxi.TaxiDetails request) {
      return blockingUnaryCall(
          getChannel(), getExitMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ExitServiceFutureStub extends io.grpc.stub.AbstractStub<ExitServiceFutureStub> {
    private ExitServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ExitServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExitServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ExitServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<it.unimi.di.sdp.proto.ExitTaxi.ExitResponse> exit(
        it.unimi.di.sdp.proto.ExitTaxi.TaxiDetails request) {
      return futureUnaryCall(
          getChannel().newCall(getExitMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EXIT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ExitServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ExitServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_EXIT:
          serviceImpl.exit((it.unimi.di.sdp.proto.ExitTaxi.TaxiDetails) request,
              (io.grpc.stub.StreamObserver<it.unimi.di.sdp.proto.ExitTaxi.ExitResponse>) responseObserver);
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

  private static abstract class ExitServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ExitServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return it.unimi.di.sdp.proto.ExitTaxi.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ExitService");
    }
  }

  private static final class ExitServiceFileDescriptorSupplier
      extends ExitServiceBaseDescriptorSupplier {
    ExitServiceFileDescriptorSupplier() {}
  }

  private static final class ExitServiceMethodDescriptorSupplier
      extends ExitServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ExitServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ExitServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ExitServiceFileDescriptorSupplier())
              .addMethod(getExitMethod())
              .build();
        }
      }
    }
    return result;
  }
}
