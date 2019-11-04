package com.cai.core.validate.code;

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
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    //@Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    /*@Autowired
    RedisService redisService;*/

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("======={}=============","/authentication/from".equals(request.getRequestURI() ));
        log.info("====={}======={}","POST".equals(request.getMethod()),request.getMethod() );
        //获取请求连接
        if("/authentication/from".equals(request.getRequestURI())
        &&  "POST".equals(request.getMethod())){
            log.info("=====拦截登入请求=======");
            String code = request.getParameter("code");

            try {
                ApplicationContext applicationContext = SpringContextUtil.getApplicationContext();
                RedisService redisService = (RedisService)applicationContext.getBean("redisService");
                Object object = redisService.set("k",11);
                Object objectV = redisService.get("k");
                log.info("==========Redis:{}==========",objectV.toString());
                if(object == null){
                    throw new ValidateCodeException("验证码不存在");
                }
                if(object.toString().equals("ValidateCode"+code)){
                    throw new ValidateCodeException("验证码不一致");
                }
                log.info("=======code:{}=======",code);

            } catch (ValidateCodeException e) {
                e.printStackTrace();
                //登入失败转发的控制层
                //redirectStrategy.sendRedirect(request,response,"/authentication/logErr");
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        //不做验证
        filterChain.doFilter(request,response);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
}
