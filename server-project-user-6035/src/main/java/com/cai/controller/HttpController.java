package com.cai.controller;

import com.cai.utilEntity.MessageBox;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * 多线程异步处理请求服务
 */
@RestController
@Slf4j
@EnableSwagger2
public class HttpController {
    /**
     * 方式1
     * @return
     */
    @GetMapping("/httpCallable/{id}")
    @ApiOperation(value = "多线程测试接口")
    public Callable<MessageBox> httpCallable(@ApiParam(value = "用户id") @PathVariable(value = "id") String id){
      log.info("主线程开启：{}",new Date().getTime());
        Callable<MessageBox> callable = new Callable<MessageBox>() {
            @Override
            public MessageBox call() throws Exception {
                log.info("副线程开启：{}",new Date().getTime());
                Thread.sleep(2000);
                log.info("副线程关闭：{}",new Date().getTime());
                return MessageBox.build("100","ok Json","副线程处理完成");
            }
        };
        log.info("主线程关闭：{}",new Date().getTime());
        return callable;
    }
}
