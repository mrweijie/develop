package GUI;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.URI;

public class websocketdemo extends JFrame {

    // 定义组件
    JPanel jpLeft = new JPanel();
    JPanel jpLeftUp = new JPanel();
    JPanel jpLeftDown = new JPanel();

    JTextField ipText = new JTextField("ws://192.168.10.201:50003",20);
    JTextArea sendText = new JTextArea("{\"type\":\"SZM\",\"action\":\"init\",\"model\":[{\"sceneId\":\"2\"}]}",10,25);
    public static JTextArea showText = new JTextArea("",1,1);

    JButton connectButton = new JButton("连接");
    JButton breakButton = new JButton("断开");
    JButton sendButton = new JButton("发送");

    public static Channel channel;
    public static String host;
    public static int port;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        websocketdemo d1 = new websocketdemo();
    }

    // 构造函数
    public websocketdemo() {
        jpLeftUp.add(ipText);
        jpLeftUp.add(connectButton);
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
            }
        });
        jpLeftUp.add(breakButton);
        breakButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destroy();
            }
        });
        breakButton.setEnabled(false);
        jpLeftDown.add(sendText);
        jpLeftDown.add(sendButton);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(channel != null){
                    channel.writeAndFlush(new TextWebSocketFrame(sendText.getText()));

                    showText.append("\r\n"+ host +" :"+ port +"发送数据："+sendText.getText());
                }else{
                    System.out.println("channel 为空");
                }
            }
        });

        jpLeft.add(jpLeftUp);
        jpLeft.add(jpLeftDown);

        this.setLayout(new GridLayout(1, 2));
        this.add(jpLeft);
        this.add(showText);
        JScrollPane scroll = new JScrollPane(showText);
        this.add(scroll);

        this.setSize(1000, 550);
        this.setTitle("Websocket测试");
        this.setLocation(400,250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void connect(){
        try{
            EventLoopGroup group=new NioEventLoopGroup();
            Bootstrap boot=new Bootstrap();
            boot.option(ChannelOption.SO_KEEPALIVE,true)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .option(ChannelOption.SO_BACKLOG,1024*1024*10)
                    .group(group)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline p = socketChannel.pipeline();
                            p.addLast(new ChannelHandler[]{new HttpClientCodec(),
                                    new HttpObjectAggregator(1024*1024*10)});
                            p.addLast("hookedHandler", new ClientHandler());
                        }
                    });
            URI websocketURI = new URI(ipText.getText());
            HttpHeaders httpHeaders = new DefaultHttpHeaders();
            //进行握手
            WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(websocketURI, WebSocketVersion.V13, (String)null, true,httpHeaders);
            channel=boot.connect(websocketURI.getHost(),websocketURI.getPort()).sync().channel();
            ClientHandler handler = (ClientHandler)channel.pipeline().get("hookedHandler");
            handler.setHandshaker(handshaker);
            handshaker.handshake(channel);
            //阻塞等待是否握手成功
            handler.handshakeFuture().sync();

            breakButton.setEnabled(true);
            connectButton.setEnabled(false);
            InetSocketAddress inetsocketaddress = (InetSocketAddress) channel.remoteAddress();//得到ip和端口集成的数据
            host = inetsocketaddress.getAddress().getHostAddress();//这是得到ip
            port = inetsocketaddress.getPort();
            showText.append("\r\n" + host +" :"+ port +" : 连接成功");
        }catch (Exception e){
            showText.append("\r\n"+ host +" :"+ port +"连接错误");
        }finally {

        }
    }

    public void destroy(){
        channel.close();
        showText.append("\r\n"+ host +" :"+ port +" 已关闭 ！");

        breakButton.setEnabled(false);
        connectButton.setEnabled(true);
    }
}

class ClientHandler extends SimpleChannelInboundHandler<Object> {
    WebSocketClientHandshaker handshaker;
    ChannelPromise handshakeFuture;

    public void handlerAdded(ChannelHandlerContext ctx) {
        this.handshakeFuture = ctx.newPromise();
    }

    public WebSocketClientHandshaker getHandshaker() {
        return handshaker;
    }

    public void setHandshaker(WebSocketClientHandshaker handshaker) {
        this.handshaker = handshaker;
    }

    public ChannelPromise getHandshakeFuture() {
        return handshakeFuture;
    }

    public void setHandshakeFuture(ChannelPromise handshakeFuture) {
        this.handshakeFuture = handshakeFuture;
    }

    public ChannelFuture handshakeFuture() {
        return this.handshakeFuture;
    }

    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("channelRead0  " + this.handshaker.isHandshakeComplete());
        Channel ch = ctx.channel();
        FullHttpResponse response;
        if (!this.handshaker.isHandshakeComplete()) {
            try {
                response = (FullHttpResponse) msg;
                //握手协议返回，设置结束握手
                this.handshaker.finishHandshake(ch, response);
                //设置成功
                this.handshakeFuture.setSuccess();
                System.out.println("WebSocket Client connected! response headers[sec-websocket-extensions]:{}" + response.headers());
            } catch (WebSocketHandshakeException var7) {
                FullHttpResponse res = (FullHttpResponse) msg;
                String errorMsg = String.format("WebSocket Client failed to connect,status:%s,reason:%s", res.getStatus(), res.content().toString(CharsetUtil.UTF_8));
                this.handshakeFuture.setFailure(new Exception(errorMsg));
            }
        } else if (msg instanceof FullHttpResponse) {
            response = (FullHttpResponse) msg;
            //this.listener.onFail(response.status().code(), response.content().toString(CharsetUtil.UTF_8));
            throw new IllegalStateException("Unexpected FullHttpResponse (getStatus=" + response.getStatus() + ", content=" + response.content().toString(CharsetUtil.UTF_8) + ')');
        } else {
            WebSocketFrame frame = (WebSocketFrame) msg;
            if (frame instanceof TextWebSocketFrame) {
                TextWebSocketFrame textFrame = (TextWebSocketFrame) frame;
                websocketdemo.showText.append("\r\n"+ websocketdemo.host +" :"+ websocketdemo.port +"接收到数据："+ textFrame.text());
                //this.listener.onMessage(textFrame.text());
//                System.out.println("TextWebSocketFrame");
            } else if (frame instanceof BinaryWebSocketFrame) {
                BinaryWebSocketFrame binFrame = (BinaryWebSocketFrame) frame;
                System.out.println("BinaryWebSocketFrame");
            } else if (frame instanceof PongWebSocketFrame) {
                System.out.println("WebSocket Client received pong");
            } else if (frame instanceof CloseWebSocketFrame) {
                System.out.println("receive close frame");
                //this.listener.onClose(((CloseWebSocketFrame)frame).statusCode(), ((CloseWebSocketFrame)frame).reasonText());
                ch.close();
            }

        }
    }
}
