package com.cai.controller;

import com.cai.utilEntity.ConsJson;
import com.cai.utilEntity.ResultValue;
import com.cai.utilEntity.utli;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件上传
 */
@Slf4j
@RestController
public class FileController {
    @PostMapping(value = "UpLode")
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
}
