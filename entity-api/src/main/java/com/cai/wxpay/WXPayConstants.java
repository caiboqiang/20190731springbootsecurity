package com.cai.wxpay;

import sun.net.www.http.HttpClient;

/**
 * 常量
 */
public class WXPayConstants {

    /*支付*/
    public static final String APP_ID = "wxa94c78d2b7b2cba8";//appid  填写你们自己的
    public static final String MCH_ID = "1499926502";//商家ID   填写你们自己的
    public static final String OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    public static final String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";//统一下单接口
    public static final String CALLBACK_URL = "https://127.0.0.1/wxPay/callBack";//微信服务器调用支付结果通知路径
    public static final String PATERNER_KEY = "WuHan20210325WuHan20210325SaaSbb";//商户key秘钥   填写你们自己的v1
    /*退款*/
    public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";//申请退款路径接口
    public static final String notify_url = "https://127.0.0.1/wxPay/notifyUrl";//微信服务器调用退款结果通知路径

    public static final String MD5 = "MD5";
    public static final String FIELD_SIGN = "sign";
    public static final String FIELD_SIGN_TYPE = "sign_type";
    public static final String WXPAYSDK_VERSION = "WXPaySDK/3.0.9";
    public static final String USER_AGENT = WXPAYSDK_VERSION +
            " (" + System.getProperty("os.arch") + " " + System.getProperty("os.name") + " " + System.getProperty("os.version") +
            ") Java/" + System.getProperty("java.version") + " HttpClient/" + HttpClient.class.getPackage().getImplementationVersion();

}
