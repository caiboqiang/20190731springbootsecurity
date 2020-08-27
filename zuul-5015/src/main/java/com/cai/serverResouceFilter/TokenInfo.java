package com.cai.serverResouceFilter;

import lombok.Data;

import java.util.Date;

@Data
public class TokenInfo {
    /**
     * bean是不是可用的 激活的
     */
    private boolean active;
    /**
     * 客户端应用id
     */
    private String client_id;
    /**
     * 权限
     */
    private String[] scope;

    private String user_name;
    /**
     * 可访问的id
     */
    private String[] aud;
    /**
     * token的过期时间
     */
    private Date exp;
    /**
     * 令牌对应的权限 用户的
     */
    private String[] authorities;
}
