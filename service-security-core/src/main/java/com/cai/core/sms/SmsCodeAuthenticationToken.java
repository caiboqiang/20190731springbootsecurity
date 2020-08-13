package com.cai.core.sms;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 手机短信登入认证  短信验证码的Token
 */
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 510L;
    //用来放认证信息的，在认证之前应该放手机号。认证成功之后放登入成功之后的用户。
    private final Object principal;
    //在UsernamePasswordAuthenticationToken放的事密码，在这是没有用的。
    //private Object credentials;

    public SmsCodeAuthenticationToken(Object phone, Object credentials) {
        super((Collection)null);
        this.principal = phone;
        //this.credentials = credentials;
        this.setAuthenticated(false);
    }

    public SmsCodeAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        //this.credentials = credentials;
        super.setAuthenticated(true);
    }

    public Object getCredentials() {
        //return this.credentials;
        return null;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    public void eraseCredentials() {
        super.eraseCredentials();
        //this.credentials = null;
    }
}
