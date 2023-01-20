package Thread;

public class ThreadDemo01 {
    public static void main(String[] args) {
        Thread01 t1=new Thread01("售票窗口1");
        Thread01 t2=new Thread01("售票窗口2");

//        t1.setPriority(1);
//        t2.setPriority(10);
        t2.setDaemon(true);

        t1.start();
/*        try {
            t1.join();//等待t1执行完毕
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        t2.start();
//        System.out.println(Thread.currentThread().getName());
//        System.out.println(t1.getPriority());
//        System.out.println(t2.getPriority());
    }
}
