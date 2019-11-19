package com.cai.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.cai.entity.AlipayVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Slf4j
@RestController
public class AlipayController {

    private String gatewayUrL = "https://openapi.alipaydev.com/gateway.do";
    private String app_id = "2016100200645935";
    // 商户私钥，您的PKCS8格式RSA2私钥
    private String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCXzBKgTg2BY745NDEkqzMeAhVsZIyX0sMpRkYF3R0J4ji9VOvJGsm6igHeJ9anDx0GYNTQLPrGG7BODLANhAOqM0zigsPmMA35qGyQj6ug8bSaNGxCIACnbc3gIlOKtrdMPMNyAENQphqwtVq3Xj0+mXNpmhnBPWNCojb6Lnzu8K38j5I3/GawzEtYNbO/TgGFGXQwqneOXIr0R+Ppcqy06G9Bw5ILyOUoSnlMhoJJiKr8Al+4is3uaL6CLDn+zPU8Fr8keYPujN6di9dJKOoi6m058vus4RyejkhhDfuFJCLoIZ2jjnYA+7fblN5nhIhEqvxKn+6UeQ2c82MvsFArAgMBAAECggEAXvImNF6d0/CvathaZLUwo/RpsgKybcRGjeqGoeRL5YK6OrKDSKaYxzTLNkl02cl6PiMS+aMouAFZpXgKJmoMYMj1rjhcj/4+v4m5XjJ/B4wnisx/alzaC9+Chk7yMjFXNThMh2HiuO8sgprswwUPCbixl7vroBLloygTeyjYvBqYG9Dui3V5CZ/Uf+BQp/0IiwKDftFsnr3zeXFeYQNNyPVMGYzCajSF7vQJoOIyJwlE4AJvzTnXklJW/NaxwqYm0MhAGkbhYc7ZoT7mZm6NQIf7OqKjsAkztFPvVkdeqgUwoc6+u6V7hx4QXcH3nCurYIwbvfxRyarWOKyQCIYdAQKBgQDG3sKgb5GMpKsOawPph4R921P8FfG2qhUH/z3LN72K5K2NRnxY4uDHUxGLNOGQOW2CCsjm5jSvHIlPGKRjiR44qSszVNAdVnfs3GzWnhEJ42fDt0GFP3I3v7C6wdCMuA4oGlO58emyV/jdZZUdnW7HgaMrMdrS0NudbwaoN0kURwKBgQDDZ3yK4sWNg0gE+Nr0VUdQDZ/O1AvRLY5XpRJXVJHd/WAfPavFG1+449lATCEhKx0Vf94LaMI0ugeLsf0ODtBAU+AMsddkV25NlQCUHqwJhYXi2XBS8EAbcYDNgh7s/mxYfKvpn9TILYwzBJK0aSPlDp/r0BOMcE0aBlaX3a+K/QKBgDzSrJv9A3sMcfS67jtiYzdK+WjEHWVMp5oGipO4vJPaVOo8gJN+8FiGI3t79PDzX3bCbaeRCNP12lkCpGR8721M8BWG/w+5/nvG/tK1DWkE0Buf1L44JWq/igHUfUBMmqfRRYI9ZshQ96BfDTg9ikAiehEf8lk/36tK5bJVFljJAoGBAI3boUTK4RSdTDmf2IUtK/poCa6DrpiLdB0Yz7nqwYLhoVjM0tjjyJhXga72Fc8Yl6K2E6NAyEyzCStXahk8k98/hRZyPaaw+nkERUlDAy5eaxaHOM5dcIz718BaSM1Q36NVzxBqYnV466FWHpMvCUoPVfZCNw7rsG3GUDMsU3aZAoGAHCdZfhb6O7y6+V1hQ8C+t3elBJaZn8YfF5aCThKHKMzPi19bCvMq1W8Q4iKtAGWwz9gy01nDLMcC0iH/ZcqWtmQgi5AbwBGC9Qiw+BuM9ri8lfkF/KE1ue8sIaTxVGgV6tLPLqK86ECwZhnhmHLg/Hzds+qoxIouOyA8QSYSNY0=";
    private String charset = "UTF-8";
    // 支付宝公钥,
    private String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjpCTKqu2SNMz95Qjv6qedtDaY6lrEPuePHMKGbXL0/3JcQrepx5f9PQP7thXnuTaMTZpmNmDNardZyzMnULCtG6hzfQtAks+CpehEtntzQpUyNGH7881yREiH93xyfaJ2YattgFfY3GK7QlMZIsGw9RW7wONQUP36p56Q2W8IzxnyajKZa7BxJHDluJK4oQYA4lvSWMmOzQAbOD4s1MW+lFxzlxgYr6Bg/sq9zn0BahTEWCi//vF/fxasVxU3bthGczcpVhEZxVNDrDqj0iwRhuJ3gk96l5UMCFFQ7CkkN3qwS1WJASFIkzhYPg4rFojxb9n9RVjUaMiRrUoVnkyLQIDAQAB";
    private String sign_type = "RSA2";
    //同步回调
    private String return_url = "";
    //异步回调
    private String notify_url = "";
    @GetMapping("/pay")
    private String alipayPay() throws AlipayApiException {

        /*AlipayVo vo = new AlipayVo();
        //这里模拟了一个订单的id
        vo.setOut_trade_no(UUID.randomUUID().toString().replace("-", ""));
        vo.setTotal_amount("0.01");


        //这个是固定的，沙箱默认就用这个参数
        vo.setProduct_code("FAST_INSTANT_TRADE_PAY");*/
        AlipayVo vo = new AlipayVo();

        SimpleDateFormat formattesr = new SimpleDateFormat("yyyyMMddHHmmss");

        Date date = new Date(new Date().getTime()+2*60*1000);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        vo.setOut_trade_no(formattesr.format(new Date()));
        vo.setTotal_amount("0.01");
        vo.setSubject("测试商品");
        vo.setTime_expire(dateString);
        //这个是固定的这个参数
        vo.setProduct_code("FAST_INSTANT_TRADE_PAY");
        vo.setIt_b_pay("2m");
        String json = JSON.toJSONString(vo);
        log.info("发起支付传参："+json);
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrL, app_id,
                merchant_private_key, "json", charset, alipay_public_key, sign_type);
        // 设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        //同步回调
        alipayRequest.setReturnUrl(return_url);
        //异步回调
        alipayRequest.setNotifyUrl(notify_url);
        alipayRequest.setBizContent(json);
        String result = null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (Exception e) {
            log.info("payerror" + result);
        }

