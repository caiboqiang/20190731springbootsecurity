package com.cai.configToken;

//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

//@Configuration
//@EnableWebSecurity
public class OAuth2WebSecurityConfig  {//extends WebSecurityConfigurerAdapter

//    @Bean
//    public ResourceServerTokenServices tokenServices(){
//        //调用远程服务的
//        RemoteTokenServices tokenServices = new RemoteTokenServices();
//        tokenServices.setClientId("adminApp");
//        tokenServices.setClientSecret("123456");
//        tokenServices.setCheckTokenEndpointUrl("http://localhost:5056/oauth/check_token");
//        return tokenServices;
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManager() throws Exception{
//        OAuth2AuthenticationManager auth2AuthenticationManager = new OAuth2AuthenticationManager();
//        auth2AuthenticationManager.setTokenServices(tokenServices());
//        return auth2AuthenticationManager;
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .anyRequest().permitAll().and().logout().permitAll();//配置不需要登录验证
//    }
}