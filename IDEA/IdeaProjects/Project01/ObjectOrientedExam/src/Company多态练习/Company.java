package Company多态练习;

/*• 4.5 定义一个类Company，在该类中写一个方法，调用该方法可以打印出某月某个员工的工资数额，
    写一个测试类TestCompany,在main方法，把若干各种类型的员工放在一个ColaEmployee 数组里，并单元出数组中每个员工当月的工资。*/
public class Company {

    static void monthPaySout(){
        int month = 5;
        SalesEmployee SE =new SalesEmployee("SE1",month,100000);//销售人员提成
        SalariedEmployee SDE =new SalariedEmployee("SDE1",month,6000);//固定工资
        HourlyEmployee HE =new HourlyEmployee("HE1",month,165);//小时工

        System.out.println("----------------");
        System.out.println(SE.toString());
        System.out.println("----------------");
        System.out.println(SE.getName()+"的"+month+"月工资为："+SE.getSalary(month));
        System.out.println("----------------");
        System.out.println(HE.getName()+"的"+month+"月工资为："+HE.getSalary(month));
        System.out.println("----------------");
        System.out.println(SDE.getName()+"的"+month+"月工资为："+SDE.getSalary(month));
    }

    static void TestCompany() {//储存至数组

    }

    public static void main(String[] args) {
        monthPaySout();//输出工资

    }


}
