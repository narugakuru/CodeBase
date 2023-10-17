package main.java.pojo;

public class UserT {
    private String UserTName;

    public UserT() {

    }

    public UserT(String userTName) {
        UserTName = userTName;
    }

    public String getUserTName() {
        return UserTName;
    }

    public void setUserTName(String userTName) {
        UserTName = userTName;
    }
    public void show(){
        System.out.println("T="+this.UserTName);
    }

}
