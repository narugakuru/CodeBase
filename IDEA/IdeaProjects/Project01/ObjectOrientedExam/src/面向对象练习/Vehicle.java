package 面向对象练习;
/*
1、设计2个类，要求如下：（知识点：类的继承 方法的覆盖） [必做题]
• 2.1 定义一个汽车类Vehicle，
• 2.1.1 属性包括：汽车品牌brand（String类型）、颜色color（String类型）和速度speed（double类型）。/
• 2.1.2 至少提供一个有参的构造方法（要求品牌和颜色可以初始化为任意值，但速度的初始值必须为0）。/
• 2.1.3 为属性提供访问器方法。注意：汽车品牌一旦初始化之后不能修改。/
• 2.1.4 定义一个一般方法run()，用打印语句描述汽车奔跑的功能
• 2.1.5 在main方法中创建一个品牌为―benz‖、颜色为―black‖的汽车
*/
public class Vehicle {
    private String brand;
    private String color;
    private double speed =0;


    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void run(){
        System.out.println(brand+"`s speed is "+speed);
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public double getSpeed() {
        return speed;
    }

    public Vehicle(String brand, String color, double speed) {
        this.brand = brand;
        this.color = color;
        this.speed = speed;
    }

}
