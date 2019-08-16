package com.cai.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //TODO 表明它是Eureka服务注册中心
public class EurekaApplication5025 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication5025.class, args);
    }

}
