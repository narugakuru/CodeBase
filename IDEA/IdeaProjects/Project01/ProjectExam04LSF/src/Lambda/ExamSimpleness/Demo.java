package Lambda.ExamSimpleness;
//lambda仅用于函数式接口，即单方法接口
public class Demo {

    public Demo(int a) {
        this.a = a;
    }

    private int a;

    private static void useAdd(Addable a){
        System.out.println(a.add(114,514));
    }

    private static void useFly(Flyable f){
        f.fly("dio");
    }

    public static void main(String[] args) {
//        Demo((int x) -> x);

        useAdd((int x,int y)->{
            return x-y;
        });
//
        useAdd((x,y) -> x*y);

        useFly((String s) -> {System.out.println(s);} );
//
        useFly(s-> System.out.println(s+" is dead"));


    }
}
