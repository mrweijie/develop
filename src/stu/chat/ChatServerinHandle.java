package stu.chat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServerinHandle extends SimpleChannelInboundHandler<ByteBuf> {

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf){
        Channel channel = ctx.channel();
        byte[] byteArray = new byte[byteBuf.capacity()];
        byteBuf.readBytes(byteArray);
        String result = new String(byteArray);
        System.out.println(ctx.channel().remoteAddress() + ":" +result);
        channelGroup.writeAndFlush(channel.remoteAddress()+" : "+result);
        channelGroup.flush();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx){
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(channel.remoteAddress()+"加入了\n");
        channelGroup.add(channel);
        try {
            super.handlerAdded(ctx);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(channel.remoteAddress()+"走了\n");
        channelGroup.remove(channel);
        try {
            super.handlerRemoved(ctx);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(channel.remoteAddress()+"可以发数据了\n");
        channelGroup.flush();
        try {
            super.channelInactive(ctx);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
