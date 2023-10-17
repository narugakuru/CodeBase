package Stream.Exam01Generation;

import java.util.*;
import java.util.stream.Stream;
/*
    Stream流常见3类生成方式：
*/
public class Generation {
    public static void main(String[] args) {
//        Collection体系可使用默认方法生成流
        List<String> list =new ArrayList<>();
        Stream<String> listStream =list.stream();

        Set<String> set =new HashSet<>();
        Stream<String> setStream = set.stream();
//        Map体系的集合间接生成流
        Map<String,Integer> map =new HashMap<>();
        Stream<String> keyStream = map.keySet().stream();
        Stream<Integer> valueStream = map.values().stream();
        Stream<Map.Entry<String, Integer>> entryStream = map.entrySet().stream();

//        数组通过Stream接口的静态方法生成流
        String[] strArray = {"hello","fuck","you"};
        Stream<String> strArrayStreamOf = Stream.of(strArray);
        Stream<String> strArrayStreamOf2 = Stream.of("fuck","baby","for","even");
        Stream<Integer> integerStream = Stream.of(114,514,233);
    }
}
