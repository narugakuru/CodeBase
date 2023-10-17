package com.raisei.bootdemo01.controller;

import com.raisei.bootdemo01.bean.AccountBean;
import com.raisei.bootdemo01.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MybatisTestController {

    @Autowired
    AccountService accountService;

    @ResponseBody
    @GetMapping("/acct")
    public AccountBean getAcct(@RequestParam("id") Integer id){
        return accountService.getAcct(id);
    }

    @ResponseBody
    @PostMapping("/insertAcct")
    public Integer insertAcct(AccountBean accountBean){
        return accountService.insertAcct(accountBean);
    }

}
