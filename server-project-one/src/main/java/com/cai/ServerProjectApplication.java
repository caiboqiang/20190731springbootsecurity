package com.cai;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient //TODO 客户端
@EnableDistributedTransaction //TODO 开启分布式事物
@EnableFeignClients(basePackages = {"com.cai.*"})
public class ServerProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerProjectApplication.class, args);
    }

}
