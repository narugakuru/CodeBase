package Stream.Exam02Middle;

import java.util.ArrayList;
import java.util.stream.Stream;

//合并，去除重复元素
public class ConcatDistinct {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("add");
        list.add("start");
        list.add("abstract");
        list.add("android");
//    取3
        Stream<String> limit = list.stream().limit(3);
//    跳2
        Stream<String> skip = list.stream().skip(2);
//    合并1，2，输出
//        Stream.concat(limit,skip).forEach(System.out::println);
//    不能出现两个concat(limit,skip)??
//    合并1，2，字符串元素不可重复，输出
        System.out.println("----------------");
        Stream.concat(limit,skip).distinct().forEach(System.out::println);
    }
}
