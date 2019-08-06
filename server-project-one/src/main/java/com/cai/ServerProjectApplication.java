package com.cai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //TODO 客户端
//@EnableDistributedTransaction
public class ServerProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerProjectApplication.class, args);
    }

}
