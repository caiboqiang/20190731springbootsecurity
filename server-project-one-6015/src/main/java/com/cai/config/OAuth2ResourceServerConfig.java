package com.cai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.stereotype.Component;

@Configuration
@EnableResourceServer //作用告诉OAuth2服务器本服务作为资源服务器
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter{
    //配置id
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("server-6015");
    }
    //配置权限
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST)
                .access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.GET)
                .access("#oauth2.hasScope('read')");//权限表达式 表示只有写 读
    }


}
