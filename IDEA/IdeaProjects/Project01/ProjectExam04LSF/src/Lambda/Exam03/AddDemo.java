package Lambda.Exam03;/*
你可以在函数式接口上使用Lambda表达式
函数式接口就是只定义一个抽象方法的接口*/
public class AddDemo {

    private static void useAddable(Addable a){
        System.out.println(a.add(114,514));
    }

    public static void main(String[] args) {
        useAddable(new AddableImp());
//lambda自动匹配方法？？

        useAddable((int x,int y)->{
            return  x+y;
        });
//
        useAddable(new Addable() {
            @Override
            public int add(int x, int y) {
                return x*y;
            }
        });

    }


}
