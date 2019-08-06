package com.cai;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.cai.*"})
@EnableDistributedTransaction
public class ServerProjectTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerProjectTwoApplication.class, args);
    }

}
