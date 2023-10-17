package FunctionInterface.Exam04Consumer;

/*public interface Consumer<T>
表示接受一个输入参数，并返回没有结果的操作。
        这是一个functional interface其功能的方法是accept(Object)。
void accept(T t)
        在给定的参数上执行此操作。
default Consumer<T> andThen(Consumer<? super T> after)
        返回一个由 Consumer执行此操作，在序列，其次是 after操作。
*/

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;

import java.util.function.Consumer;

public class ConsumerDemo {
    public static void main(String[] args) {
        operatorString("dio", s -> System.out.println(s + " shi nn de i ta"));
//        operatorString("dio",System.out::println);
        operatorString("dio", s -> System.out.println(new StringBuffer(s).reverse().toString()));
//        operatorString("dio",System.out::println(new StringBuffer().reverse().toString()));
//两个Consumer参数
//        operatorString("jo su da", s -> System.out.println(s),
        operatorString("jo su da", System.out::println,
                s -> System.out.println(new StringBuffer(s).reverse().toString()));

    }

    //以不同方法消费同一个字符串两次
    private static void operatorString(String name, Consumer<String> con1, Consumer<String> con2) {
//        con1.accept(name);
//        con2.accept(name);
        con1.andThen(con2).accept(name);
    }

    //    定义方法，消费字符串数据
    private static void operatorString(String name, Consumer<String> con) {
        con.accept(name);
    }

}
