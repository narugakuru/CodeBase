package Class;

public class StringBuilderDemo {


    public static void main(String[] args) {
        StringBuilder sb1 =new StringBuilder();
        StringBuilder sb2=new StringBuilder("hello");
        StringBuilder sb3=new StringBuilder("wrold");
        StringBuilder sb4=new StringBuilder("?");

        System.out.println("sb2；"+sb2);
        sb2.append(sb3).append(sb4);
        System.out.println("sb2:"+sb2);
    }
}
