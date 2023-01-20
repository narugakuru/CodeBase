package oracle_test.demo.controller;

import oracle_test.demo.bean.Dept;
import oracle_test.demo.bean.SalGrade;
import oracle_test.demo.bean.Sc;
import oracle_test.demo.mapper.DeptMapper;
import oracle_test.demo.mapper.SalGradeMapper;
import oracle_test.demo.mapper.ScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private SalGradeMapper salGradeMapper;

    @Autowired
    private DeptMapper deptMapper;

    @GetMapping("/insert")
    @ResponseBody
    public String score_insert(Sc sc){
        int insert = scoreMapper.insert(sc);
        if (insert==0)
        {
            return "yes!!";
        }
        return "no!";
    }

    @GetMapping("/get")
    @ResponseBody
    public String score_select(Integer sid){
        Sc sc = scoreMapper.selectById(sid);
        return sc.toString();
    }

    @GetMapping("/get2")
    @ResponseBody
    public String salgrade_select(){
        SalGrade salGrade = salGradeMapper.selectById(10);
        return salGrade.toString();
    }

    @GetMapping("/get3")
    @ResponseBody
    public String dept_select(){
        Dept dept = deptMapper.selectById(10);
        System.out.println(dept.toString());
        return dept.toString();
    }

}
