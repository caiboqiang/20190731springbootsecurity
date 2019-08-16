package com.cai.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "serverProjectTwo")
public interface OrderProductFeignClient {
    @GetMapping(value = "/getFeign")
    String getFeign();
}
