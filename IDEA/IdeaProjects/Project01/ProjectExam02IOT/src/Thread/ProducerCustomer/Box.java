package Thread.ProducerCustomer;

public class Box {
    private int milk;
    private boolean state = false;

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public synchronized void get() {
        if(!state){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("消费者拿到第" + this.milk + "瓶牛奶");
        state=false;

        notifyAll();
    }

    public synchronized void put(int milk) {
        if (state) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.milk = milk;
        System.out.println("送奶工放入第" + this.milk + "瓶牛奶");

        state=true;

        notifyAll();
    }
}
