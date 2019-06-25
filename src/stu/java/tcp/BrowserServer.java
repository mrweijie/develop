package stu.java.tcp;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * WebServer服务端
 * @author IMajes
 * @date 2017-3-15
 */
public class BrowserServer extends ServerBase {

    public String channelsName;

    public BrowserServer(String channelsName){
        this.channelsName = channelsName;
    }

    /**
     * 初始化通道
     */
    @Override
    public void initChannel(SocketChannel socketChannel) {

        //数据接受后回调socketChannel.pipeline().addLast(new WebByteDecoder());
        ChannelPipeline pipeline = socketChannel.pipeline();
        //HttpServerCodec   请求(应答消息)--->编码(解码)--->HTTP消息
        pipeline.addLast("http-codec",new HttpServerCodec());
        //HttpObjectAggregator将HTTP消息的多个部分组合成一条完整的HTTP消息;
        pipeline.addLast("aggregator",new HttpObjectAggregator(6553600));
        //ChunkedWriteHandler的主要作用是支持异步发送大的码流(例如大文件传输),但不占用过多的内存,防止JAVA内存溢出
        pipeline.addLast("http-chunked",new ChunkedWriteHandler());
        //BrowserHandler 自定义业务处理方式，传入当前服务器的通道名称
        pipeline.addLast("handler",new BrowserHandler(channelsName));

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }
    /**
     * 开启webSocket服务
     * @param port 传入要开启的端口
     * @throws InterruptedException
     */
    @Override
    public void run(int port) throws InterruptedException {
        super.run(port);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(WebManager.bossGroup, WebManager.workerGroup)
                .channel(NioServerSocketChannel.class) // (3)
                .childHandler(this)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        fu = serverBootstrap.bind(port).sync();//这句是必须的
        fu.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
    }

    /**
     * 关闭服务
     */
    @Override
    public void shutDownServer() {
        if (fu.channel().isOpen() && fu.channel().isWritable())
            fu.channel().closeFuture();//这句是必须的
    }
}
