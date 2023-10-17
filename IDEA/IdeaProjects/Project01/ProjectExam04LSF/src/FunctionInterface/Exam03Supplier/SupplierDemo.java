package FunctionInterface.Exam03Supplier;

import java.util.function.Supplier;
/*返回泛型的接口,也可指定返回值类型
表示结果的供应商。
没有要求一个新的或不同的结果被返回时，每个供应商被调用。
这是一个functional interface其功能的方法是get()。*/

public class SupplierDemo {//Supplier供应商
    public static void main(String[] args) {

        String s= getString(new Supplier<String>() {
            @Override
            public String get() {
                return "mhw";
            }
        });
        System.out.println(s);

        System.out.println(getString(()-> "monster"));

        System.out.println(getInt(()->23333));

    }

    private static Integer getInt(Supplier<Integer> sup){
        return sup.get();
    }

    private static String getString(Supplier<String> sup){

        return sup.get();
    }

}
