package Company多态练习;

//• 4.4 SalesEmployee ：ColaEmployee 的子类，销售人员，工资由月销售额和提成率决定。属性：月销售额、提成率
public class SalesEmployee extends ColaEmployee {
    private double monthSalasVolemu;
    private double pushMoney = 0.05;//提成

    @Override
    public String toString() {
        return "SalesEmployee{" +
                "monthSalasVolemu=" + monthSalasVolemu +
                ", pushMoney=" + pushMoney +
                '}';
    }

    public SalesEmployee(String name, int month, double monthSalasVolemu) {
        super(name, month);
        this.monthSalasVolemu = monthSalasVolemu;
    }

    public double getSalary(int month) {
        if (this.getMonth() == month)
            return monthSalasVolemu * pushMoney + 100;
        else
            return monthSalasVolemu * pushMoney;
    }

}
