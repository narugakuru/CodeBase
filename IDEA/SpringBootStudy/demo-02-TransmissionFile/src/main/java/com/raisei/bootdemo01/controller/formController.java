package com.raisei.bootdemo01.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
public class formController {

    @GetMapping("/form_layouts")
    public String toForm(){
        return "form/form_layouts";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String userName,
                         @RequestPart("headerImg") MultipartFile headerImg,
                         @RequestPart("photos") MultipartFile[] photos) throws IOException {

        log.info("上传信息：email={}，username={}，headerImg={}byte，photos={}",email,userName,headerImg.getSize(),photos.length);
        //判断文件是否为空
        if (photos.length>0){
            for (MultipartFile photo : photos) {
                //            保存到文件服务器，OSS服务器
                String originalFilename = photo.getOriginalFilename();
                //将文件保存到目标目录
                photo.transferTo(new File("d://cache//"+originalFilename));
            }
        }
        return "main";
    }
}
