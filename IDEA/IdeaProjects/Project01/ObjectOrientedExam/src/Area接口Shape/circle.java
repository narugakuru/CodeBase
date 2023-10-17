package Area接口Shape;

class circle implements Shape{
    private final double pi=3.14;
    private double radius;


    public void setRadius(double radius) {
        this.radius = radius;
    }

    public circle(double radius) {//构造函数
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getArea(){
        return pi*radius*radius;
    }

    public double getCircumference(){
        return pi*2*radius;
    }

}
