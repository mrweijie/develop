package tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

import static io.netty.handler.codec.http.HttpHeaders.Names.HOST;
import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpHeaders.setContentLength;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_0;

/**
 * 基础的websocket服务处理器（父类）
 *
 * @author IMajes
 */
public class BrowserBaseHandler extends SimpleChannelInboundHandler<Object> {

    private String channelsName;
    private WebSocketServerHandshaker handshaker;//支持webSocket的类
    private static final Logger logger = Logger.getLogger(BrowserBaseHandler.class.getName());

    //构造方法里初始化
    public BrowserBaseHandler(String channelName) {
        this.channelsName = channelName;
    }

    /**
     * 客户端请求服务器打开webSocket
     *
     * @param ctx ChannelHandlerContext对象，本地webSocket通道
     * @param msg 接入的对象：FullHttpRequest（传统http）/WebSocketFrame
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof FullHttpRequest) // 传统的HTTP接入
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        else if (msg instanceof WebSocketFrame) // WebSocket接入
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
    }

    /**
     * 处理传统的http请求,这里应该是请求打开webSocket的时候的
     * @param req FullHttpRequest
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {

        // 如果HTTP解码失败，或者是webSocket请求，返回HTTP异常
        if (!req.getDecoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_0, BAD_REQUEST));//响应返回http版本和状态码，提示出错
            return;
        }
        String local = getWebSocketLocation(req);//得到webSocket的启动路径
        // 构造握手响应返回，本机测试
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(local, null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            //WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    /**
     * 处理webSocket 的请求
     * @param ctx 通道对象
     * @param frame WebSocketFrame
     */
    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否是关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否是Ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 本例程仅支持文本消息，不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
        }
        // 返回应答消息
        String request = ((TextWebSocketFrame) frame).text();
        if (logger.isLoggable(Level.FINE)){
            logger.fine(String.format("%s received %s", ctx.channel(), request));
        }
        //根据情况处理请求数据和返回数据的方法
        try {
            dealRequest(request, ctx);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理前端发过来的消息，被子类调用
     * @param request 请求信息
     * @param ctx 通道对象
     */
    public void dealRequest(String request, ChannelHandlerContext ctx) throws Exception {
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
        // 返回应答给客户端
        if (res.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(),CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
            setContentLength(res, res.content().readableBytes());
        }

        ChannelFuture f = ctx.channel().writeAndFlush(res);
        // 如果是非Keep-Alive，关闭连接
        if (!isKeepAlive(req) || res.getStatus().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 有错误的时候
     * @param ctx 通道对象
     * @param cause 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        //TCPManager.getInstance().clearWebClient();//这里连接了设备的客户端不应该在此处受影响
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    /**
     * 得到webSocket的启动路径
     * @param req 浏览器的请求
     * @return 启动路径
     */
    private String getWebSocketLocation(FullHttpRequest req) {
        String location = req.headers().get(HOST) + "/" + this.channelsName;
        return "ws://" + location;
    }

    /**
     * 浏览器加入连接
     * @param ctx 通道对象
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel inch = ctx.channel();
        WebManager.addChannalGroup(this.channelsName, inch);//管理者添加一个通道
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
    }

    /**
     * 浏览器断开连接
     * @param ctx 通道对象
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Channel inch = ctx.channel();
        WebManager.removeChannelGroup(this.channelsName, inch);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    //通道的可写性被改变
    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

}
