package Thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public  class Thread01 extends Thread{
    private Lock lock=new ReentrantLock();

    public  Thread01(){

    }
    public  Thread01(String name){
        super(name);
    }
    @Override
    public void run() {

            lock.lock();
            for (int i = 0; i < 100; i++) {
                System.out.println(getName() + ":" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }
}
