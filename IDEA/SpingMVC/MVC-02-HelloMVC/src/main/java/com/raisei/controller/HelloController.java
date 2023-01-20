package com.raisei.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloController implements Controller {
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        ModelAndView mv = new ModelAndView();
//        封装对象到model里
        mv.addObject("msg","HelloSpringMVC!!");
//        封装跳转视图
        mv.setViewName("hello");//web-inf/jsp/hello.jsp
        return mv;
    }
}
