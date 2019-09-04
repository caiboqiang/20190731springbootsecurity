package com.cai.filter;

import com.cai.utilEntity.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * spring apo 切片拦截
 */
@Slf4j
@Aspect
@Component
public class SysAspect {
    //切入点 在那些方法起作用  在什么时候起作用
    //提取重复的拦截代码(公用方法)
    @Pointcut("execution(public * com.cai.controller.*.*(..))")
    public void http() {
    }

    //请求前通知
    @Before("http()")
    public void httpBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest servletRequest = (HttpServletRequest) requestAttributes.getRequest();
        //url
        servletRequest.getRequestURL();
        log.info("===========AOP:{}===============",servletRequest.getRequestURL());
        //method GET/POST
        servletRequest.getMethod();
        //ip
        servletRequest.getRemoteAddr();
        //获取类名
        joinPoint.getSignature().getDeclaringTypeName();
        //类的方法
        joinPoint.getSignature().getName();
        //获取参数
        joinPoint.getArgs();
        log.info("==拦截成功=={}", servletRequest.getRequestURL());
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询cookie SHAREJSESSIONID 这一块可以换成redis 判断用户是否登入
        //Cookie[] cookies = request.getCookies();
        Cookie cookie = CookieUtil.get(request, "JSESSIONID");
        if(cookie != null ){
            String cookieName = cookie.getName();
            log.info("========AOP获取token:{}-{}==============",cookieName,cookie.getValue());
        }

        //System.out.println("拦截成功");

    }

    //请求后通知
    @After("http()")
    public void httpAfter() {
        log.info("==请求后拦截成功==");
        //System.out.println("请求后拦截成功");
    }

    /**
     * 获取返回的内容
     * returning = "object"返回入参
     */
    @AfterReturning(pointcut = "http()", returning = "object")
    public void httpAfterReturning(Object object) {
        log.info("==接口返回内容拦截:{}==", object.toString());
    }

    /**
     * 一般采用这种方式的比较多 在那些方法起作用  在什么时候起作用 ProceedingJoinPoint:包含了我们需要的信息
     */
    @Around("execution(public * com.cai.controller.*.*(..))")
    public Object aRound(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object[] objects = proceedingJoinPoint.getArgs();
        for (Object args :objects){
            log.info("=========={}===========",args);

        }

        Object Object = proceedingJoinPoint.proceed();//实际上是去调用被拦截的方法返回的内容

        log.info("==========Object:{}===========",Object.toString());

        return Object;

    }


}
