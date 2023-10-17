package Thread;

public class SellTicketDemo {

    public static void main(String[] args) {

        SellTicket s=new SellTicket();
        Thread t1=new Thread(s,"窗口1");
        Thread t2=new Thread(s,"窗口2");
        Thread t3=new Thread(s,"窗口3");

//        t1.setPriority(5);
//        t2.setPriority(5);
//        t3.setPriority(5);

        t1.start();
        t2.start();
        t3.start();
    }

}
