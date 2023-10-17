package com.raisei.hello.Controller;

import com.raisei.hello.bean.Car;
import com.raisei.hello.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController//返回字符串
public class helloController {

    @Autowired
    Car car;

    @Qualifier(value="user22-com.raisei.hello.bean.User")
    @Autowired
    User user;

    @RequestMapping("/car")
    public Car seeCar(){
        return car;
    }

    @RequestMapping("/users")
    public User seeUser(){
        return user;
    }

    @RequestMapping("/hello2")
    public String hello(){
        return "hello,SpringBoot";
    }




}
