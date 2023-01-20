package com.raisei.bootdemo01.controller;



import com.raisei.bootdemo01.bean.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class indexController {

    @Autowired
    JdbcTemplate template;

    @Autowired
    StringRedisTemplate redisTemplate;

    @ResponseBody
    @GetMapping("/sql")
    public String getSql(){
        Long aLong = template.queryForObject("select count(*) from testdemo.user", Long.class);
        return  aLong.toString();
    }


    @GetMapping(value = {"/","/login"})
    public String toIndex(){

        return "login";
    }
    /**
     *
     * @param user
     * @param session
     * @param model
     * @return
     */
    @PostMapping("/login")
    public String doLogin(User user, HttpSession session, Model model){
//        判断账号密码
        if (user.getUserName()!=null&&"123".equals(user.getPassword())){
            session.setAttribute("loginUser",user);
            return "redirect:/main.html";
        }else {
         model.addAttribute("msg","账号密码错误");
         return "login";
        }
    }

    /**
     *
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session, Model model){
        model.addAttribute("mainCount",redisTemplate.opsForValue().get("/main.html"));
        model.addAttribute("sqlCount",redisTemplate.opsForValue().get("/sql"));
        return "main";
    }
}
