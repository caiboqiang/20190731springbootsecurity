package com.cai.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cai.utilEntity.MessageBox;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.web.bind.annotation.*;

/**
 * RESTful API 数据校验
 */
@RestController
@RequestMapping(value = "/REST")
@DefaultProperties
public class RESTfulController {

    @GetMapping("/get")
    @SentinelResource("get")
    public MessageBox get() {
        //throw new SysErr();
//        try(Entry entry = SphU.entry("Serve_6015")){
//            //资源保护
//            System.out.println("受保护的资源");
//        }catch(BlockException b){
//            System.err.println(b);
//        }
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
