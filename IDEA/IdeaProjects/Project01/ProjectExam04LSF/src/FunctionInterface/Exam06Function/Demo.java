package FunctionInterface.Exam06Function;

import java.util.function.Function;

public class Demo {
    public static void main(String[] args) {

        convert(100, i -> String.valueOf(i + 566));
        convert("5", Integer::parseInt);
        convert("100", s -> Integer.parseInt(s), i -> String.valueOf(i + 566));
    }

    //    String->int
    private static void convert(String s, Function<String, Integer> fun) {
        System.out.println("String->int " + fun.apply(s));
    }

    //    int->String
    private static void convert(int i, Function<Integer, String> fun) {
        System.out.println("int->String " + fun.apply(i));
    }

    //String->int->String
    private static void convert(String s, Function<String, Integer> fun1, Function<Integer, String> fun2) {
//        Integer i = fun1.apply(s);
//        String ss =fun2.apply(i);
//        System.out.println("result:"+ss);
        System.out.println(fun1.andThen(fun2).apply(s));
    }

}
