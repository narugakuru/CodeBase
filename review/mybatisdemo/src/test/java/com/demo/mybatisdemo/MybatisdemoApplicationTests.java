package com.demo.mybatisdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.mybatisdemo.entity.User;
import com.demo.mybatisdemo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


@Slf4j
@SpringBootTest
@MapperScan("com.demo.mybatisdemo.mapper")
@RunWith(SpringRunner.class)
public class MybatisdemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        System.out.println(userMapper.selectList(null));
    }

    @Test
    public void insert(){
        User user = new User();
        user.setUsername("三更草堂222");
        user.setPassword("7777");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    //模糊查询
    @Test
    public void wap(){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.like("username","三更");
        List<User> list = userMapper.selectList(wrapper);
        System.out.println(list);
    }
}
