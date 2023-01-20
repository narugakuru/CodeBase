package Company多态练习;
//• 4.3 HourlyEmployee ：ColaEmployee 的子类，按小时拿工资的员工，每月工作超出160 小时的部分按照1.5 倍工资发放。
//        属性：每小时的工资、每月工作的小时数
public class HourlyEmployee extends ColaEmployee{
    private double monthworktime;
    private final double hourlyPay = 50;

    @Override
    public String toString() {
        return "HourlyEmployee{" +
                "monthworktime=" + monthworktime +
                ", hourlyPay=" + hourlyPay +
                '}';
    }

    public double getMonthworktime() {
        return monthworktime;
    }

    public double getHourlyPay() {
        return hourlyPay;
    }

    public HourlyEmployee(String name, int month, double monthworktime) {
        super(name, month);
        this.monthworktime = monthworktime;
    }

    public double getSalary(int month) {
        if(this.getMonth()==month) {
            if (monthworktime <= 160)
                return hourlyPay * monthworktime+100;
            else
                return hourlyPay * 160 + hourlyPay * (monthworktime - 160)+100;
        }
        else {
            if (monthworktime <= 160)
                return hourlyPay * monthworktime;
            else
                return hourlyPay * 160 + hourlyPay * (monthworktime - 160);
        }
    }

}
