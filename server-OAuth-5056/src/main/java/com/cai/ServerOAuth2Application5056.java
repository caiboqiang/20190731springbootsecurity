package com.cai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //TODO 客户端
@EnableCircuitBreaker
public class ServerOAuth2Application5056 {


    public static void main(String[] args) {
        SpringApplication.run ( ServerOAuth2Application5056.class, args );
    }

}
