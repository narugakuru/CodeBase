package com.raisei.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raisei.pojo.User;
import com.raisei.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@Controller
public class test01 {

    @RequestMapping("/t1")
    @ResponseBody
    public String test01(String name, Model model) {
//        System.out.println(name);
        User user = new User("raisei",19,"nan");
        model.addAttribute("msg",name);
        System.out.println(user);
        return user.toString();
    }

    @GetMapping("/t2")
    @ResponseBody
    public String test02() throws JsonProcessingException {

        return JsonUtils.getJson(new Date());
    }
}
