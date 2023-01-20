package FunctionInterface.Exam02;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ComparatorDemo {
    public static void main(String[] args) {
//        定义集合
        ArrayList<String> array =new ArrayList<>();
        array.add("cc");
        array.add("a");
        array.add("dd");
        array.add("bbbb");
        System.out.println("排序前："+ array);

//        Collections.sort(array);//按a~z排序
        Collections.sort(array,getComparator());//按长度排序
        System.out.println("排序后："+ array);


    }
//方法返回值是函数式接口，可return返回Lambda表达式
    private static Comparator<String> getComparator(){
        Comparator<String> comp =new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        };
//        return comp;

/*      return new Comparator<String>() {
          @Override
          public int compare(String o1, String o2) {
              return o1.length()-o2.length();
          }
      };*/

    return (s1,s2)-> s1.length()-s2.length();

    }
}
