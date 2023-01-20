package Thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SellTicket implements Runnable {
    private int ticket = 1;
    private Object obj = new Object();
    private int x = 0;
    private final int t = 300;
    private Lock lock = new ReentrantLock();


    @Override
    public void run() {
        while (true) {
            synchronized (obj) {
                if (ticket < t) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "已出售第" + ticket + "张票");
                    ticket++;
                }
            }
        }
    }

    private /*synchronized*/ void Sell() {
        try {
            lock.lock();
            if (ticket < t) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "已出售第" + ticket + "张票");
                ticket++;
            }
        } finally {
            lock.unlock();
        }
    }


}
    /*  public void run() {
        while (true) {
           //lock
            if (x % 2 == 0) {
                synchronized (this) {
                    if (ticket < t) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "已出售第" + ticket + "张票");
                        ticket++;
                    }
                }
            } else {
                Sell();
            }
            x++;
        }
    }*/

