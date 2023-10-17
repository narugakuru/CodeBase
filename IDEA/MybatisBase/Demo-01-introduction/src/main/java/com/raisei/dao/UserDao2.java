package com.raisei.dao;

import com.raisei.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao2 {
    List<User> findByCondition(User user);
    List<User> findAll();
    List<User> findByTrim(User user);
    List<User> findByWhere(User user);

    List<User> findByFor(@Param("ids")Integer[] ids);

    List<User> findByChose(User user);
}
