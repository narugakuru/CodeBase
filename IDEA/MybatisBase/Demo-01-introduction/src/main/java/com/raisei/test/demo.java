package com.raisei.test;

import com.raisei.dao.StudentDao;
import com.raisei.dao.UserDao;
import com.raisei.dao.UserDao2;
import com.raisei.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class demo {
    public static void main(String[] args) throws IOException {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        UserDao2 userDao2 = session.getMapper(UserDao2.class);

        User user = new User(1, null, null, null);
        System.out.println(userDao2.findByChose(user));
      /*  List<User> user = userDao2.findByWhere(new User(1,null,null,null));
        System.out.println(user);
*/
//        Integer[] ids = {1,2,3};
//        System.out.println(userDao2.findByFor(ids));


/*//        StudentDao studentDao = session.getMapper(StudentDao.class);

        UserDao userDao = session.getMapper(UserDao.class);

        userDao.insertUser(new User(null,"aibo",35,"上海"));

        List<User> all = userDao.findAll();
        System.out.println(all);

        userDao.updateUser(new User(4,"Raisei",20,"南昌"));
//        session.commit();

        Map map = new HashMap();
        map.put("id",1);
        map.put("age",19);
        User byMap = userDao.findByMap(map);
        System.out.println(byMap);*/

        session.close();
    }

}
