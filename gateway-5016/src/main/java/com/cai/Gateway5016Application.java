package com.cai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableDiscoveryClient
@EnableZuulProxy //表示服务就是个网关了
@SpringBootApplication
@EnableResourceServer
public class Gateway5016Application {

    public static void main(String[] args) {
        SpringApplication.run(Gateway5016Application.class, args);
    }

}
