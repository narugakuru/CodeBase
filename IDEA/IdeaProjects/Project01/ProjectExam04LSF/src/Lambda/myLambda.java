package Lambda;

public class myLambda {
    public static void main(String[] args) {
//        内部匿名类
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread is started");
            }
        }).start();*/
//Lambda表达式进一步简化
        new Thread(()->{
            System.out.println("Thread is started");
        }).start();

    }

}
