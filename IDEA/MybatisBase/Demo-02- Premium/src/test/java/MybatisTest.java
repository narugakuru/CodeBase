import com.github.pagehelper.PageHelper;
import com.raisei.dao.OrderDao;
import com.raisei.dao.RoleDao;
import com.raisei.dao.UserDao;
import com.raisei.pojo.Order;
import com.raisei.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    private SqlSession sqlSession;

    //    执行main前，执行此方法
    @Before
    public void init() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        获取连接
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void testUserDao() {
        UserDao userDao = sqlSession.getMapper(UserDao.class);
//        System.out.println(userDao.findById(2));
        System.out.println("======================");
        User pdd = userDao.findByUsername("pdd");
        System.out.println(pdd.getId());
        System.out.println(pdd.getRoles());

    }

    @Test
    public void testOrderDao() {

        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        Order byId = orderDao.findById(1);//order的订单id
        System.out.println(byId);
    }

    @Test
    public void testRoleDao(){
        RoleDao roleDao = sqlSession.getMapper(RoleDao.class);
        System.out.println(roleDao.findRoleByUserId(2));

    }

    @Test
    public void testPageHelper(){
        UserDao user = sqlSession.getMapper(UserDao.class);
        //多少页，每页多少行
        PageHelper.startPage(1,2);
        List<User> all = user.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }

    }


//    测试之后执行此方法
    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }

}
