package com.cai.server.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.stereotype.Component;

/**
 * 配置类
 * 1.需要继承一个spring的父类 AuthorizationServerConfigurerAdapter:授权服务器配置的适配器
 */
@Configuration
@EnableAuthorizationServer //代表这个应用最为 Authorization 授权服务器
public class OAuth2AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder; //密码加密工具

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("isAuthenticated()"); //验证token一定是经过身份认证的
    }

    /**
     * 客户端应用的配置
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory ().
                withClient ("adminApp"). //应用的服务名
                secret (passwordEncoder.encode("123456789")). //密码
                scopes ("r","w").//权限控制
                accessTokenValiditySeconds (1800).//Token令牌有效时间
                resourceIds ( "XXXXX-oooooo1" ).//可以访问的资源服务器
                authorizedGrantTypes ("password").//授权的方式
                and ().
                withClient ("admin"). //应用的服务名
                secret (passwordEncoder.encode("123456")). //密码
                scopes ("r","w").//权限控制
                accessTokenValiditySeconds (1800).//Token令牌有效时间
                resourceIds ( "XXXXX-oooooo" ).//可以访问的资源服务器
                authorizedGrantTypes ("password");
    }

    /**
     * 用户登入的验证
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager (authenticationManager);
    }
}
