import javafx.application.Application;
import main.java.Student;
import main.java.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MyTest {
    public static void main(String[] args) {
        Test2();
    }

    public static void Test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("resources\\UserBeans.xml");
        User u = context.getBean("user",User.class);
        System.out.println(u.toString());
    }

    public static void Test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("resources\\Beans.xml");

        Student student = (Student) context.getBean("student");
//        System.out.println(student.getName());
//        System.out.println(student.getBooks());
        System.out.println(student.toString());
        System.out.println(Arrays.toString(student.getBooks()));
    }

}
