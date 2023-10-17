package com.raisei.bootdemo01.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raisei.bootdemo01.bean.User;
import com.raisei.bootdemo01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller()
public class TableController {

    @Autowired
    UserService userService;

    @GetMapping("/basic")
    public String basicTable(){
        return "table/basic_table";
    }

    @GetMapping("/dynamic")
    public String dynamicTale(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
        //直接查询所有数据
//        List<User> list = userService.list();
//        model.addAttribute("users",list);
        //分页查询数据
        Page<User> userPage = new Page<>(pn,2);
        //分页查询的结果
        Page<User> page = userService.page(userPage,null);
        model.addAttribute("users",page);
        return "table/dynamic_table";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id,
                             @RequestParam(value = "pn",defaultValue = "1")Integer pn,
                             RedirectAttributes ra){
        userService.removeById(id);
        ra.addAttribute("pn",pn);
        return "redirect:/dynamic";
    }


    @GetMapping("/pricing")
    public String pricingTable(){
        return "table/pricing_table";
    }
    @GetMapping("/responsive")
    public String responsiveTable(){
        return "table/responsive_table";
    }

}
