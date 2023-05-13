package grpc_code_gen.chat.message;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.53.0)",
    comments = "Source: message.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class messageGrpc {

  private messageGrpc() {}

  public static final String SERVICE_NAME = "message";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc_code_gen.chat.message.Message.MessageRequest,
      grpc_code_gen.chat.message.Message.MessageReply> getFindMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "find",
      requestType = grpc_code_gen.chat.message.Message.MessageRequest.class,
      responseType = grpc_code_gen.chat.message.Message.MessageReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc_code_gen.chat.message.Message.MessageRequest,
      grpc_code_gen.chat.message.Message.MessageReply> getFindMethod() {
    io.grpc.MethodDescriptor<grpc_code_gen.chat.message.Message.MessageRequest, grpc_code_gen.chat.message.Message.MessageReply> getFindMethod;
    if ((getFindMethod = messageGrpc.getFindMethod) == null) {
      synchronized (messageGrpc.class) {
        if ((getFindMethod = messageGrpc.getFindMethod) == null) {
          messageGrpc.getFindMethod = getFindMethod =
              io.grpc.MethodDescriptor.<grpc_code_gen.chat.message.Message.MessageRequest, grpc_code_gen.chat.message.Message.MessageReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "find"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc_code_gen.chat.message.Message.MessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc_code_gen.chat.message.Message.MessageReply.getDefaultInstance()))
              .setSchemaDescriptor(new messageMethodDescriptorSupplier("find"))
              .build();
        }
      }
    }
    return getFindMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static messageStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<messageStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<messageStub>() {
        @java.lang.Override
        public messageStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new messageStub(channel, callOptions);
        }
      };
    return messageStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static messageBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<messageBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<messageBlockingStub>() {
        @java.lang.Override
        public messageBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new messageBlockingStub(channel, callOptions);
        }
      };
    return messageBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static messageFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<messageFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<messageFutureStub>() {
        @java.lang.Override
        public messageFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new messageFutureStub(channel, callOptions);
        }
      };
    return messageFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class messageImplBase implements io.grpc.BindableService {

    /**
     */
    public void find(grpc_code_gen.chat.message.Message.MessageRequest request,
        io.grpc.stub.StreamObserver<grpc_code_gen.chat.message.Message.MessageReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFindMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                grpc_code_gen.chat.message.Message.MessageRequest,
                grpc_code_gen.chat.message.Message.MessageReply>(
                  this, METHODID_FIND)))
          .build();
    }
  }

  /**
   */
  public static final class messageStub extends io.grpc.stub.AbstractAsyncStub<messageStub> {
    private messageStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected messageStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new messageStub(channel, callOptions);
    }

    /**
     */
    public void find(grpc_code_gen.chat.message.Message.MessageRequest request,
        io.grpc.stub.StreamObserver<grpc_code_gen.chat.message.Message.MessageReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class messageBlockingStub extends io.grpc.stub.AbstractBlockingStub<messageBlockingStub> {
    private messageBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected messageBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new messageBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc_code_gen.chat.message.Message.MessageReply find(grpc_code_gen.chat.message.Message.MessageRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class messageFutureStub extends io.grpc.stub.AbstractFutureStub<messageFutureStub> {
    private messageFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected messageFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new messageFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc_code_gen.chat.message.Message.MessageReply> find(
        grpc_code_gen.chat.message.Message.MessageRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFindMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FIND = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final messageImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(messageImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FIND:
          serviceImpl.find((grpc_code_gen.chat.message.Message.MessageRequest) request,
              (io.grpc.stub.StreamObserver<grpc_code_gen.chat.message.Message.MessageReply>) responseObserver);
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

  private static abstract class messageBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    messageBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc_code_gen.chat.message.Message.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("message");
    }
  }

  private static final class messageFileDescriptorSupplier
      extends messageBaseDescriptorSupplier {
    messageFileDescriptorSupplier() {}
  }

  private static final class messageMethodDescriptorSupplier
      extends messageBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    messageMethodDescriptorSupplier(String methodName) {
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
      synchronized (messageGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new messageFileDescriptorSupplier())
              .addMethod(getFindMethod())
              .build();
        }
      }
    }
    return result;
  }
}
