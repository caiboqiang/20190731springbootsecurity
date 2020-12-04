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
	//判断这个过滤器是不是要起作用，true 永远起作用
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		log.info("授权验证 authorization start");
        //获取请求和响应的 来处理请求头的信息
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		//判断是否是 toke开头的请求 不需要做认证
		if(StringUtils.startsWith(request.getRequestURI(),"/token")){
			return null;
        }

		String authorizationHeader = request.getHeader("Authorization");
		if(StringUtils.isBlank(authorizationHeader)){
			return null;
		}
		//如果需要认证
		if(isNeedAuth(request)) {
			TokenInfo tokenInfo = (TokenInfo)request.getAttribute("tokenInfo");
			//判断这个令牌是不是有效的
			if(tokenInfo != null && tokenInfo.isActive()) {
				//是否有权限
				if(!hasPermission(tokenInfo, request)) {
					log.info("是否有权限 audit log update fail 403");
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
    //报错处理
	private void handleError(int status, RequestContext requestContext) {
		requestContext.getResponse().setContentType("application/json");
		requestContext.setResponseStatusCode(status);
		requestContext.setResponseBody("{\"message\":\"auth fail 认证失败\"}");
		requestContext.setSendZuulResponse(false); // 不要往下走
	}

	private boolean hasPermission(TokenInfo tokenInfo, HttpServletRequest request) {
		return true; //RandomUtils.nextInt() % 2 == 0;
	}
   //权限验证
	private boolean isNeedAuth(HttpServletRequest request) {
		return true;
	}
    //过滤器的类型 pre：在run业务逻辑之前会执行这个过滤器   post：在run业务逻辑之后会执行这个过滤器  err：在run业务逻辑抛出异常执行这个过滤器 route
	@Override
	public String filterType() {
		return "pre";
	}
	//控制过滤器执行的顺序
	@Override
	public int filterOrder() {
		return 3;
	}

}
