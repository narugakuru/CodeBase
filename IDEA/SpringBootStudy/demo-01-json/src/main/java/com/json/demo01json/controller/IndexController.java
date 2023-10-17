package com.json.demo01json.controller;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


@Controller
@ResponseBody
public class IndexController {


    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/json1")
    public String json1(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        return "f**k you!!";
    }

    @GetMapping("/json2")
    public String json2(HttpServletResponse response) throws JSONException {
        response.setHeader("Access-Control-Allow-Origin", "*");

        JSONObject json = new JSONObject();
        json.put("id", 114514);
        json.put("name","jackson");
        json.put("value","fq!");
        return json.toString();

    }
}
