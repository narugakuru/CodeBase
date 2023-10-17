package FunctionInterface.Exam05Predicate;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

import java.util.function.Predicate;

public class PredicateTest {
    public static void main(String[] args) {
        System.out.println(checkString("hello", s -> s.length() > 8));
        System.out.println(checkString("hello world", s -> s.length() > 8));

    }

    private static boolean checkString(String s, Predicate<String> pre) {
//        return !pre.test(s);
        return pre.negate().test(s);
    }

}
