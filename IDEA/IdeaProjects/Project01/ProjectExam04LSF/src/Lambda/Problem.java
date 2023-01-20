package Lambda;
//Lambda适用范围：函数式接口
//匿名类适用范围：任意接口，抽象类，具体类
//区别:Lambda是动态编译不会生成class，匿名类会单独生成class
public class Problem {
    public static void main(String[] args) {
        useShow(()-> System.out.println("lambda s"));

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable");
            }
        }).start();
        //简化代码
        new Thread(()-> System.out.println("lambda t")).start();*/

        new Thread(()-> System.out.println("dio") ).start();
    }

    private static void useShow(Inter i){
        i.show();
    }

}
