package com.cai.feignClient;

import com.cai.feignClient.Impl.OrdersFeignClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ServerProjectUser",fallback = OrdersFeignClientImpl.class)//
public interface OrdersFeignClient {

    @GetMapping(value = "/getF")
    public String getF();

    @GetMapping(value = "/getFeignAll")
    public String getFeignAll();

}
