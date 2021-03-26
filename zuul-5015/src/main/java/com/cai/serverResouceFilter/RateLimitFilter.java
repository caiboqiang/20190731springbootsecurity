package com.cai.serverResouceFilter;

import com.google.common.util.concurrent.RateLimiter;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 限流
 */
//@Component
public class RateLimitFilter extends OncePerRequestFilter {
    //1秒只能访问一次的限流器
    private RateLimiter rateLimiter = RateLimiter.create(1);
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //true
        if(rateLimiter.tryAcquire()){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }else {
            //达到限流
            httpServletResponse.setStatus(HttpStatus.SC_CONFLICT);//409 其实应该返回429
            httpServletResponse.getWriter().write("网络太拥挤稍后在使用");
            httpServletResponse.getWriter().flush();
            return;
        }
    }
}
