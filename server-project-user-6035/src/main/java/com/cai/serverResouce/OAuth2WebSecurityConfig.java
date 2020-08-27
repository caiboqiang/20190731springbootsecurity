package com.cai.serverResouce;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
//import org.springframework.security.oauth2.provider.token.*;
//import org.springframework.stereotype.Component;
//
///**
// * 验证token是不是合法的
// */
////@Configuration
////@EnableWebSecurity
//public class OAuth2WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    /**
//     * 需要远程验证
//     */
//    @Bean
//    public ResourceServerTokenServices tokenServices(){
//        //这是一个调用远程服务的
//        RemoteTokenServices tokenServices = new RemoteTokenServices ( );
//        tokenServices.setClientId ("adminApp");
//        tokenServices.setClientSecret (passwordEncoder().encode ("12345"));
//        tokenServices.setCheckTokenEndpointUrl ("http://localhost:5056/oauth/chech_token");//校验的地址
//        //userData
//        tokenServices.setAccessTokenConverter (getAccessTokenConverter()  ); //把token转换成用户数据
//        return tokenServices;
//    }
//    //获取用户数据
//    private AccessTokenConverter getAccessTokenConverter(){
//        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
//        //为了设置userDetailsService 转换
//        DefaultUserAuthenticationConverter userTokenConverter = new DefaultUserAuthenticationConverter();
//        userTokenConverter.setUserDetailsService (userDetailsService);
//
//        accessTokenConverter.setUserTokenConverter (userTokenConverter);
//        return accessTokenConverter;
//    }
//
//    /**
//     * 把它声明成一个bean
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        OAuth2AuthenticationManager auth2AuthenticationManager = new OAuth2AuthenticationManager();
//        auth2AuthenticationManager.setTokenServices (tokenServices());
//        return auth2AuthenticationManager;
//    }
//}
