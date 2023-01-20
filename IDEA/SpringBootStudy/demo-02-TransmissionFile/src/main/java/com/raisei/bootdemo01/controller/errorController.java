package com.raisei.bootdemo01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class errorController {

    @GetMapping("/error5")
    public String getError5xx() {
        int i = 10 / 0;
        return "error/4xx";
    }
}
