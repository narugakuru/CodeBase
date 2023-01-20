package com.raisei.service;

import com.raisei.pojo.User;

public interface UserService {
    public User login(String userCode, String password);
}
