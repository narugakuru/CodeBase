package com.raisei.dao;

import com.raisei.pojo.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    User findById(Integer id);

    User findByUsername(String username);
}
