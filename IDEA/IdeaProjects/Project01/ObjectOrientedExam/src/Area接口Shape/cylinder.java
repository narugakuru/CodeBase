package Area接口Shape;

public class cylinder extends circle implements Shape{
    private final double pi=3;
    private double radius;
    private double high;

    @Override
    public double getRadius() {
        return radius;
    }

    public double getHigh() {
        return high;
    }

    //为什么不能没有构造函数？？？
    public cylinder(double radius, double radius1, double high) {
        super(radius);
        this.radius = radius1;
        this.high = high;
    }

    public double getArea(){
            return (pi*radius*radius*2+high*2*pi*radius);
    }

}
