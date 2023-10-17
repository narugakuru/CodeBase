package TCP.Exam04Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss =new ServerSocket(10086);

        Socket s = ss.accept();

        BufferedReader br =new BufferedReader(new InputStreamReader(s.getInputStream()));

        String line;
        while ((line=br.readLine())!=null){
            System.out.println(line);
        }
        br.close();
        ss.close();

    }


}
