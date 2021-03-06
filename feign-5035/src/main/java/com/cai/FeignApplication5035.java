package com.cai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.cai.*"})
@ComponentScan("com.cai")
public class FeignApplication5035 {

    public static void main(String[] args) {
        SpringApplication.run(FeignApplication5035.class, args);
    }

}
