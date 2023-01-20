package MyInterface.myDefault;

public interface DefaultInter {
    void show();

    default void out() {
        System.out.println("out");
    }
}
