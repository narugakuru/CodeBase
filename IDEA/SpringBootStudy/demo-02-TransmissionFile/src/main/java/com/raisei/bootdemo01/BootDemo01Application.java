package com.raisei.bootdemo01;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan(basePackages = "com.raisei.bootdemo01.mapper")
@ServletComponentScan(basePackages = "com.raisei.bootdemo01.servlet")
@SpringBootApplication
@EnableAdminServer
public class BootDemo01Application {
    public static void main(String[] args) {
        SpringApplication.run(BootDemo01Application.class, args);
    }
}
