package TCP.Exam05Text;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(8080);

        while(true) {
            Socket s = ss.accept();
            new Thread (new ServerThread(s)).start();
        }

    }

}
