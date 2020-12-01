package com.cai.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * 拦截器 试用过滤器拦截服务请求
 */
//@Component
public class SysFilter implements Filter {
    //初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("=========系统SysFilter初始化=========");
    }
    //最重要的
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long t = new Date().getTime();
        //System.out.println("=========资源请求开始时间："+t +"=========");
        //System.out.println("=========资源请求资源路径LocalAddr："+request.getLocalAddr() +"=========");
        //System.out.println("=========资源请求资源路径LocalName："+request.getLocalName() +"=========");
        //String url = ((HttpServletRequest) request).getPathTranslated();
        //System.out.println("=========资源请求资源路径URI："+((HttpServletRequest) request).getRequestURI() +"=========");
        chain.doFilter(request,response);
        long l = new Date().getTime()-t;
        //System.out.println("=========资源请求结束时间："+ l +"=========");
    }
    //销毁
    @Override
    public void destroy() {
        System.out.println("=========系统SysFilter销毁=========");
    }
}
