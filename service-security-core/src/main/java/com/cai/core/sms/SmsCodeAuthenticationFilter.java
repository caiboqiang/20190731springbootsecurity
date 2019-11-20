package com.cai.core.sms;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤器
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public static final String SPRING_SECURITY_FORM_PHONE_KEY = "phone";
    //public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    private String phoneParameter = "phone";
    //private String passwordParameter = "password";
    private boolean postOnly = true;

    public SmsCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher("/authentication/phone", "POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("认证方法不支持当前的请求： " + request.getMethod());
        } else {
            String phone = this.obtainUsername(request);
            //String password = this.obtainPassword(request);
            if (phone == null) {
                phone = "";
            }

            /*if (password == null) {
                password = "";
            }*/
            //去掉空格
            phone = phone.trim();
            //UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            //用自己的
            SmsCodeAuthenticationToken smsCodeAuthenticationToken = new SmsCodeAuthenticationToken(phone,null);
            this.setDetails(request, smsCodeAuthenticationToken);
            return this.getAuthenticationManager().authenticate(smsCodeAuthenticationToken);
        }
    }

    /**
     * 获取密码
     * @param request
     * @return
     */
    /*protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(this.passwordParameter);
    }*/

    /**
     * 获取用户名
     * @param request
     * @return
     */
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(this.phoneParameter);
    }

    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setUsernameParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.phoneParameter = usernameParameter;
    }

    /*public void setPasswordParameter(String passwordParameter) {
        Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
        this.passwordParameter = passwordParameter;
    }*/

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getUsernameParameter() {
        return this.phoneParameter;
    }

    /*public final String getPasswordParameter() {
        return this.passwordParameter;
    }*/
}