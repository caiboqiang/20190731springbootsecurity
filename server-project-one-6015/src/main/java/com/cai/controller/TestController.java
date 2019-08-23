package com.cai.controller;

import com.cai.utilEntity.MessageBox;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping(value = "/getV")
    public MessageBox getV(){
        return MessageBox.build("","");
    }
}
