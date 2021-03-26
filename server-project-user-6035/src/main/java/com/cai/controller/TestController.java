package com.cai.controller;

import com.cai.config;
import com.cai.entity.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class TestController {
    /**
     * 单独获取用户数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/getId")
    public String getId( Long id){//@AuthenticationPrincipal(expression = "#this.id")
        log.info ( "============"+id);
        return "ok";
    }

    /**
     *返回用户信息
     * @param userVo
     * @return
     */
    @RequestMapping(value = "/getUserData")
    public String getUserData( UserVo userVo){//@AuthenticationPrincipal
        log.info ( "============"+userVo.toString ());
        return "ok";
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext ( config.class);
        annotationConfigApplicationContext.getBean ( "redisService" );
        System.err.println (annotationConfigApplicationContext.getBean ( "redisService" ));
    }
}
