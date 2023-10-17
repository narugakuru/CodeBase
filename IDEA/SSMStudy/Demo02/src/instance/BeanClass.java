package instance;

public class BeanClass {
    public String message;
    public BeanClass(){
        message = "构造方法实例化";
    }
    public BeanClass(String s){
        message = s;
    }
}
