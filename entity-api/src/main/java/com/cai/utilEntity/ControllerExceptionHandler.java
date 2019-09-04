package com.cai.utilEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 控制器的异常处理器:用来处理自定义异常
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(SysErr.class)
    public MessageBox handlerSysErr(SysErr sysErr){
        return MessageBox.build("100","系统异常",sysErr.getMessage());
    }
}
