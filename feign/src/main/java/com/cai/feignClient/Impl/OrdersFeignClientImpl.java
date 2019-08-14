package com.cai.feignClient.Impl;

import com.cai.feignClient.OrdersFeignClient;
import org.springframework.stereotype.Component;

@Component
public class OrdersFeignClientImpl implements OrdersFeignClient {
    @Override
    public String getF() {
        return null;
    }

    @Override
    public String getFeignAll() {
        System.out.println("服务挂了");
        return "接口异常";
    }
}
