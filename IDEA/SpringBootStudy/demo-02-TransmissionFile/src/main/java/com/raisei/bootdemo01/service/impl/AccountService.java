package com.raisei.bootdemo01.service.impl;

import com.raisei.bootdemo01.bean.AccountBean;
import com.raisei.bootdemo01.mapper.AccountMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountMapper accountMapper;

    public AccountBean getAcct(Integer id){

        return accountMapper.getAcct(id);
    }

    public Integer insertAcct(AccountBean accountBean){
        return accountMapper.insertAcct(accountBean);
    }
}
