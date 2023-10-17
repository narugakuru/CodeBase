package Thread;
/*
Runnable可用多线程处理同一资源，把线程，代码，数据有效分离
避免了java单继承的限制
*/
public class MyRunnableDemo {

    public static void main(String[] args) {
        MyRunnable mr = new MyRunnable();
        Thread t1 = new Thread(mr,"迪奥");
        Thread t2=new Thread(mr,"乔斯达");

        t1.start();
        t2.start();

    }

}
