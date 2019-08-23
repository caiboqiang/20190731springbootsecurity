package com.cai.controller;

import com.cai.utilEntity.MessageBox;
import org.springframework.web.bind.annotation.*;

/**
 * RESTful API 数据校验
 */
@RestController
@RequestMapping(value = "/REST")
public class RESTfulController {

    @GetMapping("/get")
    public MessageBox get(){
        return MessageBox.build("","Get API");
    }

    @GetMapping("/getV/{id}")
    public MessageBox getV(@PathVariable("id") String id){
        return MessageBox.build("","Get API:"+id);
    }
    @PostMapping("/post")
    public MessageBox post(){
        return MessageBox.build("","Post API");
    }
}
