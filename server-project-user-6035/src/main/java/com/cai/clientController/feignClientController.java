package com.cai.clientController;

import com.cai.service.OrdersService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class feignClientController {

    @Autowired
    OrdersService ordersService;

    @GetMapping(value = "/getF")
    public String getF(){
        ordersService.getF();
        return "ok";
    }

    @GetMapping(value = "/getFeignAll")
    @HystrixCommand
    public String getFeignAll(){
        /*if(true){
            throw  new RuntimeException("-------");
        }*/
        ordersService.getF();
        return "ok";
    }




}
