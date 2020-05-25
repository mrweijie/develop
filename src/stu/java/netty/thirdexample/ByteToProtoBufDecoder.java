package stu.java.netty.thirdexample;

import GUI.showpicdemo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class ByteToProtoBufDecoder extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new byte[((ByteBuf)msg).readableBytes()].length);
//        if (msg instanceof ByteBuf) {
//            showpicdemo.change(new byte[((ByteBuf)msg).readableBytes()]);
//        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }
}
