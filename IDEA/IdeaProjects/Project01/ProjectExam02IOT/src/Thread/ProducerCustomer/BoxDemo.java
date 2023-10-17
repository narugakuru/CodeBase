package Thread.ProducerCustomer;

public class BoxDemo {

    public static void main(String[] args) {
        Box box=new Box();
        Producer pd=new Producer(box);
        Customer ct=new Customer(box);

        Thread t1=new Thread(pd);
        Thread t2=new Thread(ct);

        t1.start();
        t2.start();

    }


}
