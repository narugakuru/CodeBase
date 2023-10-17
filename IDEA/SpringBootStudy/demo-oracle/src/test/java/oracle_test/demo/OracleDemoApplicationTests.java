package oracle_test.demo;

import oracle_test.demo.bean.Dept;
import oracle_test.demo.bean.SalGrade;
import oracle_test.demo.bean.Sc;
import oracle_test.demo.mapper.DeptMapper;
import oracle_test.demo.mapper.SalGradeMapper;
import oracle_test.demo.mapper.ScoreMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class OracleDemoApplicationTests {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private SalGradeMapper salGradeMapper;

    @Test
    void contextLoads() {
        Dept dept = deptMapper.selectById(10);
        System.out.println(dept.toString());
    }

    @Test
    void sc(){
        Sc sc = scoreMapper.selectById(1);
        System.out.println(sc.toString());
    }

    @Test
    void salgrade(){
        SalGrade salGrade = salGradeMapper.selectById(1);
        System.out.println(salGrade.toString());
    }

    @Test
    public void insert_score() {
        Random random = new Random();
        for (int i = 0; i < 35; i++) {
            Sc sc = new Sc();
            //赋随机值,学号1-5，课程号1-10，成绩40-100，无key
            int sid = random.nextInt(5)+1;
            int cid = random.nextInt(10) + 1;
            float score = (float) (random.nextInt(60) + 40);//40~100
            sc.setSid(sid);
            sc.setCid(cid);
            sc.setScore(score);
            System.out.println(sc.toString());

            int insert = scoreMapper.insertAll(sc);
            System.out.println(insert);
        }

    }

}
