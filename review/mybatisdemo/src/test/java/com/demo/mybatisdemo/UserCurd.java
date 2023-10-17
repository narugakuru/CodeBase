package com.demo.mybatisdemo;

import com.demo.mybatisdemo.entity.User;
import com.demo.mybatisdemo.mapper.UserMapper;
import com.demo.mybatisdemo.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.demo.mybatisdemo.mapper")
public class UserCurd {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserService iUserService;

    @Test
    public void query(){
        List<User> list = userMapper.selectList(null);
        System.out.println(list);
    }

    @Test
    public void serviceQuery(){
        List<User> list = iUserService.list();
        System.out.println(list);

        List<Map<String, Object>> maps = iUserService.listMaps();
        System.out.println(Arrays.toString(maps.toArray()));
    }
}
