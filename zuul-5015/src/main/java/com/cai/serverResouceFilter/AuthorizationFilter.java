/**
 * 
 */
package com.cai.serverResouceFilter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

/**
 * 授权过滤器器
 *
 */
@Slf4j
@Component
public class AuthorizationFilter extends ZuulFilter {

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		log.info("授权验证 authorization start");
		
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		//如果需要认证
		if(isNeedAuth(request)) {
			
			TokenInfo tokenInfo = (TokenInfo)request.getAttribute("tokenInfo");
			//判断这个令牌是不是有效的
			if(tokenInfo != null && tokenInfo.isActive()) {
				if(!hasPermission(tokenInfo, request)) {
					log.info("audit log update fail 403");
					handleError(403, requestContext);
				}
				//设置用户信息
				requestContext.addZuulRequestHeader("username", tokenInfo.getUser_name());
			}else {
				if(!StringUtils.startsWith(request.getRequestURI(), "/token")) {
					log.info("audit log update fail 401");
					handleError(401, requestContext);
				}
			}
		}
		
		return null;
	}
	
	private void handleError(int status, RequestContext requestContext) {
		requestContext.getResponse().setContentType("application/json");
		requestContext.setResponseStatusCode(status);
		requestContext.setResponseBody("{\"message\":\"auth fail\"}");
		requestContext.setSendZuulResponse(false);
	}

	private boolean hasPermission(TokenInfo tokenInfo, HttpServletRequest request) {
		return true; //RandomUtils.nextInt() % 2 == 0;
	}
   //权限验证
	private boolean isNeedAuth(HttpServletRequest request) {
		return true;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 3;
	}

}
