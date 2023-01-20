package Stream.Exam03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo2 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("add");
        list.add("start");
        list.add("abstract");
        list.add("android");

        List<String> there = list.stream().limit(3).collect(Collectors.toList());
        System.out.println(there);

        String[] strArray = {"林青霞,30", "张曼玉,35", "王祖贤,33", "柳岩,25"};

//        得到年龄数据大于28的流
        Stream<String> arrayStream = Stream.of(strArray).filter(s -> Integer.parseInt(s.split(",")[1]) > 28);
//        把Stream流操作完毕的数据收集到map集合中遍历，字符串中的姓名做key，年龄做value
        Map<String, String> collectMap = arrayStream.collect(Collectors.toMap(
                s -> s.split(",")[0], s -> s.split(",")[1]));

        for(String key:collectMap.keySet()){
            System.out.println(key+","+collectMap.get(key));
        }
    }

}
