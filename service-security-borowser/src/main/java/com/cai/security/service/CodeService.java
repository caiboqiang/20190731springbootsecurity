package com.cai.security.service;

import com.cai.utilEntity.MessageBox;

public interface CodeService {
    /**
     * 发送短信登入密码
     * @param phone
     * @return
     */
    MessageBox createSmsCode(String  phone);

}
