package stu.java.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * tcp连接客户端，就是连接设备的
 */
public class TCPClient {

    private EventLoopGroup workerGroup;
    private Bootstrap b;
    private String ip;
    private int port;
    private ChannelFuture future;
    private Channel channel;
    private ChannelHandlerContext ctx;

    //构造方法
    public TCPClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    //初始化客户端
    private void initClient() {
        workerGroup = WebManager.workerGroup;
        b = new Bootstrap();
        b.group(workerGroup);
        b.channel(NioSocketChannel.class);
        b.option(ChannelOption.SO_KEEPALIVE, true);//保持连接
        b.option(ChannelOption.TCP_NODELAY, true);//有数据立即发送
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                //连接回调
//                ch.pipeline().addLast(new FeedbackInboundHandler());
                ch.pipeline().addLast(new WebClientHandler());
            }
        });

    }

    /**
     * 连接客户端
     */
    public void connect() {
        initClient();//初始化客户端
        try {
            future = b.connect(new InetSocketAddress(ip, port));
            channel = future.channel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否连接了
     */
    public boolean isconnect() {
        return channel != null && channel.isOpen() && channel.isWritable();
    }

    /**
     * 重新连接
     */
    public void reconnect() {
        close();//关闭连接
        connect();//打开连接
    }

    /**
     * 关闭
     */
    public void close() {
        if (future != null && future.channel().isOpen()) {
            try {
                future.channel().close().await(2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            future = null;
            channel = null;
        }
        TCPManager.getInstance().getTcpClientMap().remove(ip + "#" + port);
    }

    /**
     * 发送字符串给设备
     *
     * @param str
     * @return
     */
    public boolean sendString(String str) {
//        System.out.println("sendString======isWritable"+channel.isWritable()+"    isOpen"+channel.isOpen());
        if (channel != null && channel.isOpen() && channel.isWritable()) {
//            System.out.println("进来了sendString"+str);
            ByteBuf byteBuf = channel.alloc().buffer();
            byteBuf.resetWriterIndex();
            byteBuf.writeBytes(str.getBytes());
            channel.writeAndFlush(byteBuf);
            return true;
        } else {
            return false;//直接返回前端连接失败，不执行重新连接了
        }
    }


    /**
     * 发送byte数组给设备
     *
     * @param hexByte 转换后的
     * @return
     */
    public boolean sendHexString(byte[] hexByte) {
        if (channel != null && channel.isOpen() && channel.isWritable()) {
            ByteBuf byteBuf = channel.alloc().buffer();
            byteBuf.resetWriterIndex();
            byteBuf.writeBytes(hexByte);
            channel.writeAndFlush(byteBuf);
            return true;
        } else {
            return false;//直接返回前端连接失败，不执行重新连接了
        }
    }

    /**
     * 自定义的反馈处理
     */
    class FeedbackInboundHandler extends ByteToMessageDecoder {
        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) {

        }

    }
    /**
     * 处理器2
     */
    class WebClientHandler extends ChannelInboundHandlerAdapter {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                System.out.println("channelRead");
                System.out.println(msg.toString());
                super.channelRead(ctx, msg);
            }

            @Override
            public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                System.out.println("channelInactive");
                super.channelInactive(ctx);
                reconnect();//自动重连
                if (isconnect()) {
                } else {
                }
            }

            @Override
            public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
                System.out.println("channelRegistered");
                super.channelRegistered(ctx);
            }

            @Override
            public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
                System.out.println("channelUnregistered");
                super.channelUnregistered(ctx);
            }

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                System.out.println("channelActive");
                super.channelActive(ctx);
            }

            @Override
            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                System.out.println("channelReadComplete");
                super.channelReadComplete(ctx);
            }

            @Override
            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                System.out.println("userEventTriggered");
                super.userEventTriggered(ctx, evt);
            }

            @Override
            public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
                System.out.println("channelWritabilityChanged");
                super.channelWritabilityChanged(ctx);
            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                System.out.println("exceptionCaught");
                super.exceptionCaught(ctx, cause);
            }
        }
    }
 