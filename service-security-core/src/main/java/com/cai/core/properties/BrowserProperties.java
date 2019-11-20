package com.cai.core.properties;

import lombok.Data;

@Data
public class BrowserProperties {
    //默认系统页面
    private  String login = "/signIn.html";

    //默认记住我1小时
    private String rememberMeSeconds = "60";

    //验证码 默认时间
    private  String ValidateCodeTimeOut = "60l";

    //验证码登入 默认时间
    private  long CodeTimeOut = 180l;

    private LoginResponseType loginType = LoginResponseType.JSON;
}
