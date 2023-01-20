package com.raisei.hello.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewTestController {

    @GetMapping("/thy")
    public String hello(Model model){
        model.addAttribute("msg","fq");
        model.addAttribute("msg2","olixie");
        model.addAttribute("link","http://www.baidu.com");
        return "helloThymeleaf";
    }

}
