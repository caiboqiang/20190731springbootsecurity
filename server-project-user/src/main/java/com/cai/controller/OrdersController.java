package com.cai.controller;

import com.cai.service.OrdersService;
import com.cai.utilEntity.MessageBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrdersController {
    @Autowired
    OrdersService ordersService;

    @GetMapping(value = "/ok")
    public String getTest() {
        return "1.0-SNAPSHOT";
    }

    @GetMapping(value = "/getAll")
    public MessageBox getAll() {

        return MessageBox.build("200", "ok", ordersService.getAll());
    }






}
