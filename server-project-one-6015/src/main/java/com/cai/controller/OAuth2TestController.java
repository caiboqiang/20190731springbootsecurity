package com.cai.controller;

import com.cai.utilEntity.MessageBox;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2TestController {

    @ApiOperation("@AuthenticationPrincipal 获取用户名测试")
    @PostMapping(value = "/postTest")
    public MessageBox postTest(@AuthenticationPrincipal String username){//@AuthenticationPrincipal
        System.out.println("==========={"+username+"}===========");
        return MessageBox.build("200","postTest："+username);
    }
}
