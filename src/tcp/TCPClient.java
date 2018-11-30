package tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
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
        b.option(ChannelOption.SO_TIMEOUT, 5000);
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                //连接回调
                ch.pipeline().addLast(new FeedbackInboundHandler());
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
            channel = future.awaitUninterruptibly().channel();
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
            InetSocketAddress inetsocketaddress = (InetSocketAddress) ctx.channel().remoteAddress();//得到ip和端口集成的数据
            String host = inetsocketaddress.getAddress().getHostAddress();//这是得到ip
            int port = inetsocketaddress.getPort();
            String host_port = host + "#" + port;//组合地址

            //AV3200机器的预览服务器返回了图片
            if (true) {
                ChannelGroup channels = WebManager.getChannels("3200");
                if (channels == null||channels.size()<=0) {
//                    和前端的连接全部断开后不应该继续和机器拿数据，关闭该通道
                    close();
                    return;
                }
                byte[] recvbyte = new byte[byteBuf.readableBytes()];
                byteBuf.readBytes(recvbyte);
                byteBuf.clear();
                byteBuf.resetReaderIndex();
                byte[] image = analayImageDealPacketSplicing2(recvbyte);
//                ReferenceCountUtil.release(byteBuf);
                if (image != null) {
                    ByteBuf byteBuf2 = Unpooled.wrappedBuffer(image);
                    channels.writeAndFlush(new BinaryWebSocketFrame(byteBuf2));
                    //new一个文件对象用来保存图片，默认保存当前工程根目录
                    File imageFile = new File("E:/weijie/1122.jpg");
                    if(!imageFile.exists()){
                        byte2image(image,"E:/weijie/1122.jpg");
                        System.out.println("输出了1122");
                    }else {
                        File imageFile2 = new File("E:/weijie/112233.jpg");
                        if(!imageFile2.exists()){
                            byte2image(image,"E:/weijie/112233.jpg");
                            System.out.println("输出了112233");
                        }else {
                            File imageFile3 = new File("E:/weijie/112244.jpg");
                            if(!imageFile3.exists()){
                                byte2image(image,"E:/weijie/112244.jpg");
                                System.out.println("输出了112244");
                            }
                        }
                    }
                }
            }
        }


        //byte数组到图片
        // byte[]图片的字节数组   ，String 保存的地址。
        public void byte2image(byte[] data, String path) {
            if (data.length < 3 || path.equals("")) return;
            try {
                FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
                imageOutput.write(data, 0, data.length);
                imageOutput.close();
//                System.out.println("Make Picture success,Please find image in " + path);
            } catch (Exception ex) {
//                System.out.println("Exception: " + ex);
                ex.printStackTrace();
            }
        }

        volatile byte[] contain = new byte[1024 * 1024 * 5];//声明一个装分段发过来的数据的容器要足够大
        Integer conSize = 0;//容器中数据的真实长度
        boolean isHasHead = false;//看看容器是否已经装了开头了

        int picsize = 0;


        /**
         * 3200图片处理 大部分是粘包的数据，拆包拼包
         */
        private synchronized byte[] analayImageDealPacketSplicing2(byte[] temp) {
            int tempLength = temp.length;
            //判断数据开头第一个int也就是数据第一位和第二位是否为协议头“43 34”，和第二个int也就是数据第五位是否是第一通道“0”
            if (temp[0] == (byte) 0x43 && temp[1] == (byte) 0x34 && temp[4] == (byte) 0x01) {

                //获取图片大小（第五个int，也就是数据的第20位开始）
                picsize = MyLog.toInt(new byte[]{temp[23], temp[22], temp[21], temp[20]});//将四位十六进制的数转化为int类型。
                for (int i = 0; i < tempLength - 24; i++) {
                    contain[i] = temp[i + 24];
                }
                conSize = (tempLength - 24);
                picsize -= (tempLength - 24);
            } else if (picsize != 0) { //非协议头
                if (tempLength < picsize) {  //数据包的小于图片的大小（picsize）累加
                    for (int i = 0; i < tempLength; i++) {
                        contain[conSize + i] = temp[i];
                    }
                    conSize += tempLength;
                    picsize -= tempLength;
                } else {     //数据包大于图片大小（picsize）处理
                    for (int i = 0; i < picsize; i++) {
                        contain[conSize + i] = temp[i];
                    }
                    conSize += picsize;
                    byte[] back = subBytes(contain, 0, conSize);
                    picsize = 0;
                    return back;
                }
            }
            return null;

        }

        /**
         * 3000图片处理  大部分是粘包的数据，拆包拼包
         */
        private synchronized byte[] analayImageDealPacketSplicing(byte[] temp) {

            int tempLength = temp.length;
            byte[] containImg = new byte[tempLength];
            ///image>
            //接收的是完整的开头(<image,,,,,),, {  } ,/image>
            if ((temp[0] == '<' && temp[1] == 'i' && temp[2] == 'm' && temp[3] == 'a' && temp[4] == 'g' && temp[5] == 'e') &&
                    (temp[tempLength - 7] == '/' && temp[tempLength - 6] == 'i' && temp[tempLength - 5] == 'm' && temp[tempLength - 4] == 'a' && temp[tempLength - 3] == 'g' && temp[tempLength - 2] == 'e') && temp[tempLength - 1] == '>') {
                for (int i = 0; i < tempLength; i++) {
                    containImg[i] = temp[i];
                }
                return containImg;
            }

            if (temp[0] == '<' && temp[1] == 'i' && temp[2] == 'm' && temp[3] == 'a' && temp[4] == 'g' && temp[5] == 'e') {
                for (int i = 0; i < tempLength; i++) {
                    contain[i] = temp[i];
                }
                conSize = tempLength;//接收了开头，数据真实长度是当前接收的数据的长度
                isHasHead = true;//记录已经接收了开头
            } else //中间或者开头
            {
                // 找到这个/image>代表是结尾，这时需要记录结尾的索引，如果这一段数据整段不存在这个/image>则表示这段数据的最末尾是图片的中间数据，这是不完整的数据
                int endIndex = -1;//如果是结尾，这里记录了结尾尖括号的索引
                for (int k = tempLength - 1; k >= 6; k--) {
                    if (temp[k - 6] == '/' && temp[k - 5] == 'i' &&
                            temp[k - 4] == 'm' && temp[k - 3] == 'a' &&
                            temp[k - 2] == 'g' && temp[k - 1] == 'e' &&
                            temp[k] == '>') {
                        endIndex = k;//记录结尾符号的索引
                        //System.out.println("找到了结尾/image>，endIndex = " + endIndex);
                        break;
                    }
                }


                if (endIndex != -1) //接收的是结尾部分
                {
                    if (endIndex == tempLength - 1) //接收的是正确的结尾，没有粘包
                    {
                        if (isHasHead) {
                            for (int i = 0; i < tempLength; i++) {
                                contain[conSize + i] = temp[i];
                            }
                            conSize += tempLength;
                        }
                        byte[] bs = subBytes(contain, 0, conSize);
                        //System.out.println("---正确结尾---包长度：" + conSize);
                        conSize = 0;
                        isHasHead = false;

                        return bs;
                    } else //接收的是粘包的结尾
                    {
                        if (isHasHead) {
                            for (int i = 0; i <= endIndex; i++) {
                                contain[conSize + i] = temp[i];
                            }
                            conSize += (endIndex + 1);

                            byte[] bs = subBytes(contain, 0, conSize);
                            //System.out.print("---粘包结尾---包长度：" +conSize);

                            for (int g = endIndex + 1; g < tempLength; g++) {
                                contain[g - endIndex - 1] = temp[g];
                            }
                            conSize = tempLength - endIndex - 1;
                            isHasHead = true;
                            //System.out.println(" 粘包长度：" +conSize);
                            return bs;
                        }
                    }
                } else //接收的是中间
                {
                    if (isHasHead) {
                        for (int i = 0; i < tempLength; i++) {
                            contain[conSize + i] = temp[i];
                        }
                        conSize += tempLength;
                    }
                }

            }

            return null;

        }

        /**
         * 传入一个byte数组，从中截取出一段数据复制到新的byte数组中，并返回新的数组
         *
         * @param src   从中截取的源数组
         * @param begin 截取的起始索引（0~length）
         * @param count 需要截取出来的数据的长度
         * @return 装了截取的数据的新数组
         */
        public byte[] subBytes(byte[] src, int begin, int count) {
            byte[] bs = new byte[count];
            for (int i = begin; i < begin + count; i++) bs[i - begin] = src[i];

            return bs;
        }

        /**
         * 传入一个数组，找出图片的开头标志的索引，并返回该索引
         */
        private int judegeImage(byte[] test) {
            int pos = -1;
            for (int i = 0; i < test.length; i++) {
                if (test[i] == '<' &&
                        i < test.length - 6 &&
                        test[i + 1] == 'i' &&
                        test[i + 2] == 'm' &&
                        test[i + 3] == 'a' &&
                        test[i + 4] == 'g' &&
                        test[i + 5] == 'e') {
                    pos = i;
                    break;
                }
            }
            return pos;
        }

        /**
         * 合并bytes
         */
        public byte[] mergeBytes(byte[] bytes0, byte[] bytes1) {
            byte[] bs = new byte[bytes0.length + bytes1.length];
            int i = 0;
            for (; i < bytes0.length; ) {
                bs[i] = bytes0[i];
                i++;
            }
            for (int j = 0; j < bytes1.length; j++) {
                bs[j + i] = bytes1[j];
            }
            return bs;
        }
    }

    /**
     * 处理器2
     */
    class WebClientHandler extends ChannelInboundHandlerAdapter {
        int a = 0;

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            super.channelRead(ctx, msg);

        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            super.channelInactive(ctx);
            reconnect();//自动重连
            if (isconnect()) {
            } else {
            }
        }
    }
}
 