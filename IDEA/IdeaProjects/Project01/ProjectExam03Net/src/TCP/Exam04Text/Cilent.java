package TCP.Exam04Text;

import java.io.*;
import java.net.Socket;
import java.util.stream.Stream;

//10.100.8.159
public class Cilent {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("192.168.31.222",10086);
//
        BufferedReader br =new BufferedReader(new FileReader("Project01/ProjectExam03Net/src/TCP/InetAddress.text"));
//
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

        String line;
        while((line=br.readLine())!=null){
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        br.close();
        s.close();
    }
}
