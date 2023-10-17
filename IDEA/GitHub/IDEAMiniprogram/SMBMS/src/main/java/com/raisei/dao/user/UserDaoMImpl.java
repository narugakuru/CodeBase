package com.raisei.dao.user;

import com.raisei.dao.BaseDaoM;
import com.raisei.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class UserDaoMImpl {

    public User getLoginUser(String userCode) {

        SqlSession session = BaseDaoM.getSession();
        UserDaoM userDaoM = session.getMapper(UserDaoM.class);
        User loginUser = userDaoM.getLoginUser(userCode);
        BaseDaoM.closeSession(session);//不是这个问题
        return loginUser;
    }

}
