package com.cai.core.validate.code;

import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * 验证码类
 */
@Data
public class ImageCode {
    private BufferedImage image;
    private String code;
    private Long date = 120L;
}
