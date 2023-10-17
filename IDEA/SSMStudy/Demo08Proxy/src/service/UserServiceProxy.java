package service;

public class UserServiceProxy implements UserService {
    private UserServiceImpl userService;

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public void add() {
        log("add");
        userService.add();
    }

    @Override
    public void rent() {

    }

    public void log(String message) {
        System.out.println("[DeBug]使用了" + message + "方法");
    }
}
