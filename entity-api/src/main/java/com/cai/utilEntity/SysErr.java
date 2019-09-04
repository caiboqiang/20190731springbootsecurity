package com.cai.utilEntity;

/**
 * 系统异常处理
 */
public class SysErr extends RuntimeException {
    public SysErr(){
        super("系统异常");
    }
}
