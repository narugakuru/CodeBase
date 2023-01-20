import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service10.UserService;
import diy10.*;

public class MyTest10 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans10.xml");
        UserService userService = (UserService) context.getBean("userService10");
        userService.delete();

    }
}
