package com.itheima.controller;

import com.itheima.pojo.AliyunOSSProperties;
import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.multi.MultiMenuItemUI;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    /**
     * 本地磁盘上传文件的方案
     */
    /* @PostMapping("/upload")
    public Result upload(String name, Integer age , MultipartFile file) throws IOException {
        log.info("接收到的参数：{},{},{}",name,age,file);
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();

        //基于UUID生成新的文件名
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String newFileName = UUID.randomUUID().toString() + extension;
        //转存文件
        file.transferTo(new File("D:/images/" + newFileName));
        return Result.success();
    }*/
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}",file);
        //将文件交给阿里云OSS存储管理
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传OSS后的url:{}",url);
        return Result.success(url);
    }
}
