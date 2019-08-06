package com.cai.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ServerProjectUser")
public interface OrdersFeignClient {
    @GetMapping(value = "/getFeginAll")
    String getFeginAll();
}
