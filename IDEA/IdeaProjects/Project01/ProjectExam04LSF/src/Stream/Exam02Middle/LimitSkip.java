package Stream.Exam02Middle;

import java.util.ArrayList;

//取，跳
public class LimitSkip {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("add");
        list.add("start");
        list.add("abstract");
        list.add("android");


//    取前3个元素，输出
        list.stream().limit(3).forEach(System.out::println);
//    跳过3个元素，输出
        list.stream().skip(3).forEach(System.out::println);
//    跳过2个元素，剩下元素前1个输出
        list.stream().skip(2).limit(1).forEach(System.out::println);
    }
}
