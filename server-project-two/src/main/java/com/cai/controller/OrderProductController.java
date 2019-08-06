package com.cai.controller;

import com.cai.entity.OrderProduct;
import com.cai.feignClient.OrdersFeignClient;
import com.cai.service.OrderProductService;
import com.cai.utilEntity.MessageBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class OrderProductController {
    @Autowired
    OrderProductService orderProductService;
    @Autowired
    OrdersFeignClient ordersFeignClient;

    /**
     * TODO 测试Feign请求
     * @return
     */
    @GetMapping(value = "/get")
    public MessageBox get(){
        System.out.println("====="+ordersFeignClient.getFeginAll());
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setCapacity(1);
        orderProduct.setCount(1);
        orderProduct.setCreationtime(new Date());
        orderProduct.setPrice(1);
        orderProduct.setLineno(1);
        orderProduct.setProductcode("1");
        orderProduct.getCreationtime(new Date());
        orderProduct.setProductname("1");
        orderProduct.setProducttype(1);
        orderProduct.setStatus(1);
        orderProduct.setUnit("1");
        orderProduct.setTenantid(1);
        //orderProductService.addOrderProduct(orderProduct);
        return MessageBox.build("200","ok",ordersFeignClient.getFeginAll());
    }

    @GetMapping(value = "/getFeign")
    public String getFeign(){
        //System.out.println("====="+ordersFeignClient.getFeginAll());
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setCapacity(1);
        orderProduct.setCount(1);
        orderProduct.setCreationtime(new Date());
        orderProduct.setPrice(1);
        orderProduct.setLineno(1);
        orderProduct.setProductcode("1");
        orderProduct.getCreationtime(new Date());
        orderProduct.setProductname("1");
        orderProduct.setProducttype(1);
        orderProduct.setStatus(1);
        orderProduct.setUnit("1");
        orderProduct.setTenantid(1);
        orderProductService.addOrderProduct(orderProduct);
        return "ok";
    }



}
