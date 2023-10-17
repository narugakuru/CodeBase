package FunctionInterface.Exam01;

public class RunnableDemo {
    public static void main(String[] args) {
//
        startThread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread()
                        .getName()+" Thread is started");
            }
        });
//
        startThread(()-> System.out.println(Thread.currentThread()
                .getName()+" sai ko da ze"));

    }


    private static void startThread(Runnable r){

        new Thread(r).start();

    }

}
