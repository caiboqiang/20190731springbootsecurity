package com.cai.controller;

//import com.cai.core.properties.SecurityProperties;
import com.cai.redis.RedisService;
//import com.cai.security.service.CodeService;
import com.cai.service.PollingIp;
import com.cai.utilEntity.MessageBox;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.concurrent.Callable;

/**
 * 验证码发送
 */
@RestController
@Slf4j
@EnableSwagger2
@Api(value = "后台验证码业务",tags = "验证码操作接口")
public class CodeController {
    @Autowired
    private PollingIp pollingIp;
    @Autowired
    private RedisService redisService;
    //@Autowired
    //private CodeService codeService;
    //@Autowired
    //private SecurityProperties securityProperties;
    @GetMapping(value = "/createSmsCode/{phone}")
    @ApiOperation(value = "发送登入密码接口")
    public Callable<MessageBox> createSmsCode(@ApiParam(value = "手机号")@PathVariable(value = "phone") String  phone){
        Callable<MessageBox> callable = new Callable<MessageBox>(){
            @Override
            public MessageBox call() throws Exception {
                //return codeService.createSmsCode(phone);
                return null;
            }
        };
        return callable;
    }
    @GetMapping(value = "/getSmsCode/{phone}")
    @ApiOperation(value = "获取登入密码接口")
    public Callable<MessageBox> getSmsCode(@ApiParam(value = "手机号")@PathVariable(value = "phone")String phone){
        Callable<MessageBox> callable = new Callable<MessageBox>(){
            @Override
            public MessageBox call() throws Exception {
                int code = (int)((Math.random()*9+1)*100000);
                //Object object = redisService.set("Login"+phone,code,securityProperties.getBrowser().getCodeTimeOut());
                //redisService.set(phone,code,securityProperties.getBrowser().getCodeTimeOut());
                //log.info("=====object:{}======",object.toString());
                return MessageBox.build("200","获取短信验证码",code);
            }
        };
        return callable;
    }
}
