package com.cai.controller;

import com.cai.utilEntity.MessageBox;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties
public class TestController {
    @GetMapping(value = "/getV")
    @HystrixCommand
    public MessageBox getV(){
        return MessageBox.build("","");
    }
}
