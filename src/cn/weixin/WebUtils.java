//package cn.weixin;
//
//import net.sf.json.JSONObject;
//import org.hibernate.Criteria;
//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.jdom2.input.SAXBuilder;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.PrintWriter;
//import java.util.*;
//
//public class WebUtils {
////    /**
////     * @Title: getIpAddress
////     * @Description: 获取客户端真实IP地址
////     * @author yihj
////     * @param @param request
////     * @param @param response
////     * @param @return    参数
////     * @return String    返回类型
////     * @throws
////     */
////    public static String getIpAddress(HttpServletRequest request) {
////        // 避免反向代理不能获取真实地址, 取X-Forwarded-For中第一个非unknown的有效IP字符串
////        String ip = request.getHeader("x-forwarded-for");
////        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
////            ip = request.getHeader("Proxy-Client-IP");
////        }
////        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
////            ip = request.getHeader("WL-Proxy-Client-IP");
////        }
////        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
////            ip = request.getRemoteAddr();
////        }
////        return ip;
////    }
//
//    public void payCallback(HttpServletRequest request,HttpServletResponse response) {
//        System.out.println("微信回调接口方法 start");
//        System.out.println("微信回调接口 操作逻辑 start");
//        String inputLine = "";
//        String notityXml = "";
//        try {
//            while((inputLine = request.getReader().readLine()) != null){
//                notityXml += inputLine;
//            }
//            //关闭流
//            request.getReader().close();
//            System.out.println("微信回调内容信息："+notityXml);
//            //解析成Map
//            Map<String,String> map = doXMLParse(notityXml);
//            //判断 支付是否成功
//            if("SUCCESS".equals(map.get("result_code"))){
//                System.out.println("微信回调返回是否支付成功：是");
//                //获得 返回的商户订单号
//                String outTradeNo = map.get("out_trade_no");
//                System.out.println("微信回调返回商户订单号："+outTradeNo);
//                //访问DB
//                WechatAppletGolfPayInfo payInfo = appletGolfPayInfoMapper.selectByPrimaryKey(outTradeNo);
//                System.out.println("微信回调 根据订单号查询订单状态："+payInfo.getPayStatus());
//                if("0".equals(payInfo.getPayStatus())){
//                    //修改支付状态
//                    payInfo.setPayStatus("1");
//                    //更新Bean
//                    int sqlRow = appletGolfPayInfoMapper.updateByPrimaryKey(payInfo);
//                    //判断 是否更新成功
//                    if(sqlRow == 1){
//                        System.out.println("微信回调  订单号："+outTradeNo +",修改状态成功");
//                        //封装 返回值
//                        StringBuffer buffer = new StringBuffer();
//                        buffer.append("<xml>");
//                        buffer.append("<return_code>SUCCESS</return_code>");
//                        buffer.append("<return_msg>OK</return_msg>");
//                        buffer.append("</xml>");
//
//                        //给微信服务器返回 成功标示 否则会一直询问 咱们服务器 是否回调成功
//                        PrintWriter writer = response.getWriter();
//                        //返回
//                        writer.print(buffer.toString());
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
//     * @param strxml
//     * @return
//     * throws JDOMException
//     * throws IOException
//     */
//    @SuppressWarnings("rawtypes")
//    private Map<String,String> doXMLParse(String strxml) throws Exception {
//        if(null == strxml || "".equals(strxml)) {
//            return null;
//        }
//
//        Map<String,String> m = new HashMap<String,String>();
//        InputStream in = String2Inputstream(strxml);
//        SAXBuilder builder = new SAXBuilder();
//        Document doc = builder.build(in);
//        Element root = doc.getRootElement();
//        List list = root.getChildren();
//        Iterator it = list.iterator();
//        while(it.hasNext()) {
//            Element e = (Element) it.next();
//            String k = e.getName();
//            String v = "";
//            List children = e.getChildren();
//            if(children.isEmpty()) {
//                v = e.getTextNormalize();
//            } else {
//                v = getChildrenText(children);
//            }
//
//            m.put(k, v);
//        }
//
//        //关闭流
//        in.close();
//
//        return m;
//    }
//
//
//    private  InputStream String2Inputstream(String str) {
//        return new ByteArrayInputStream(str.getBytes());
//    }
//
//
//    /**
//     * 获取子结点的xml
//     * @param children
//     * @return String
//     */
//    @SuppressWarnings("rawtypes")
//    private static String getChildrenText(List children) {
//        StringBuffer sb = new StringBuffer();
//        if(!children.isEmpty()) {
//            Iterator it = children.iterator();
//            while(it.hasNext()) {
//                Element e = (Element) it.next();
//                String name = e.getName();
//                String value = e.getTextNormalize();
//                List list = e.getChildren();
//                sb.append("<" + name + ">");
//                if(!list.isEmpty()) {
//                    sb.append(getChildrenText(list));
//                }
//                sb.append(value);
//                sb.append("</" + name + ">");
//            }
//        }
//
//        return sb.toString();
//    }
//
//    public JSONObject createUnifiedOrder(HttpServletRequest request, HttpServletResponse response) {
//        System.out.println("微信 统一下单 接口调用");
//        //设置最终返回对象
//        JSONObject resultJson = new JSONObject();
//        //创建条件
//        Criteria criteria = new Criteria();
//
//        //接受参数(金额)
//        String amount = request.getParameter("amount");
//        //接受参数(openid)
//        String openid = request.getParameter("openid");
//        //接口调用总金额单位为分换算一下(测试金额改成1,单位为分则是0.01,根据自己业务场景判断是转换成float类型还是int类型)
//        //String amountFen = Integer.valueOf((Integer.parseInt(amount)*100)).toString();
//        //String amountFen = Float.valueOf((Float.parseFloat(amount)*100)).toString();
//        String amountFen = "1";
//        //创建hashmap(用户获得签名)
//        SortedMap<String, String> paraMap = new TreeMap<String, String>();
//        //设置body变量 (支付成功显示在微信支付 商品详情中)
//        String body = "啦啦啦测试";
//        //设置随机字符串
//        String nonceStr = Utils.getUUIDString().replaceAll("-", "");
//        //设置商户订单号
//        String outTradeNo = Utils.getUUIDString().replaceAll("-", "");
//
//
//        //设置请求参数(小程序ID)
//        paraMap.put("appid", APPLYID);
//        //设置请求参数(商户号)
//        paraMap.put("mch_id", MCHID);
//        //设置请求参数(随机字符串)
//        paraMap.put("nonce_str", nonceStr);
//        //设置请求参数(商品描述)
//        paraMap.put("body", body);
//        //设置请求参数(商户订单号)
//        paraMap.put("out_trade_no", outTradeNo);
//        //设置请求参数(总金额)
//        paraMap.put("total_fee", amountFen);
//        //设置请求参数(终端IP)
//        paraMap.put("spbill_create_ip", WebUtils.getIpAddress(request, response));
//        //设置请求参数(通知地址)
//        paraMap.put("notify_url", WebUtils.getBasePath()+"wechat/wechatAppletGolf/payCallback");
//        //设置请求参数(交易类型)
//        paraMap.put("trade_type", "JSAPI");
//        //设置请求参数(openid)(在接口文档中 该参数 是否必填项 但是一定要注意 如果交易类型设置成'JSAPI'则必须传入openid)
//        paraMap.put("openid", openid);
//        //调用逻辑传入参数按照字段名的 ASCII 码从小到大排序（字典序）
//        String stringA = formatUrlMap(paraMap, false, false);
//        //第二步，在stringA最后拼接上key得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue。(签名)
//        String sign = MD5Util.MD5(stringA+"&key="+KEY).toUpperCase();
//        //将参数 编写XML格式
//        StringBuffer paramBuffer = new StringBuffer();
//        paramBuffer.append("<xml>");
//        paramBuffer.append("<appid>"+APPLYID+"</appid>");
//        paramBuffer.append("<mch_id>"+MCHID+"</mch_id>");
//        paramBuffer.append("<nonce_str>"+paraMap.get("nonce_str")+"</nonce_str>");
//        paramBuffer.append("<sign>"+sign+"</sign>");
//        paramBuffer.append("<body>"+body+"</body>");
//        paramBuffer.append("<out_trade_no>"+paraMap.get("out_trade_no")+"</out_trade_no>");
//        paramBuffer.append("<total_fee>"+paraMap.get("total_fee")+"</total_fee>");
//        paramBuffer.append("<spbill_create_ip>"+paraMap.get("spbill_create_ip")+"</spbill_create_ip>");
//        paramBuffer.append("<notify_url>"+paraMap.get("notify_url")+"</notify_url>");
//        paramBuffer.append("<trade_type>"+paraMap.get("trade_type")+"</trade_type>");
//        paramBuffer.append("<openid>"+paraMap.get("openid")+"</openid>");
//        paramBuffer.append("</xml>");
//
//        try {
//            //发送请求(POST)(获得数据包ID)(这有个注意的地方 如果不转码成ISO8859-1则会告诉你body不是UTF8编码 就算你改成UTF8编码也一样不好使 所以修改成ISO8859-1)
//            Map<String,String> map = doXMLParse(getRemotePortData(URL, new String(paramBuffer.toString().getBytes(), "ISO8859-1")));
//            //应该创建 支付表数据
//            if(map!=null){
//                //清空
//                criteria.clear();
//                //设置openId条件
//                criteria.put("openId", openid);
//                //获取数据
//                List<WechatAppletGolfPayInfo> payInfoList = appletGolfPayInfoMapper.selectByExample(criteria);
//                //如果等于空 则证明是第一次支付
//                if(CollectionUtils.isEmpty(payInfoList)){
//                    //创建支付信息对象
//                    WechatAppletGolfPayInfo appletGolfPayInfo = new  WechatAppletGolfPayInfo();
//                    //设置主键
//                    appletGolfPayInfo.setPayId(outTradeNo);
//                    //设置openid
//                    appletGolfPayInfo.setOpenId(openid);
//                    //设置金额
//                    appletGolfPayInfo.setAmount(Long.valueOf(amount));
//                    //设置支付状态
//                    appletGolfPayInfo.setPayStatus("0");
//                    //插入Dao
//                    int sqlRow = appletGolfPayInfoMapper.insert(appletGolfPayInfo);
//                    //判断
//                    if(sqlRow == 1){
//                        logger.info("微信 统一下单 接口调用成功 并且新增支付信息成功");
//                        resultJson.put("prepayId", map.get("prepay_id"));
//                        resultJson.put("outTradeNo", paraMap.get("out_trade_no"));
//                        return resultJson;
//                    }
//                }else{
//                    //判断 是否等于一条
//                    if(payInfoList.size() == 1){
//                        //获取 需要更新数据
//                        WechatAppletGolfPayInfo wechatAppletGolfPayInfo = payInfoList.get(0);
//                        //更新 该条的 金额
//                        wechatAppletGolfPayInfo.setAmount(Long.valueOf(amount));
//                        //更新Dao
//                        int sqlRow = appletGolfPayInfoMapper.updateByPrimaryKey(wechatAppletGolfPayInfo);
//                        //判断
//                        if(sqlRow == 1){
//                            logger.info("微信 统一下单 接口调用成功 修改支付信息成功");
//                            resultJson.put("prepayId", map.get("prepay_id"));
//                            resultJson.put("outTradeNo", paraMap.get("out_trade_no"));
//                            return resultJson;
//                        }
//                    }
//                }
//            }
//            //将 数据包ID 返回
//
//            System.out.println(map);
//        } catch (UnsupportedEncodingException e) {
//            logger.info("微信 统一下单 异常："+e.getMessage());
//            e.printStackTrace();
//        } catch (Exception e) {
//            logger.info("微信 统一下单 异常："+e.getMessage());
//            e.printStackTrace();
//        }
//        logger.info("微信 统一下单 失败");
//        return resultJson;
//    }
//}
