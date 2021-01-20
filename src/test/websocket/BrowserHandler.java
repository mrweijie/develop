package test.websocket;


import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * webSocket服务器处理前端信息的处理器
 */
public class BrowserHandler extends BrowserBaseHandler {
    public static boolean connectionState = true;//ip地址连接的标记(true表示能建立连接,false表示不能连接)
    private String channelsName;//通道名字

    //构造方法
    public BrowserHandler(String channelName) {
        super(channelName);
        this.channelsName = channelName;
    }

    //处理请求
    @Override
    public void dealRequest(String request, ChannelHandlerContext ctx) throws Exception {
        System.out.println("前端发来的数据是：" + request.replace("\r","").replace("\n","") + ",通道名称是：" + channelsName);
        if (!request.contains("type") && !request.contains("action") && !request.contains("model")) {
            return;
        }
//        if(Config.Frequency_TIMES != -1){
//            Config.Frequency_TIMES += 1;
//        }
//        WebAgreement agrt = new Gson().fromJson(request, WebAgreement.class);
//        String type = agrt.getType(), action = agrt.getAction();
//        List model = agrt.getModel();
//        switch (type) {
//            case Constant.SZM:
//                new WebLightHandler(ctx).deal(action, model);
//                break;
//            case Constant.AUDIO:
//                WebDBHandler.getWebDBHandler().deal(action, model);
//                break;
//            case Constant.AV1000M2:
//                WebAV1000M2VideoHandler.getInstence().deal(action, model);
//                break;
//            case Constant.AV3000MINI:
////                    WebAV3000MiniVideoHandler.getInstence().deal(action, model, ctx);
//                SingletonFactory.getInstance(WebAV3000MiniVideoHandler.class).deal(action, model, ctx);
//                break;
//            case Constant.VIDEO://拼接屏视频操作
//                SingletonFactory.getInstance(WebVideoHandler.class).handleVideo(action, model, ctx);
//                break;
//            case Constant.audio3200:
//                SingletonFactory.getInstance(DevAudio3200Handler.class).deal(action, model);
//                break;
//            case Constant.PDU:
//                SingletonFactory.getInstance(PDUHandler.class).deal(action, model);
//                break;
//            case Constant.AV3200PRE://开启预览
//                SingletonFactory.getInstance(WebVideoHandler.class).init();
//                break;
//            case Constant.checkSerial:
//                SingletonFactory.getInstance(CheckSerialHandler.class).deal(action, model);
//                break;
//        }
    }
}
