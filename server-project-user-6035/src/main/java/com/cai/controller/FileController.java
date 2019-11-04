package com.cai.controller;

import com.cai.redis.RedisService;
import com.cai.utilEntity.ConsJson;
import com.cai.utilEntity.MessageBox;
import com.cai.utilEntity.ResultValue;
import com.cai.utilEntity.utli;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 文件上传
 */
@Slf4j
@RestController
public class FileController {

    @Autowired
    private RedisService redisService;
    @PostMapping(value = "UpLode")
    @ApiOperation(value = "文件上传接口")
    public String UpLode(MultipartFile[] files) throws Exception {
        //log.info("文件名：{}",file.getName());
        //log.info("原始文件名：{}",file.getOriginalFilename());
        //log.info("获取文件尺寸：{}",file.getSize());
        String path = "";//文件存放位置
        //new File(path,new Date(.getTime()+))
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        // 上传目录地址
        //String uploadDir = pathFile;//request.getSession().getServletContext().getRealPath("/") + "upload/";
        String uploadDir = "";//request.getSession().getServletContext().getRealPath("/") + "upload/";
        // 如果目录不存在，自动创建文件夹
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdir();
        }
        Map<String,String> map = null;
        // 遍历文件数组执行上传
        for (int i = 0; i < files.length; i++) {
            if (files[i] != null) {
                // 调用上传方法
                map = utli.executeUpload(uploadDir, files[i]);
                list.add(map);
            }

        }
        if (files.length == 1) {
            log.info("===========图片上传成功==========");
            return ConsJson.Object2StringJson(new ResultValue("SERVICE-USER", 0, "图片上传成功", map));
        } else {
            log.info("===========图片上传成功==========");
            return ConsJson.Object2StringJson(new ResultValue("SERVICE-USER", 0, "图片上传成功", list));
        }
    }
    @GetMapping(value = "/getRedis/{code}")
    public Callable<MessageBox> getRedis(@PathVariable("code") String code){
        Callable<MessageBox> callable = new Callable<MessageBox>() {
            @Override
            public MessageBox call() throws Exception {
                Object o = redisService.get(code);
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
