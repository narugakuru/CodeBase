package 面向对象练习;
/*• 2.2 定义一个Vehicle类的子类轿车类Car，要求如下：
• 2.2.1 轿车有自己的属性载人数loader（int 类型）。
• 2.2.2 提供该类初始化属性的构造方法。
• 2.2.3 重新定义run()，用打印语句描述轿车奔跑的功能。
• 2.2.4 在main方法中创建一个品牌为―Honda‖、颜色为―red‖，载人数为2人的轿车。*/
public class Car extends Vehicle{
    private int loader;


    public Car(String brand, String color, double speed, int loader) {
        super(brand, color, speed);
        this.loader = loader;
    }


/*    public void run(){
        System.out.println(getBrand()+"时速为："+getSpeed());
    }*/

}
