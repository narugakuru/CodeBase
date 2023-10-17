package AllTestSystem;/*
//学号 姓名 性别 年龄 期中考试 期末考试
• 6.3定义一个可容纳5个学生的学生类数组，使用随机数给该数组装入各系学生的对象，然后按如下格式输出数组中的信息：
• 学号:XXXXXXXX 姓名：XXX 性别：X 年龄：XX 综合成绩：XX*/
public abstract class AllGradeSystem implements Department{
    public static void print(){
        System.out.println("学号 "+"姓名  "+"性别 "+"年龄 "+"综合成绩");
        Department[] D =new Department[5];
        Department Computer =new ComputerDepartment("01","张三","男",19,
                99,1,100,10);
        D[0]=Computer;
        System.out.println(+D[0].getAllTest());
    }


    public static void main(String[] args) {
        print();
    }
}
