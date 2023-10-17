package com.raisei.hello.Controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MatrixController {
    
    @ResponseBody
    @GetMapping("/user/{path}")
    public Map<String,Object> getMatrix(@MatrixVariable("age") Integer age,
                                        @MatrixVariable("name") String name){
        Map<String, Object> map = new HashMap<>();
        map.put("age",age);
        map.put("name",name);
        return map;
    }


    @GetMapping("/request")
    public String goToPage(HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("msg1","ya ta ze!!");
        httpServletRequest.setAttribute("msg2",114514);
        return "forward:/success";
    }

    @ResponseBody//返回json字符串
    @GetMapping("/success")
    public Map<String,Object> toSuccess(@RequestAttribute("msg1") String msg1,
                                        @RequestAttribute("msg2") Integer msg2){
        Map<String, Object> map = new HashMap<>();
        map.put("msg1",msg1);
        map.put("mgs2",msg2);
        return map;
    }
}
