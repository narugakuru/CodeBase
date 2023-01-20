package Company多态练习;/*
4.1 ColaEmployee ：这是所有员工总的父类，属性：员工的姓名,员工的生日月份。方法：getSalary(int month)
        根据参数月份来确定工资，如果该月员工过生日，则公司会额外奖励100 元。*/
public class ColaEmployee {
    private String name;
    private int month;

    public ColaEmployee(String name, int month) {
        this.name = name;
        this.month = month;
    }
    public String getName() {
        return name;
    }
    public int getMonth() {
        return month;
    }

    public double getSalary(int month) {//奖金月
        return 100;
    }


}
