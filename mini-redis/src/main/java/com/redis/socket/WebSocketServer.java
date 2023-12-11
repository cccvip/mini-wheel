package com.redis.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.CharsetUtil;

public class WebSocketServer {

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new WebSocketServerInitializer());

            ChannelFuture f = b.bind(8080).sync();
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static class WebSocketServerInitializer extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();
            pipeline.addLast(new HttpServerCodec());
            pipeline.addLast(new ChunkedWriteHandler());
            pipeline.addLast(new HttpObjectAggregator(8192));

            pipeline.addLast(new WebSocketServerCompressionHandler());

            pipeline.addLast(new WebSocketServerProtocolHandler("/websocket",null,true));

            pipeline.addLast(new MyPingHandler());
        }
    }

    public static class MyPingHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
        //https://developer.mozilla.org/zh-CN/docs/Web/API/WebSockets_API/Writing_WebSocket_servers#pings_%E5%92%8C_pongs%EF%BC%9Awebsockets_%E7%9A%84%E5%BF%83%E8%B7%B3

//客户端或服务器端都可以通过发送一个带有指定控制序列的控制帧以开始关闭连接握手（参见章节 5.5.1）。对端收到这个控制帧会回复一个关闭帧，关闭发起端关闭连接。任何在关闭连接后接收到的数据都会被丢弃。
        //        在经过握手之后的任意时刻里，无论客户端还是服务端都可以选择发送一个 ping 给另一方。当 ping 消息收到的时候，接受的一方必须尽快回复一个 pong 消息。例如，可以使用这种方式来确保客户端还是连接状态。
//
//        一个 ping 或者 pong 都只是一个常规的帧，只是这个帧是一个控制帧。Ping 消息的 opcode 字段值为 0x9，pong 消息的 opcode 值为 0xA 。当你获取到一个 ping 消息的时候，回复一个跟 ping 消息有相同载荷数据的 pong 消息 (对于 ping 和 pong，最大载荷长度位 125)。你也有可能在没有发送 ping 消息的情况下，获取一个 pong 消息，当这种情况发生的时候忽略它。
//
//        备注： 如果在你有机会发送一个 pong 消息之前，你已经获取了超过一个的 ping 消息，那么你只发送一个 pong 消息。



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        if (frame instanceof BinaryWebSocketFrame) {
            // 解析收到的 BinaryWebSocketFrame
            BinaryWebSocketFrame binaryFrame = (BinaryWebSocketFrame) frame;
            ByteBuf binaryData = binaryFrame.content();
            System.out.println(binaryData.toString(CharsetUtil.UTF_8));
//          读取 WebSocket 帧的 Opcode 字段来获取帧的类型信息。WebSocket 帧的 Opcode 字段位于帧头的第一个字节，占据 4 个 bit。
            byte firstByte = binaryData.getByte(0); // 帧头的第一个字节
            System.out.println(firstByte);
        } else {
            // 其他类型的帧处理
            System.out.println(frame.content().retain().toString(CharsetUtil.UTF_8));
        }
        }
    }

}
