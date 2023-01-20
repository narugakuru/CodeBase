package com.raisei.bootdemo01;


import com.raisei.bootdemo01.bean.User;
import com.raisei.bootdemo01.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;
/*

@Slf4j
@SpringBootTest
class BootDemo01ApplicationTests {

    @Autowired
    JdbcTemplate template;

    @Autowired
    UserMapper userMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoadsTest() {
        Long aLong = template.queryForObject("select count(*) from testdemo.user", Long.class);
        log.info("记录总数==>{}",aLong);
    }

    @Test
    void MybatisPlusTest(){
        User user = userMapper.selectById(1);
        log.info(user.toString());
    }

    @Test
    void getRedis(){
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("hello","world");

        System.out.println(stringStringValueOperations.get("hello"));
    }
}
*/
