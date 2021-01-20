package test.websocket;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * websockeServer管理类
 */
public class WebManager {
    /**
     * 通道组 打开一个网页就会多一个连接通道
     * 服务器channalsName/对应的链接通道组
     */
    volatile static Map<String, ChannelGroup> channelGroupMap = new HashMap<>();
    /**
     * 记录要单独返回数据的通道，比如，AV3000Mini的场景功能用到
     */
    volatile static Map<String,Channel> channelMap = new HashMap<>();
    /**
     * 服务端组
     */
    volatile static Map<String, ServerBase> map = new HashMap<>();
    public static NioEventLoopGroup workerGroup = new NioEventLoopGroup();
    public static NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
    public static void addChannel(String name,Channel ch){
        channelMap.put(name,ch);
    }
    public static void reChannel(String name){
        channelMap.remove(name);
    }
    public static Channel getChannel(String name){
        return channelMap.get(name);
    }
    /**
     * 得到某个通道组
     * @param name 通道组(value)的key
     * @return 返回相应的通道组
     */
    public synchronized static ChannelGroup getChannels(String name) {
        ChannelGroup group = channelGroupMap.get(name);
        if(group == null){
            return null;
        }
        return group;
    }
    /**
     * 删除一个通道组
     */
    public static void clearChannels(String name) {
        channelGroupMap.get(name).close();
        channelGroupMap.remove(name);
    }
    /**
     * 删除所有通道组
     */
    public static void clearAllChannels() {
        for (Map.Entry<String, ChannelGroup> entity : channelGroupMap.entrySet()) {
            entity.getValue().close();
        }
    }
    /**
     * webSocket添加一个连接通道，当打开一个网页的时候就添加一个通道（连接）
     * @param name 用来找通道组
     * @param channel 要添加的通道
     */
    public synchronized  static void addChannalGroup(String name, Channel channel) {
        ChannelGroup channels = channelGroupMap.get(name);
        if (channels == null) {
            channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);//创建一个通道组
            channelGroupMap.put(name, channels);
        }
        channels.add(channel);
    }
    /**
     * webSocket删除一个连接通道
     * @param name 用于找通道组
     * @param channel 要删除的通道
     */
    public static void removeChannelGroup(String name, Channel channel) {
        if (channel.isWritable() && channel.isOpen()) {
            channel.closeFuture();
        }
        ChannelGroup channels = channelGroupMap.get(name);
        if (channels.contains(channel)) {
            channels.remove(channel);
        }
    }
    /**
     * 得到连接浏览器的服务端实例
     * @return 浏览器的服务端
     */
    public static synchronized BrowserServer getWebServerByName(String chName) {
        BrowserServer bsServer = (BrowserServer) map.get(chName);
        if (bsServer == null) {
            bsServer = new BrowserServer(chName);
            map.put(chName, bsServer);
        }
        return bsServer;
    }
    /**
     * 关闭webSocket
     */
    public static void closeAllWebSocket() {
        for (Map.Entry<String, ServerBase> entity : map.entrySet()) {
            ServerBase server = entity.getValue();
            server.shutDownServer();
        }
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
}