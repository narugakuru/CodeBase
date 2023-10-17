package Thread;

public class SellDemo2 {

    public static void main(String[] args) {
    		Object obj = new Object();
    		WindowThread thread01 = new WindowThread("窗口一", obj);
    		WindowThread thread02 = new WindowThread("窗口二", obj);
    		WindowThread thread03 = new WindowThread("窗口三", obj);
    		//开启线程
    		thread01.start();
    		thread02.start();
    		thread03.start();
    	}
    }
class WindowThread extends Thread {
        public static int ticket = 500;
        //设置线程共有对象，方便加锁
        Object obj;

        public WindowThread(String name, Object obj) {
            super(name);
            this.obj = obj;
        }

        @Override
        public void run() {
            while (true) {
                //加锁。确保线程安全，不发生一个资源多次被使用
                synchronized (obj) {
                    if (ticket > 0) {
                        String message = Thread.currentThread().getName() + "卖了第" + ticket + "票";
                        System.out.println(message);
                        ticket--;
                    } else {
                        break;
                    }

                }
            }

        }


}
