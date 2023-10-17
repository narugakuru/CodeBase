package MyInterface.myDefault;

public class defaultDemo {
    public static void main(String[] args) {
        DefaultInter di=new DefaultInter() {
            @Override
            public void show() {
                System.out.println("dio");
            }
        };
        di.out();
        di.show();
        System.out.println("------------------");
        DefaultInter dd=new defaultImp();
        dd.out();
        dd.show();
    }

}
