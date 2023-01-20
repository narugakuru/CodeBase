package com.raisei.service;

import com.raisei.dao.user.UserDaoM;
import com.raisei.dao.user.UserDao;
import com.raisei.dao.user.UserDaoMImpl;
import com.raisei.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class UserServiceImpl implements UserService {
    //业务调用dao，引入dao层
    private UserDaoM userDaoM;

    public User login(String userCode, String password) {

        UserDaoMImpl userDaoM = new UserDaoMImpl();
        User loginUser = userDaoM.getLoginUser(userCode);

        //判断用户名是否存在
        if (loginUser != null) {
            //判断loginUser的密码是否正确
            if (password.equals(loginUser.getUserPassword())) {
                System.out.println("返回了loginUser");
                return loginUser;
            } else {
                System.out.println("loginUser返回了null");
                return null;
            }
        } else {
            System.out.println("loginUser返回了null");
            return null;
        }
    }

/*    //OK
    @Test
    public void Test() {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        User user = userServiceImpl.login("admin", "123456");
        System.out.println(user.toString());
    }*/
}
