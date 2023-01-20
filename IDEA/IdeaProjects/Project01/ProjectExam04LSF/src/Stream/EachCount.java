package Stream;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Collection;

public class EachCount {
    public static void main(String[] args) {
//        Collection<String> coll =new
        ArrayList<String> list = new ArrayList<String>();
        list.add("add");
        list.add("start");
        list.add("abstract");
        list.add("android");

//函数式编程风格        生成流，过滤“a”，过滤length>3，逐一打印
        list.stream().filter(s -> s.startsWith("a")).filter(s -> s.length() > 3).forEach(s -> System.out.println(s));
//        list.stream().filter(s -> s.startsWith("a")).filter(s -> s.length() > 3).forEach(System.out::println);
//count
        long count = list.stream().filter(s -> s.length() > 3).count();
        System.out.println(count);
        /*被Stream替代的过程
        ArrayList<String> alist = new ArrayList<String>();
        for (String s : list) {
            if (s.startsWith("a"))
                alist.add(s);
        }
        ArrayList<String> atlist = new ArrayList<>();
        for (String s : alist) {
            if (s.length() > 3)
                atlist.add(s);
        }
        for (String s : atlist) {
            System.out.println(s);
        }
*/
    }
}
