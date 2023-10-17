package FunctionInterface.Exam05Predicate;

import java.util.function.Predicate;

public class PredicateOrAnd {
    public static void main(String[] args) {
        boolean hello_world = checkString("hello world", s -> s.length() < 10, s -> s.length() > 15);
        System.out.println(hello_world);
        boolean hello = checkString("hello", s -> s.length() > 10);
        System.out.println(hello);
    }

    private static boolean checkString(String s, Predicate<String> pre1, Predicate<String> pre2) {
//        return pre1.test(s)&&pre2.test(s);
//        return pre1.and(pre2).test(s);
        return pre1.or(pre2).test(s);
    }

    private static boolean checkString(String s, Predicate<String> pre) {
        return pre.test(s);
    }

}
