package Company多态练习;
//• 4.2 SalariedEmployee ： ColaEmployee 的子类，拿固定工资的员工。属性：月薪
public class SalariedEmployee extends ColaEmployee{
    private double monthpay;

    @Override
    public String toString() {
        return "SalariedEmployee{" +
                "monthpay=" + monthpay +
                '}';
    }

    public double getSalary(int month){
        if(this.getMonth()==month)
            return (monthpay+100);
        else
            return monthpay;
    }
    public SalariedEmployee(String name, int month, double monthpay) {
        super(name, month);
        this.monthpay = monthpay;
    }

}
