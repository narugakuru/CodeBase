package Stream.Exam02Middle;

import java.util.ArrayList;
import java.util.List;

public class MapToInt {
    public static void main(String[] args) {
        List<String> list =new ArrayList<>();

        list.add("100");list.add("235");list.add("2452");list.add("84");
//
//        list.stream().map(Integer::parseInt).forEach(System.out::println);
//
        int sum = list.stream().mapToInt(Integer::parseInt).sum();
        System.out.println(sum);
    }

}
