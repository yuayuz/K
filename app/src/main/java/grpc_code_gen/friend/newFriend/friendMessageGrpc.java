package grpc_code_gen.friend.newFriend;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.53.0)",
    comments = "Source: newFriend.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class friendMessageGrpc {

  private friendMessageGrpc() {}

  public static final String SERVICE_NAME = "friendMessage";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc_code_gen.friend.newFriend.NewFriend.newFriendRequest,
      grpc_code_gen.friend.newFriend.NewFriend.newFriendReply> getFindMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "find",
      requestType = grpc_code_gen.friend.newFriend.NewFriend.newFriendRequest.class,
      responseType = grpc_code_gen.friend.newFriend.NewFriend.newFriendReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc_code_gen.friend.newFriend.NewFriend.newFriendRequest,
      grpc_code_gen.friend.newFriend.NewFriend.newFriendReply> getFindMethod() {
    io.grpc.MethodDescriptor<grpc_code_gen.friend.newFriend.NewFriend.newFriendRequest, grpc_code_gen.friend.newFriend.NewFriend.newFriendReply> getFindMethod;
    if ((getFindMethod = friendMessageGrpc.getFindMethod) == null) {
      synchronized (friendMessageGrpc.class) {
        if ((getFindMethod = friendMessageGrpc.getFindMethod) == null) {
          friendMessageGrpc.getFindMethod = getFindMethod =
              io.grpc.MethodDescriptor.<grpc_code_gen.friend.newFriend.NewFriend.newFriendRequest, grpc_code_gen.friend.newFriend.NewFriend.newFriendReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "find"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc_code_gen.friend.newFriend.NewFriend.newFriendRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc_code_gen.friend.newFriend.NewFriend.newFriendReply.getDefaultInstance()))
              .setSchemaDescriptor(new friendMessageMethodDescriptorSupplier("find"))
              .build();
        }
      }
    }
    return getFindMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static friendMessageStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<friendMessageStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<friendMessageStub>() {
        @java.lang.Override
        public friendMessageStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new friendMessageStub(channel, callOptions);
        }
      };
    return friendMessageStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static friendMessageBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<friendMessageBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<friendMessageBlockingStub>() {
        @java.lang.Override
        public friendMessageBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new friendMessageBlockingStub(channel, callOptions);
        }
      };
    return friendMessageBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static friendMessageFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<friendMessageFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<friendMessageFutureStub>() {
        @java.lang.Override
        public friendMessageFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new friendMessageFutureStub(channel, callOptions);
        }
      };
    return friendMessageFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class friendMessageImplBase implements io.grpc.BindableService {

    /**
     */
    public void find(grpc_code_gen.friend.newFriend.NewFriend.newFriendRequest request,
        io.grpc.stub.StreamObserver<grpc_code_gen.friend.newFriend.NewFriend.newFriendReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFindMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                grpc_code_gen.friend.newFriend.NewFriend.newFriendRequest,
                grpc_code_gen.friend.newFriend.NewFriend.newFriendReply>(
                  this, METHODID_FIND)))
          .build();
    }
  }

  /**
   */
  public static final class friendMessageStub extends io.grpc.stub.AbstractAsyncStub<friendMessageStub> {
    private friendMessageStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected friendMessageStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new friendMessageStub(channel, callOptions);
    }

    /**
     */
    public void find(grpc_code_gen.friend.newFriend.NewFriend.newFriendRequest request,
        io.grpc.stub.StreamObserver<grpc_code_gen.friend.newFriend.NewFriend.newFriendReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class friendMessageBlockingStub extends io.grpc.stub.AbstractBlockingStub<friendMessageBlockingStub> {
    private friendMessageBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected friendMessageBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new friendMessageBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc_code_gen.friend.newFriend.NewFriend.newFriendReply find(grpc_code_gen.friend.newFriend.NewFriend.newFriendRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class friendMessageFutureStub extends io.grpc.stub.AbstractFutureStub<friendMessageFutureStub> {
    private friendMessageFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected friendMessageFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new friendMessageFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc_code_gen.friend.newFriend.NewFriend.newFriendReply> find(
        grpc_code_gen.friend.newFriend.NewFriend.newFriendRequest request) {
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
    private final friendMessageImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(friendMessageImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FIND:
          serviceImpl.find((grpc_code_gen.friend.newFriend.NewFriend.newFriendRequest) request,
              (io.grpc.stub.StreamObserver<grpc_code_gen.friend.newFriend.NewFriend.newFriendReply>) responseObserver);
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

  private static abstract class friendMessageBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    friendMessageBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc_code_gen.friend.newFriend.NewFriend.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("friendMessage");
    }
  }

  private static final class friendMessageFileDescriptorSupplier
      extends friendMessageBaseDescriptorSupplier {
    friendMessageFileDescriptorSupplier() {}
  }

  private static final class friendMessageMethodDescriptorSupplier
      extends friendMessageBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    friendMessageMethodDescriptorSupplier(String methodName) {
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
      synchronized (friendMessageGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new friendMessageFileDescriptorSupplier())
              .addMethod(getFindMethod())
              .build();
        }
      }
    }
    return result;
  }
}
