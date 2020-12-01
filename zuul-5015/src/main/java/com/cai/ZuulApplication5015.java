package com.cai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy //表示服务就是个网关了
public class ZuulApplication5015 {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication5015.class, args);
    }

}
