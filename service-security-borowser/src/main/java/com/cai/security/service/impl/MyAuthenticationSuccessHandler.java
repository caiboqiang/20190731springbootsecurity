package com.cai.security.service.impl;

import com.cai.core.properties.LoginResponseType;
import com.cai.core.properties.SecurityProperties;
import com.cai.utilEntity.MessageBox;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 实现自定义跳转
 */
@Slf4j
@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 此方法登入成功之后会被调用
     * @param request
     * @param response
     * @param authentication 作用封装了认证信息
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("=======登入成功authentication:{}=====",authentication);
        //返回json
        /*if(LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(MessageBox.build("200","登入成功",authentication)));
        }else {
        //跳转

        }*/
        //默认处理器
        super.onAuthenticationSuccess(request, response, authentication);

    }
}
