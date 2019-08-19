package com.cai.controller;

import com.cai.entity.OrderProduct;
import com.cai.feignClient.OrdersFeignClient;
import com.cai.service.OrderProductService;
import com.cai.utilEntity.MessageBox;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * f服务降级可以用在客户端或服务提供者
 */
@RestController
@DefaultProperties //服务接口监控
public class OrderProductController {
    @Autowired
    OrderProductService orderProductService;
    @Autowired
    OrdersFeignClient ordersFeignClient;

    /**
     * TODO 测试Feign请求
     * @return  Hystrix-
     */
    @GetMapping(value = "/get/{id}")
    @HystrixCommand
    public MessageBox get(@PathVariable("id") int id){
        if(id ==1 ){
            System.out.println("====="+ordersFeignClient.getFeignAll());
        }

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
        return MessageBox.build("200","ok");
    }

    @GetMapping(value = "/getFeign")
    @HystrixCommand
    public String getFeign(){
        //System.out.println("====="+ordersFeignClient.getFeginAll());
       /* OrderProduct orderProduct = new OrderProduct();
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
        orderProductService.addOrderProduct(orderProduct);*/
        return "ok";
    }

    @GetMapping(value = "/getOk")
    public String getOk(){

        System.out.println(ordersFeignClient.getFeignAll());
        return "getOk";
    }

}
