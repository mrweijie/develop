package cn.weixin;

import cn.weixin2.sdk.WXPayUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

import static cn.weixin2.sdk.WXPayUtil.MD5;

public class weixin {


    /**
     *
     * 方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串<br>
     * 实现步骤: <br>
     *
     * @param paraMap   要排序的Map对象
     * @param urlEncode   是否需要URLENCODE
     * @param keyToLower    是否需要将Key转换为全小写
     *            true:key转化成小写，false:不转化
     * @return
     */
    private static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower){
        String buff = "";
        Map<String, String> tmpMap = paraMap;
        try
        {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>()
            {
                @Override
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2)
                {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds)
            {
                if (StringUtils.isNotBlank(item.getKey()))
                {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (urlEncode)
                    {
                        val = URLEncoder.encode(val, "utf-8");
                    }
                    if (keyToLower)
                    {
                        buf.append(key.toLowerCase() + "=" + val);
                    } else
                    {
                        buf.append(key + "=" + val);
                    }
                    buf.append("&");
                }

            }
            buff = buf.toString();
            if (buff.isEmpty() == false)
            {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e)
        {
            return null;
        }
        return buff;
    }

    /**
     * 方法名: getRemotePortData
     * 描述: 发送远程请求 获得代码示例
     * 参数：  @param urls 访问路径
     * 参数：  @param param 访问参数-字符串拼接格式, 例：port_d=10002&port_g=10007&country_a=
     * 创建人: Xia ZhengWei
     * 创建时间: 2017年3月6日 下午3:20:32
     * 版本号: v1.0
     * 返回类型: String
     */
    private String getRemotePortData(String urls, String param){
        System.out.println("港距查询抓取数据----开始抓取外网港距数据");
        try {
            URL url = new URL(urls);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间
            conn.setConnectTimeout(30000);
            // 设置读取超时时间
            conn.setReadTimeout(30000);
            conn.setRequestMethod("POST");
            if(StringUtils.isNotBlank(param)) {
                conn.setRequestProperty("Origin", "https://sirius.searates.com");// 主要参数
                conn.setRequestProperty("Referer", "https://sirius.searates.com/cn/port?A=ChIJP1j2OhRahjURNsllbOuKc3Y&D=567&G=16959&shipment=1&container=20st&weight=1&product=0&request=&weightcargo=1&");
                conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");// 主要参数
            }
            // 需要输出
            conn.setDoInput(true);
            // 需要输入
            conn.setDoOutput(true);
            // 设置是否使用缓存
            conn.setUseCaches(false);
            // 设置请求属性
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            conn.setRequestProperty("Charset", "UTF-8");

            if(StringUtils.isNotBlank(param)) {
                // 建立输入流，向指向的URL传入参数
                DataOutputStream dos=new DataOutputStream(conn.getOutputStream());
                dos.writeBytes(param);
                dos.flush();
                dos.close();
            }
            // 输出返回结果
            InputStream input = conn.getInputStream();
            int resLen =0;
            byte[] res = new byte[1024];
            StringBuilder sb=new StringBuilder();
            while((resLen=input.read(res))!=-1){
                sb.append(new String(res, 0, resLen));
            }
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("港距查询抓取数据----抓取外网港距数据发生异常：" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("港距查询抓取数据----抓取外网港距数据发生异常：" + e.getMessage());
        }
        System.out.println("港距查询抓取数据----抓取外网港距数据失败, 返回空字符串");
        return "";
    }

    public JSONObject generateSignature(HttpServletRequest request,
                                        HttpServletResponse response) {
        System.out.println("微信 支付接口生成签名 方法开始");
        //实例化返回对象
        JSONObject resultJson = new JSONObject();

        //获得参数(微信统一下单接口生成的prepay_id )
        String prepayId = request.getParameter("prepayId");
        //创建 时间戳
        String timeStamp = Long.valueOf(System.currentTimeMillis()).toString();
        //创建 随机串
        String nonceStr = WXPayUtil.generateNonceStr();
        //创建 MD5
        String signType = "MD5";

        //创建hashmap(用户获得签名)
        SortedMap<String, String> paraMap = new TreeMap<String, String>();
        //设置(小程序ID)(这块一定要是大写)
        paraMap.put("appId", "替换为自己的小程序ID");//小程序ID
        //设置(时间戳)
        paraMap.put("timeStamp", timeStamp);
        //设置(随机串)
        paraMap.put("nonceStr", nonceStr);
        //设置(数据包)
        paraMap.put("package", "prepay_id="+prepayId);
        //设置(签名方式)
        paraMap.put("signType", signType);


        //调用逻辑传入参数按照字段名的 ASCII 码从小到大排序（字典序）
        String stringA = formatUrlMap(paraMap, false, false);
        //第二步，在stringA最后拼接上key得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue。(签名)
        String sign = null;
        try {
            sign = MD5(stringA+"&key="+"");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(StringUtils.isNotBlank(sign)){
            //返回签名信息
            resultJson.put("sign", sign);
            //返回随机串(这个随机串是新创建的)
            resultJson.put("nonceStr", nonceStr);
            //返回时间戳
            resultJson.put("timeStamp", timeStamp);
            //返回数据包
            resultJson.put("package", "prepay_id="+prepayId);

            System.out.println("微信 支付接口生成签名 设置返回值");
        }
        System.out.println("微信 支付接口生成签名 方法结束");
        return resultJson;
    }


}
