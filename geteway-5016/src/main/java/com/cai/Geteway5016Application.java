package com.cai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
@EnableDiscoveryClient
@EnableZuulProxy //表示服务就是个网关了
@SpringBootApplication
public class Geteway5016Application {

    public static void main(String[] args) {
        SpringApplication.run(Geteway5016Application.class, args);
    }

}
