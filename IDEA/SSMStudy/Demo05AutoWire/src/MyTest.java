import dao.Cat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.People;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        People people = context.getBean("people",People.class);
        System.out.println(people.toString());

//        Cat cat = context.getBean("cat",Cat.class);
//        System.out.println(cat.getName());

    }
}
