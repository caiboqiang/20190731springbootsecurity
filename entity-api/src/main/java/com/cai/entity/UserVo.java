package com.cai.entity;

import lombok.Data;
/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;*/

;import java.util.Collection;

@Data
public class UserVo {//implements UserDetails
    private Long id;
    private String username;
    private String password;

    //权限的
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        //将字符串转成集合返回
//        return AuthorityUtils.commaSeparatedStringToAuthorityList ( "ROLE_ADMIN" );
//    }
//    //用户是否过期
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//    //用户账号是否被锁定
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//   //密码是否过期
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//    //账号是否可用的
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
