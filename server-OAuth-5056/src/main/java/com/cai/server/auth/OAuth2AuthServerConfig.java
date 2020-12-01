package com.cai.server.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;


import javax.sql.DataSource;

/**
 * 配置类
 * 1.需要继承一个spring的父类 AuthorizationServerConfigurerAdapter:授权服务器配置的适配器
 */
//@EnableRedisHttpSession //表示把session存到缓存
//@EnableJdbcHttpSession //表示把session存到数据库
@Configuration
@EnableAuthorizationServer //代表这个应用最为 Authorization 授权服务器
public class OAuth2AuthServerConfig extends AuthorizationServerConfigurerAdapter{

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder; //密码加密工具

    @Autowired
    private DataSource dataSource;

    /**
     * 存储token默认存到内存
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        //redis存储
        //return new RedisTokenStore(redisConnectionFactory);
        return new JdbcTokenStore (dataSource);
    }
    /**
     * 用户登入的验证
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore (tokenStore())
       .authenticationManager (authenticationManager);
        //endpoints.authenticationManager(authenticationManager);
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security
//                .tokenKeyAccess("permitAll()")// /oauth/token_key 安全配置
//                .checkTokenAccess("permitAll()"); // /oauth/check_token 安全配置
        //.checkTokenAccess("permitAll()");  //来验证请求一定是经过身份认证的

        //security.allowFormAuthenticationForClients();
        security.checkTokenAccess("isAuthenticated()");
        //security.checkTokenAccess("permitAll()");
        //.checkTokenAccess("permitAll()"); //验证token一定是经过身份认证的
    }
    /**
     * 客户端应用的配置
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // mysql 读
        clients.jdbc (dataSource);

        //硬编码
//        clients.inMemory ().
//                withClient ("adminApp"). //应用的用户名服务名
//                secret (new BCryptPasswordEncoder ().encode("123456")). //应用的密码
//                scopes ("read","write").//ACL权限控制
//                accessTokenValiditySeconds (3600). //Token令牌有效时间
//                resourceIds ("server-6015"). //可以访问的资源服务器
//                authorizedGrantTypes ("password"). //授权的方式 有4中授权类型
//                and ().
//                withClient ("adminService"). //应用的服务名
//                secret (passwordEncoder.encode("123456")). //密码
//                scopes ("read","write").//权限控制
//                accessTokenValiditySeconds (3600). //Token令牌有效时间
//                resourceIds ("server-user"). //可以访问的资源服务器
//                authorizedGrantTypes ("password");
                //authorizedGrantTypes ("password","authorization_code","implicit","refresh_token");
    }

    public static void main(String[] args) {
        System.out.println ( new BCryptPasswordEncoder(  ).encode ( "123456" ) );
    }

}
