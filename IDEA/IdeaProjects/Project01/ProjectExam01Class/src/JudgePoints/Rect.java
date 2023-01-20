package JudgePoints;/*
（1）编写一个矩形类Rect，包含：
两个protected属性：矩形的宽width；矩形的高height。
两个构造方法：
1．一个带有两个参数的构造方法，用于将width和height属性初化；
2．一个不带参数的构造方法，将矩形初始化为宽和高都为10。
两个方法：
求矩形面积的方法area()
求矩形周长的方法perimeter()*/
public class Rect {
    protected double width;
    protected double height;

    public double Area(){//面积
        return width*height;
    }

    public double Perimeter(){//周长
        return (width+height)*2;
    }

    public Rect(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public void Rect() {
        this.width = 10;
        this.height = 10;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
}