        return result;
    }


    /**
     * @Description: 支付宝回调接口-异步回调
     * @param out_trade_no 商户订单号
     * @param trade_no     支付宝交易凭证号
     * @param trade_status 交易状态
     * @return String
     * @throws AlipayApiException
     */
    @PostMapping("/notify")
    private String alipayNotify(HttpServletRequest request, String out_trade_no, String trade_no, String trade_status)
            throws AlipayApiException {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            map.put(name, valueStr);
        }
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(map, alipay_public_key, charset, sign_type);
        } catch (com.alipay.api.AlipayApiException e) {
            log.info("notify 验证失败",e);
            // 验签发生异常,则直接返回失败
            return ("--failed--");
        }
        if (signVerified) {
            //处理你的业务逻辑，更细订单状态等
            return ("--success--");
        } else {
            log.info("验证失败,不去更新状态");
            return ("--failed--");
        }
    }


    /**
     * @Description: 支付宝回调接口-同步回调
     * @param out_trade_no 商户订单号
     * @param trade_no     支付宝交易凭证号
     * @return String
     * @throws AlipayApiException
     */
    @GetMapping("/return")
    private String alipayReturn(Map<String, String> params, HttpServletRequest request, String out_trade_no, String trade_no, String total_amount)
            throws AlipayApiException {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                System.out.println(valueStr);
            }
            map.put(name, valueStr);
        }
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(map, alipay_public_key, charset, sign_type);
        } catch (AlipayApiException e) {
            log.info("支付宝回调异常", e);
            // 验签发生异常,则直接返回失败
            return ("--fail--");
        }
        if (signVerified) {
            return ("--success--");
        } else {
            return ("fail");
        }
    }

}
