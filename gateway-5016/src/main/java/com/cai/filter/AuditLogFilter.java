/**
 *
 */
package com.cai.filter;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

//import javax.servlet.http.HttpServletRequest;
//
///**
// * 审计日志
// */
//@Slf4j
//@Component
//public class AuditLogFilter extends ZuulFilter {
//
//	@Override
//	public boolean shouldFilter() {
//		return true;
//	}
//
//	@Override
//	public Object run() throws ZuulException {
//		log.info("审计日志：在请求插入数据 相应时候跟新数据..");
//		RequestContext requestContext = RequestContext.getCurrentContext();
//		//拿到当前请求
//		HttpServletRequest request = requestContext.getRequest();
//		return null;
//	}
//
//	@Override
//	public String filterType() {
//		return "pre";
//	}
//
//	@Override
//	public int filterOrder() {
//		return 2;
//	}
//
//}
