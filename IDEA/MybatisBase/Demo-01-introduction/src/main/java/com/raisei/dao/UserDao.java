package com.raisei.dao;

import com.raisei.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserDao {

    @Select("select * from user")
    List<User> findAll();

    @Select("select from user where id = #{id} and username = #{username}")
    User findByCondition(@Param("id") Integer id, @Param("username") String username);

    @Select("select * from user where id = #{id} and age = #{age}")
    User findByMap(Map map);

    //错误示范
    @Select("select * from user where id = #{id} and username = #{username}")
    User findByTwo(Integer id, String username);

    //增删改
    @Insert("insert into user values (null, #{username}, #{age}, #{address})")
    void insertUser(User user);

    @Delete("delete from user where id=#{id}")
    void deleteUser(User user);

    @Update("update  user set age=#{age} , username = #{username} where id = #{id}")
    void updateUser(User user);

}
