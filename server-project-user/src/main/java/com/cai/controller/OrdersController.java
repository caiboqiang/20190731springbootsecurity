package com.cai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {

    @GetMapping(value = "/ok")
    public String getTest(){
        return "1.0-SNAPSHOT";
    }
}
