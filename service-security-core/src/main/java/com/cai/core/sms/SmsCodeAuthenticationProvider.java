package com.cai.core.sms;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 短信登入的业务处理
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailsService;
    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * 身份认证的 业务处理方法
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("短信认证");
        SmsCodeAuthenticationToken smsCodeAuthenticationToken = (SmsCodeAuthenticationToken)authentication;
        //(String) smsCodeAuthenticationToken.getPrincipal()
        UserDetails user = userDetailsService.loadUserByUsername(authentication.getName());
        if (user == null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        //构造个新的认证信息
        SmsCodeAuthenticationToken smsCodeAuthenticationToken1 = new SmsCodeAuthenticationToken(user,user.getAuthorities());
        smsCodeAuthenticationToken1.setDetails(smsCodeAuthenticationToken.getDetails());
        return smsCodeAuthenticationToken1;
    }

    /**
     *
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        //判断传进来的是不是我们需要的SmsCodeAuthenticationToken这个类型的
        return SmsCodeAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
