package com.cai.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 封装配置 这个类会读取所有一login开头的配置项
 */
@ConfigurationProperties(prefix = "security")
@Data
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();

}
