package com.cai.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Spring 拦截器：比Filter有有优势 可以获取到具体的内容
 */
@Component
public class SysInterceptor implements HandlerInterceptor {
    //控制器调用前的方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println("=======preHandle=======");
        //System.out.println("=======SysInterceptor调用开始时间："+new Date().getTime() +"=======");
        //System.out.println("=======SysInterceptor调用开始时间："+((HandlerMethod)handler).getBean().getClass().getName() +"=======");
        return false;
    }
    //控制器调用后的方法出现异常不会被调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       // System.out.println("=======postHandle=======");
    }
    //控制器调用后不管是否有异常都会被调用的方法 当有异常ex参数不会为空
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("=======afterCompletion=======");
    }
}
