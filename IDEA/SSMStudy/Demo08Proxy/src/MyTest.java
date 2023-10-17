import service.UserService;
import service.UserServiceImpl;
import service.UserServiceProxy;

public class MyTest {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        UserServiceProxy proxy = new UserServiceProxy();
        proxy.setUserService(userService);

        proxy.add();

    }
}
