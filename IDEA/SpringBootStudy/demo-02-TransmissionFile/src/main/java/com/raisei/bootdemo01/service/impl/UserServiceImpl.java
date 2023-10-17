package com.raisei.bootdemo01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.raisei.bootdemo01.bean.User;
import com.raisei.bootdemo01.mapper.UserMapper;
import com.raisei.bootdemo01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    UserMapper userMapper;

    public void test() {
//        userMapper.delete();
    }
}
