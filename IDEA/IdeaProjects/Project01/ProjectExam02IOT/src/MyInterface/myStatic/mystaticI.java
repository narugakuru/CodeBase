package MyInterface.myStatic;

public interface mystaticI {
    void s1();
    default void s2(){
        System.out.println("default");
    }
    static void s3(){
        System.out.println("static");
    }

}
