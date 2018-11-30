package cn.weixin2.sdk;

import java.io.InputStream;

public class WXPayment extends WXPayConfig {
    private String appId; //App ID
    private String mchId; //Mch ID
    private String key; //API 密钥
    private InputStream certStream; //商户证书内容
    private IWXPayDomain wxPayDomain; //WXPayDomain, 用于多域名容灾自动切换

    public WXPayment(String appId, String mchId, String key) {
        this.appId = appId;
        this.mchId = mchId;
        this.key = key;
    }
    public WXPayment(String appId, String mchId, String key, InputStream certStream, IWXPayDomain wxPayDomain) {
        this.appId = appId;
        this.mchId = mchId;
        this.key = key;
        this.certStream = certStream;
        this.wxPayDomain = wxPayDomain;
    }

    @Override
    String getAppID() {
        return this.appId;
    }

    @Override
    String getMchID() {
        return this.mchId;
    }

    @Override
    String getKey() {
        return this.key;
    }

    @Override
    InputStream getCertStream() {
        return this.certStream;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        return this.wxPayDomain;
    }
}
