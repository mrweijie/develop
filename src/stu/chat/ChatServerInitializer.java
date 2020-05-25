package stu.chat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import static io.netty.util.CharsetUtil.UTF_8;

public class  ChatServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline p = socketChannel.pipeline();
//        p.addLast(new DelimiterBasedFrameDecoder(100,Delimiters.lineDelimiter()));
        p.addLast(new StringDecoder(UTF_8));
        p.addLast(new StringEncoder(UTF_8));
        p.addLast(new ChatServerinHandle());

    }
}
