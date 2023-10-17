package Lambda.Exam02;

///////////////////////????????????????????????????
public class FlyableDemo {
    private static void useFlyable(Flyable f) {
        f.fly(" is fly");//赋予实现类参数
//        new FlyableImp().fly("dio");
    }

    public static void main(String[] args) {
        //重写的方法是FlyableImp!!!!
        //String ss是重命名原本FlyableImp中的s
        useFlyable((String ss) -> {
            System.out.print("monkey");
            System.out.println(ss);
        });
//
//        useFlyable(new FlyableImp());
//
/*        useFlyable(new Flyable() {
            @Override
            public void fly(String s) {
                System.out.print("pig");
                System.out.print(s);
            }
        });*/


    }

}
