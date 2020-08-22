package com.cai.serverResouce;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer //表示这个服务作为资源服务器的一个服务
public class OAuth2ServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resourceServerSecurityConfigurer) throws Exception {
        resourceServerSecurityConfigurer.resourceId("server-user"); //配置id 资源服务器id
    }

    /**
     * 权限检查
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests ()
                .antMatchers ( HttpMethod.POST ).access ( "#oauth2.hasScope('write')" )//post 请求都要验证是否有这个权限
                .antMatchers ( HttpMethod.GET ).access ("#oauth2.hasScope('read')");
//        httpSecurity.authorizeRequests ().
//                antMatchers ( "/oauth/chech_token" ).
//                permitAll ().
//                anyRequest ().authenticated ();//配置不需要拦截的请求

    }
}
