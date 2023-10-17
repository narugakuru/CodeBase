package Stream.Exam03;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Demo1 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("add");
        list.add("start");
        list.add("abstract");
        list.add("android");
//        取3，取a去1，合并，作为构造方法的参数创建对象，遍历数据
/*        Stream<String> l = list.stream().limit(2);
        Stream<String> s = list.stream().filter(ss -> ss.startsWith("a")).skip(1);

        Stream.concat(l, s).map(Actor::new).forEach(ss -> System.out.println(ss.getName()));*/

        Stream.concat(list.stream().limit(2),list.stream().filter(ss->ss.startsWith("a")).skip(1)).
                map(Actor::new).forEach(ss-> System.out.println(ss.getName()));
    }

}
