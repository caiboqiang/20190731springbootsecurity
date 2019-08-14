package com.cai.config;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * 在网关里设置整个服务的跨域
 */
@Configurable
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //跨域配置
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);//todo 设置跨域
        config.setAllowedOrigins(Arrays.asList("*")); //todo  里面放的都是域名 www.a.com  ,b.com ....
        config.setAllowedMethods(Arrays.asList("*")); //todo 允许请求 GET POST
        config.setAllowedHeaders(Arrays.asList("*")); //todo 允许的头
        config.setMaxAge(300l); //todo 缓存时间 在这个时间里面对相同的跨域请求不在检查
        source.registerCorsConfiguration("/**",config);
        return new CorsFilter(source);
    }
}
