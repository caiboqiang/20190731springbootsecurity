package com.cai.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.http.*;
//import org.springframework.stereotype.Component;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * 继承ZuulFilter需要覆盖4个方法 认证拦截
// */
//@Slf4j
//@Component
//public class OAuthFilter extends ZuulFilter {
//    private RestTemplate restTemplate = new RestTemplate();
//    /**
//     * run()
//     * 过滤器类型 pre：在业务之前执行前   post：在业务之前执行后 error：异常时候执行  route 根据需要挑一个返回
//     * @return
//     */
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    /**
//     * 设置执行顺序 前面还有个限流
//     * @return
//     */
//    @Override
//    public int filterOrder() {
//        return 1;
//    }
//
//    /**
//     * 判断过滤器是否起作用 f t
//     * @return
//     */
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        //做服务认证
//        log.info("=====zuul======oauth start");
//        //用来获取请求和相应头数据 帮助我们拿到请求对象
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        //拿到当前请求
//        HttpServletRequest request = requestContext.getRequest();
//        if(StringUtils.startsWithIgnoreCase(request.getRequestURI(),"/token")){
//            return null;
//        }
//        //认证过程中不做处理
//        String authHeader= request.getHeader("Authorization");
//        if(StringUtils.isBlank(authHeader)){
//            return null;
//        }
//        //忽略大小写判断
//        if(!StringUtils.startsWithIgnoreCase(authHeader,"bearer ")){
//            return null;
//        }
//
//        try {
//            //开始获取token
//            TokenInfo info = getTokenInfo(authHeader);
//            request.setAttribute("tokenInfo",info);
//        }catch (Exception e){
//            log.info("token验证异常:{}",e);
//        }
//        return null;
//    }
//    private TokenInfo getTokenInfo(String authHeader){
//        String token = StringUtils.substringAfter(authHeader, "bearer ");
//        String oauthServiceUrl = "http://localhost:5056/oauth/check_token";
//        //设置请求头信息
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.setBasicAuth("gateway", "123456");
//        //设置token 只能用MultiValueMap
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("token", token);
//        //组装请求实体
//        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
//        //发送请求
//        ResponseEntity<TokenInfo> response = restTemplate.exchange(oauthServiceUrl, HttpMethod.POST, entity, TokenInfo.class);
//
//        log.info("token info :" + response.getBody().toString());
//
//        return response.getBody();
//    }
//}
