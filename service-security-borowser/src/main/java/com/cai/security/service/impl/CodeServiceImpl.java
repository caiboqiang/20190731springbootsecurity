package com.cai.security.service.impl;

import com.cai.redis.RedisService;
import com.cai.security.service.CodeService;
import com.cai.utilEntity.MessageBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    private RedisService redisService;
    @Override
    public MessageBox createSmsCode(String phone) {
        if(phone == null || "".equals(phone)){
            return MessageBox.build("202","手机号不能为空");
        }
        int code =(int) ((Math.random()*9+1)*10000);
        boolean f = redisService.set("Login"+phone,code,360l);
        if(!f){
            return MessageBox.build("202","验证码发送失败");
        }
        return MessageBox.build("200","验证码发送成功",code);
    }


}
