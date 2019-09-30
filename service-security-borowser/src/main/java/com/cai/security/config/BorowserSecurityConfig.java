package com.cai.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 基于浏览器
 * 配置类
 */
@Configuration
public class BorowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //弹跳登入
        //http.httpBasic()
        //表单登入
        http.formLogin()
        .and()
        //授权配置
        .authorizeRequests()
        //任何请求
        .anyRequest()
        //都需要身份认证才可以访问
        .authenticated();

    }
}
