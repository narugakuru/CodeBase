package raisei.airline;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import raisei.airline.dao.UserMapper;

@SpringBootTest
class AirlineApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println("id为1的是==>"+userMapper.selectList(null));
//        System.out.println(userMapper.);
    }

}
