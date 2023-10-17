package JudgePoints;/*
（2）通过继承Rect类编写一个具有确定位置的矩形类PlainRect，其确定位置用
矩形的左上角坐标来标识，包含：
添加两个属性：矩形左上角坐标startX和startY。
两个构造方法：
带4个参数的构造方法，用于对startX、startY、width和height属性初始化；
不带参数的构造方法，将矩形初始化为左上角坐标、长和宽都为0的矩形；
添加一个方法：
判断某个点是否在矩形内部的方法isInside(double x,double y)。如在矩
形内，返回true, 否则，返回false。
  提示：点在矩形类是指满足条件：
x>=startX&&x<=(startX+width)&&y<startY&&y>=(startY-height)*/

public class PlainRect extends Rect{
    protected double startX;//左上角坐标
    protected double startY;

    public boolean isInside(double x,double y){
        if(x>=startX&&x<=(startX+width)&&y<startY&&y>=(startY-height))
            return true;
        else
            return false;
    }
    public PlainRect() {
            super(0, 0);
            this.startX = 0;
            this.startY = 0;
        }

    public PlainRect(double width, double height, double startX, double srartY) {
        super(width, height);
        this.startX = startX;
        this.startY = srartY;
    }
}
