package JudgePoints;

/*（3）编写PlainRect类的测试程序
创建一个左上角坐标为（10，10），长为20，宽为10的矩形对象；
计算并打印输出矩形的面积和周长；
判断点(25.5，13)是否在矩形内，并打印输出相关信息。*/
public class PlainRectDemo{

    public static void Print10() {
        PlainRect P10 = new PlainRect(10, 10, 20, 10);
        System.out.println("面积：" + P10.Area() + " 周长:" + P10.Perimeter());
        System.out.println("-----------");
        System.out.println("点（25.5，13）是否在矩形内？"+P10.isInside(25.5,13));
    }

    public static void main(String[] args) {
        Print10();

    }
}
