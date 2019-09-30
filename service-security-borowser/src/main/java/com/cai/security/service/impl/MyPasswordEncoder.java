package com.cai.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *这是因为Spring boot 2.0.3引用的security 依赖是 spring security 5.X版本，此版本需要提供一个PasswordEncorder的实例，否则后台汇报错误：
 * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
 */
@Slf4j
@Component
public class MyPasswordEncoder implements PasswordEncoder {
    //todo 用来加密密码的
    @Override
    public String encode(CharSequence arg0) {
        log.info("======={}=======",arg0.toString());
        return arg0.toString();
    }
    //todo 密码比较
    @Override
    public boolean matches(CharSequence arg0, String arg1) {
        log.info("======={}======={}==========",arg0.toString(),arg1.toString());
        return arg1.equals(arg0.toString());
    }

}
