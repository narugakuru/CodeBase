package Demo02;

import Demo02.service.TestDIService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;

public class TestDI {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Beans.xml");

        TestDIService ts = (TestDIService) applicationContext.getBean("testDIService");
        ts.sayHello();

        TestDIService ts1 = (TestDIService)applicationContext.getBean("testDIService1");
        ts1.sayHello();


    }
}
