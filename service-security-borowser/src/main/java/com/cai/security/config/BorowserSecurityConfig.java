package com.cai.security.config;

import com.cai.core.properties.SecurityProperties;
import com.cai.core.validate.code.ValidateCodeFilter;
import com.cai.security.service.impl.MyAuthenticationFailureHandler;
import com.cai.security.service.impl.MyAuthenticationSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * 基于浏览器
 * 配置类
 */
@Configuration
@Slf4j
public class BorowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;
    //注入自定义跳转
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //启动时候创建表
        jdbcTokenRepository.setCreateTableOnStartup(false);

        return jdbcTokenRepository;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info(securityProperties.getBrowser().getLogin());
        //创建图片过滤器
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        //弹跳登入
        //http.httpBasic()
        //表单登入
        http.
                addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)//在某个过滤器前添加过滤器
                .formLogin()
                //配置自定义登入页面
                .loginPage("/authentication/require") //配置请求是否是html请求决定转发跳转
                //登入路径配置
                .loginProcessingUrl("/authentication/from")
                //.successHandler(myAuthenticationSuccessHandler)//配置登入成功
                .failureHandler(myAuthenticationFailureHandler)//配置登入失败
                .and()
                //配置记住我
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(Integer.parseInt(securityProperties.getBrowser().getRememberMeSeconds()))//配置过期时间
                .userDetailsService(userDetailsService) //实现用户登入
                .and()
                //授权配置
                .authorizeRequests()
                //当访问下面链接不需要认证 可以访问
                .antMatchers("/authentication/require","/authentication/logErr","/createSmsCode/*",
                        securityProperties.getBrowser().getLogin()).permitAll()

                //任何请求
                .anyRequest()
                //都需要身份认证才可以访问
                .authenticated()
                .and()
                .csrf().disable();//关闭跨站验证

    }
}
