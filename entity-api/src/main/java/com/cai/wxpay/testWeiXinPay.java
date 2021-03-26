package com.cai.wxpay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class testWeiXinPay {

    public static void main(String[] args) {
//        Map map = new HashMap<String,Object>();
//        map.put("appid","wxa94c78d2b7b2cba8");
//        map.put("mchid","1499926502");
//        map.put("description","TEST");
//        map.put("out_trade_no",new Date().getTime()+"");
//        map.put("notify_url","www.baidu.com");
//        Map amountMap = new HashMap<String,Object>();
//        amountMap.put("total",100);
//        map.put("amount",amountMap);
//
//        Map payerMap = new HashMap<String,Object>();
//        payerMap.put("openid","oUpF8uMuAJO_M2pxb1Q9zNjWeS6o");
//        map.put("payer",payerMap);
//        String json = JSON.toJSONString(map);//map转String
//        String str = HttpUtil.doHttpPost(json,"https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi");
//        //JSONObject jsonObject = JSONObject.fromObject(map);
//        System.out.println("输出的结果是：" + str);

        prePay();


    }

    /**
     * 商品名称
     * 商品金额
     * @return
     */
    public static Map<String, Object> prePay(){
        // 返回参数
        Map<String, Object> resMap = new HashMap<>();
        //获取请求ip地址
        String ip = "127.0.0.1";//request.getHeader("x-forwarded-for");
        try {
            // 拼接统一下单地址参数
            Map<String, Object> paraMap = new HashMap<>();
            //--------------------
            //        Map map = new HashMap<String,Object>();
//        map.put("appid","wxa94c78d2b7b2cba8");
//        map.put("mchid","1499926502");
        paraMap.put("description","TEST");
//        map.put("out_trade_no",new Date().getTime()+"");
//        map.put("notify_url","www.baidu.com");
        Map amountMap = new HashMap<String,Object>();
        amountMap.put("total",100);
            paraMap.put("amount",amountMap);

        Map payerMap = new HashMap<String,Object>();
        payerMap.put("openid","oUpF8uMuAJO_M2pxb1Q9zNjWeS6o");
            paraMap.put("payer",payerMap);

            // 封装11个必需的参数
            paraMap.put("appid", WXPayConstants.APP_ID);
            paraMap.put("mch_id", WXPayConstants.MCH_ID);//商家ID
            paraMap.put("nonce_str", WXPayUtil.generateNonceStr());//获取随机字符串 Nonce Str
            //paraMap.put("body", "商品名称");     //商品名称
            paraMap.put("out_trade_no", new Date().getTime());//订单号
            //paraMap.put("total_fee", 100);    //测试改为固定金额
            paraMap.put("spbill_create_ip", ip);
            paraMap.put("notify_url",WXPayConstants.CALLBACK_URL);// 此路径是微信服务器调用支付结果通知路径
            paraMap.put("trade_type", "JSAPI");
            //paraMap.put("openid", "openId");
            String sign = WXPayUtil.generateSignature(paraMap, "WuHan20210325WuHan20210325SaaSaa");//商户密码 WXPayConstants.PATERNER_KEY
            //生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
            paraMap.put("sign", sign);
            //将所有参数(map)转xml格式
            String xml = WXPayUtil.mapToXml(paraMap);
            //String unifiedorder_url = "https://api.mch.weixin.qq.com/v3/pay/transactions/app";
            String unifiedorder_url = WXPayConstants.UNIFIEDORDER_URL;//统一下单接口
            String xmlStr = HttpClientUtil.doPostXml(unifiedorder_url, xml);
            String resData = JSON.toJSONString(paraMap, SerializerFeature.WriteMapNullValue);
            //String xmlStr =  HttpUtil.doHttpPost(resData,unifiedorder_url);
            System.out.println("xmlStr:"+xmlStr);
            //以下内容是返回前端页面的json数据
            //预支付id
            String prepay_id = "";
            if (xmlStr.indexOf("SUCCESS") != -1) {
                Map<String, Object> map = WXPayUtil.xmlToMap(xmlStr);//XML格式字符串转换为Map
                prepay_id =  map.get("prepay_id").toString();
                System.err.println("prepay_id_1=  "+prepay_id);
            }
            System.err.println("prepay_id_2=  "+prepay_id);
            Map<String, Object> payMap = new HashMap<String, Object>();
            // 封装所需6个参数调支付页面
            payMap.put("appId", WXPayConstants.APP_ID);
            payMap.put("timeStamp", WXPayUtil.getCurrentTimestamp()+"");//获取当前时间戳，单位秒
            payMap.put("nonceStr", WXPayUtil.generateNonceStr());//获取随机字符串 Nonce Str
            payMap.put("signType", "MD5");
            payMap.put("package", "prepay_id="+prepay_id);
            //生成带有 sign 的 XML 格式字符串  WuHan20210325WuHan20210325SaaSaa  WXPayConstants.PATERNER_KEY
            String paySign = WXPayUtil.generateSignature(payMap, "WuHan20210325WuHan20210325SaaSaa");
            payMap.put("paySign", paySign);
            // 封装正常情况返回数据
            resMap.put("success",true);
            resMap.put("payMap",payMap);
        } catch (Exception e) {
            // 封装异常情况返回数据
            resMap.put("success",false);
            resMap.put("message","调用统一订单接口错误");
            e.printStackTrace();
        }
        return resMap;
    }

    /*支付成功回调*/
//    public void callBack(HttpServletRequest request, HttpServletResponse response){
//        JsonResult result = new JsonResult();
//        System.err.println("微信支付成功,微信发送的callback信息,请注意修改订单信息");
//        InputStream is = null;
//        String resXml = "";
//        try {
//            is = request.getInputStream();//获取请求的流信息(这里是微信发的xml格式所有只能使用流来读)
//            String xml = WXPayUtil.inputStream2String(is);
//            Map<String, Object> notifyMap = WXPayUtil.xmlToMap(xml);//将微信发的xml转map
//            if(notifyMap.get("return_code").equals("SUCCESS")){
//
//
//                String ordersNum = notifyMap.get("out_trade_no").toString();//商户订单号
//                //处理订单状态
////          String openid = notifyMap.get("openid");
//                Date zhifutime = new Date();
//                Integer ordertype = 1;//1支付完成
//                //TODO 处理订单状态
//                try {
////                    orderMapper.updateByOrdersNum(ordersNum,ordertype,zhifutime,complete);
////                    result.setData(SUCCESS);
////                    result.setData("支付回调成功，修改订单状态为支付成功");
//
//                    resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
//                            + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
//                    BufferedOutputStream out = new BufferedOutputStream(
//                            //response.getOutputStream());
//                    out.write(resXml.getBytes());
//                    out.flush();
//                    out.close();
//                    System.err.println("返回给微信的值："+resXml.getBytes());
//                    is.close();
//                }catch (Exception e){
//                    //result.setMessage("订单状态修改失败");
//                }
//
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

    /*调用退款接口，取消订单*/
//    @RequestMapping("refund")
//    @ResponseBody
//    public Map<String, Object> refund(Integer id,HttpServletResponse response){
//
//        // 返回参数
//        Map<String, Object> resMap = new HashMap<>();
//        Date newtime = new Date();
//        String resXml = "";
//        try {
//            // 拼接统一下单地址参数
//            Map<String, Object> paraMap = new HashMap<>();
//
//            Integer price = Integer.valueOf("1000");//订单金额
//            String orderNum = new Date();
////       Integer price = 1;//支付金额，单位：分，这边需要转成字符串类型，否则后面的签名会失败
//            // 封装必需的参数
//            paraMap.put("appid", WXPayConstants.APP_ID);
//            paraMap.put("mch_id", WXPayConstants.MCH_ID);//商家ID
//            paraMap.put("nonce_str", WXPayUtil.generateNonceStr());//获取随机字符串 Nonce Str
//            paraMap.put("out_trade_no", orderNum);//订单号
//            paraMap.put("out_refund_no", orderNum);//商户退款单号
//            paraMap.put("total_fee",price);    //测试改为固定金额  订单金额
//            paraMap.put("refund_fee",price);    //退款金额
////       paraMap.put("notify_url", WXPayConstants.notify_url);   //退款路径
//            String sign = WXPayUtil.generateSignature(paraMap, WXPayConstants.PATERNER_KEY);//商户密码
//            //生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
//            paraMap.put("sign", sign);
//            //将所有参数(map)转xml格式
//            String xml = WXPayUtil.mapToXml(paraMap);
//            System.out.println("xml:"+xml);
//            // 退款 https://api.mch.weixin.qq.com/secapi/pay/refund
//            String refund_url = WXPayConstants.REFUND_URL;//申请退款路径接口
//            System.out.println("refund_url:"+refund_url);
//            //发送post请求"申请退款"
//            String xmlStr = HttpClientUtil.doRefund(refund_url, xml);
//            System.out.println("退款xmlStr:"+xmlStr);
//            /*退款成功回调修改订单状态*/
//            if (xmlStr.indexOf("SUCCESS") != -1) {
//                Map<String, Object> map = WXPayUtil.xmlToMap(xmlStr);//XML格式字符串转换为Map
//                if(map.get("return_code").equals("SUCCESS")){
//                    resMap.put("success",true);//此步说明退款成功
//                    resMap.put("data","退款成功");
//                    System.out.println("退款成功");
//                    Date refundtime = new Date();
//                    Integer ordertype = -1;//-1取消订单
//                    String complete = "已取消";
//                    try {
//                        //orderMapper.updateRefundByOrdersNum(orderNum,ordertype,refundtime,complete);
//                        //告诉微信服务器收到信息了，不要在调用回调action了========这里很重要回复微信服务器信息用流发送一个xml即可
//                        resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
//                                + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
//                        BufferedOutputStream out = new BufferedOutputStream(
//                                response.getOutputStream());
//                        out.write(resXml.getBytes());
//                        out.flush();
//                        out.close();
//                        System.err.println("返回给微信的值："+resXml.getBytes());
//                    }catch (Exception e){
//                        resMap.put("fail","订单状态修改失败");
//                    }
//                }
//
//            }else {
//                resMap.put("success","fail");//此步说明退款成功
//                resMap.put("data","退款失败");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return resMap;
//    }
}
