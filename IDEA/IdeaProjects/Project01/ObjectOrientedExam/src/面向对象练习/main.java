package 面向对象练习;
//• 2.1.5 在main方法中创建一个品牌为―benz‖、颜色为―black‖的汽车
//• 2.2.4 在main方法中创建一个品牌为―Honda‖、颜色为―red‖，载人数为2人的轿车。
public class main {
    public static void main(String[] args) {
        Vehicle benz = new Vehicle("benz","black",233);
        Car Honda = new Car("Honda","red",555,2);

        benz.run();
        Honda.run();
        Honda.setSpeed(666);
        Honda.run();
    }
}
