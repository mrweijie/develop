package tcp;

import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * webSocket服务器处理前端信息的处理器
 */
public class BrowserHandler extends BrowserBaseHandler {
    public static boolean connectionState=true;//ip地址连接的标记(true表示能建立连接,false表示不能连接)
    private String channelsName;//通道名字

    //构造方法
    public BrowserHandler(String channelName) {
        super(channelName);
        this.channelsName = channelName;
    }

    //处理请求
    @Override
    public void dealRequest(String request, ChannelHandlerContext ctx) throws Exception {
            System.out.println("前端发送的数据是：" + request + ",通道名称是：" + channelsName);
    }
}
