package com.cai.serverResouce;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Component;

/**
 * 验证token是不是合法的
 */
@Configuration
@EnableWebSecurity
public class OAuth2WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /**
     * 需要远程验证
     */
    @Bean
    public ResourceServerTokenServices tokenServices(){
        //这是一个调用远程服务的
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices ( );
        remoteTokenServices.setClientId ("adminApp");
        remoteTokenServices.setClientSecret (passwordEncoder().encode ("12345"));
        remoteTokenServices.setCheckTokenEndpointUrl ("http://localhost:5056/oauth/chech_token");//校验的地址
        return remoteTokenServices;
    }

    /**
     * 把它声明成一个bean
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        OAuth2AuthenticationManager auth2AuthenticationManager = new OAuth2AuthenticationManager();
        auth2AuthenticationManager.setTokenServices (tokenServices());
        return auth2AuthenticationManager;
    }
}
