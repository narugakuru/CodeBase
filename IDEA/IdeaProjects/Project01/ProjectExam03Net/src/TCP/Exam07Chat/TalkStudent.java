package TCP.Exam07Chat;

public class TalkStudent {
    public static void main(String[] args) {

        new Thread(new TalkSend(3333,"192.168.31.222",9999)).start();
        new Thread(new TalkReceive(8888,"老师")).start();


    }


}
