import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("resources/Beans.xml");
        UserService userService = applicationContext.getBean("useService",UserService.class);
        userService.add();

    }
}
