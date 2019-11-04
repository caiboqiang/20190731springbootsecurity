package com.cai.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 自定义用户认证
 */
@Component
@Slf4j
public class MyUserDetailsServiceImpl implements UserDetailsService {
    //根据用户名查询用户信息

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息MyUserDetailsServiceImpl
        log.info("=======用户名：{}==========",username);
        //根据用户账号查询用户信息是否被冻结
        boolean t = true;
        //用户没有过期
        boolean tf = true;
        System.out.println(username);
        return new User(username,"123456",true,true,true,true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
