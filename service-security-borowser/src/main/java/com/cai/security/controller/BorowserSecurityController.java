package com.cai.security.controller;

import com.cai.core.properties.SecurityProperties;
import com.cai.utilEntity.MessageBox;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class BorowserSecurityController {
    //把当前请求缓存起来
    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 当身份认证时候跳转到这里
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ApiOperation(value = "登入页接口")
    public MessageBox requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取之前缓存的请求

        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest != null){
            String target = savedRequest.getRedirectUrl();
            log.info("引发跳转请求的URL:{}",savedRequest.getRedirectUrl());
            redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLogin());
            /*if(StringUtils.endsWithIgnoreCase(target,".html")){
                //设置用户配置登入页面
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLogin());
            }*/
        }
        return MessageBox.build("401","没有认证，请登入");
    }
    @RequestMapping(value = "/authentication/logErr")
    public MessageBox logErr(HttpServletRequest request, HttpServletResponse response){

        log.info("=======登入失败======");
        return MessageBox.build("401","登入失败");
    }

}
