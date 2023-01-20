package com.raisei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class restfulHello {

    @RequestMapping(value="/restful",method = RequestMethod.GET)
    public String test(Model model){

        model.addAttribute("msg","get");
        return "hello";
    }

    @PostMapping("/restful2")
    public String test2(Model model){

        model.addAttribute("msg","post");
        return "test";
    }
}
