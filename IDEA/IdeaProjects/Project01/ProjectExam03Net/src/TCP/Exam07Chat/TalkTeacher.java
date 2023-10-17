package TCP.Exam07Chat;

public class TalkTeacher {
    public static void main(String[] args) {

        new Thread(new TalkSend(2222,"192.168.31.222",8888)).start();
        new Thread(new TalkReceive(9999,"学生")).start();

    }

}
