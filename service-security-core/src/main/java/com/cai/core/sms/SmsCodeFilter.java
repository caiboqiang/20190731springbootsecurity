package com.cai.core.sms;

import com.cai.core.validate.code.ValidateCodeException;
import com.cai.redis.RedisService;
import com.cai.utilEntity.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码过滤器
 * OncePerRequestFilter:这个类是spring提供的工具类保证我们的过滤器每次只会被调一次
 */
@Slf4j
@Component("smsCodeFilter")
public class SmsCodeFilter extends OncePerRequestFilter {

    //@Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("======={}=============","/authentication/phone".equals(request.getRequestURI() ));
        log.info("====={}======={}","POST".equals(request.getMethod()),request.getMethod() );
        //获取请求连接
        if("/authentication/phone".equals(request.getRequestURI())
        &&  "POST".equals(request.getMethod())){
            log.info("=====拦截登入请求=======");
            String code = request.getParameter("pass");
            String phone = request.getParameter("phone");

            try {
                ApplicationContext applicationContext = SpringContextUtil.getApplicationContext();
                RedisService redisService = (RedisService)applicationContext.getBean("redisService");
                Object objectV = redisService.get(phone);
                //log.info("==========Redis:{}==========",objectV.toString());
                if(objectV == null){
                    throw new ValidateCodeException("短信验证码不存在");
                }
                if(!objectV.toString().equals(code)){
                    throw new ValidateCodeException("短信验证码不一致");
                }
                log.info("=======code:{}=======",code);
            } catch (ValidateCodeException e) {
                e.printStackTrace();
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        //只拦截短信登入
        filterChain.doFilter(request,response);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
}
