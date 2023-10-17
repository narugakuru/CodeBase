package main.java.pojo;

public class User {
    private String UserName;

    public User() {
        System.out.println("cnmd");
    }

    public User(String userName) {
        this.UserName = userName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void show() {
        System.out.println("UserName=" + UserName);
    }
}
