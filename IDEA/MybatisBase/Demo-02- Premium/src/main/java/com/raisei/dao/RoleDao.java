package com.raisei.dao;

import com.raisei.pojo.Role;

import java.util.List;

public interface RoleDao {
    //根据userId查询所具有的角色
    List<Role> findRoleByUserId(Integer userId);
}
