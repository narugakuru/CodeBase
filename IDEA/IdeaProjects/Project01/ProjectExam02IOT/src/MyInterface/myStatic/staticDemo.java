package MyInterface.myStatic;

public class staticDemo {
    public static void main(String[] args) {
        mystaticI m = new mystaticI() {
            @Override
            public void s1() {
                System.out.println("abstract");
            }
        };

        m.s1();
        m.s2();
        mystaticI.s3();

    }

}
