package com.cai.controller;

import com.cai.service.OrdersService;
import com.cai.utilEntity.MessageBox;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "defaultFallback")//默认所有方法提供这个服务降级方法
public class OrdersController {
    @Autowired
    OrdersService ordersService;

    //@HystrixCommand(fallbackMethod = "Fallback") //备用方法要保证返回值一致
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000"),//请求超时
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//设置熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//设置在断路器滚动窗口中的最小请求数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//休眠时间窗
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//错误率 在滚动窗口中发生的错误率
    },fallbackMethod = "Fallback"
    )
    @GetMapping(value = "/ok")
    public MessageBox getTest() {
        if(true){
            int v = 10/0;
            throw new RuntimeException("出错了");
        }

        return MessageBox.build("200","ok","1.0-SNAPSHOT");
    }

    @HystrixCommand
    @GetMapping(value = "/getAll")
    public MessageBox getAll() {

        return MessageBox.build("200", "ok", ordersService.getAll());
    }

    public MessageBox Fallback(){
        return   MessageBox.build("100","ok Json","这个程序出错了");

    }
    /**
     *  服务降级全局处理
     */
    public MessageBox defaultFallback(){
        return   MessageBox.build("100","ok Json","太拥挤了，小二服务不过来，快联系程序员爸爸.......快....快...快....");

    }




}
