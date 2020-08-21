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
public class OAuth2AuthServerConfig extends AuthorizationServerConfigurerAdapter{

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder; //密码加密工具

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("isAuthenticated()");  //来验证请求一定是经过身份认证的
                //.checkTokenAccess("isAuthenticated()"); //验证token一定是经过身份认证的
    }

    /**
     * 客户端应用的配置
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory ().
                withClient ("adminApp"). //应用的用户名服务名
                secret (passwordEncoder.encode("123456")). //应用的密码
                scopes ("read","write").//ACL权限控制
                accessTokenValiditySeconds (3600). //Token令牌有效时间
                resourceIds ( "server-user" ). //可以访问的资源服务器
                authorizedGrantTypes ("password"). //授权的方式 有4中授权类型
                and ().
                withClient ("adminService"). //应用的服务名
                secret (passwordEncoder.encode("123456")). //密码
                scopes ("read","write").//权限控制
                accessTokenValiditySeconds (3600). //Token令牌有效时间
                resourceIds ( "server-user" ). //可以访问的资源服务器
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
