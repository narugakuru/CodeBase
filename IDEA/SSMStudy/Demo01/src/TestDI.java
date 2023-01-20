
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.*;
import dao.*;

public class TestDI {

    public static  void main(String[] args) {
        ApplicationContext appcon = new ClassPathXmlApplicationContext("Beans.xml");

        TestDIService ts = (TestDIService)appcon.getBean("testDIService");
        ts.sayHello();

        TestDIService ts1 = (TestDIService)appcon.getBean("testDIService1");
        ts1.sayHello();

    }
}
