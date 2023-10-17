package com.raisei.dao.user;

import com.raisei.pojo.User;

import java.sql.Connection;

public interface UserDaoM {
    User getLoginUser(String userCode);
}
