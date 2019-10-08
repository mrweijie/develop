package stu.java.tcp;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * 所有的服务端socket的父类
 * ChannelFutureListener，为特定的事项添加监听器。当事件发生时，会出发监听器指定相应的动作
 * @author IMajes
 */
public abstract class BaseServer extends ChannelInitializer<SocketChannel> {

    /**
     *服务绑定的端口
     */
    public int port;
    /**
     * ChannelFuture的作用是用来保存Channel异步操作的结果
     */
    public ChannelFuture fu;

    /**
     * 关闭websocket的服务<br>
     * 由子类实现
     */
    public abstract void shutDownServer();

    /**
     * 初始化channel<br>
     * 由子类实现该方法
     * @param socketChannel
     */
    @Override
    public abstract void initChannel(SocketChannel socketChannel);

    /**
     * 重启websocket的服务
     * @throws InterruptedException
     */
    public void restartServer() throws InterruptedException {
        shutDownServer();
        run(port);
    };

    /**
     * 开启websocket服务
     * @param port 传入要开启的端口
     * @throws InterruptedException
     */
    public void run(int port) throws InterruptedException {
        this.port=port;
        if ((fu != null) && fu.channel().isOpen()){
            shutDownServer();
        }
    }

    /**
     * 服务是否已经开启
    @return
     */
    public boolean isRunning(){
        return (fu.channel().isOpen() && fu.channel().isWritable());
    }

}
