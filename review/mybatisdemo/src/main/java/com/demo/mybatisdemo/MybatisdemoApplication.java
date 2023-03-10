package com.demo.mybatisdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.demo.mybatisdemo.mapper")
@SpringBootApplication
public class MybatisdemoApplication {

    public static void main(String[] args)  {
        SpringApplication.run(MybatisdemoApplication.class, args);
    }

}
