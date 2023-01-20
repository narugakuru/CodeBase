package TCP.Exam04Text;
//把文件上传至服务器生成文件，服务器给客户端发送反馈
import java.io.*;
import java.net.Socket;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
//创建客户端对象
        Socket s = new Socket("10.100.8.159", 10086);
//读取数据
        //把文件的数据读取到内存里，
        BufferedReader br = new BufferedReader(new FileReader("Project01/ProjectExam03Net/src/TCP/InetAddress.text"));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();//把内存中的数据-->读取到文件中
        }
//        发送结束标记
        s.shutdownOutput();
//        接收反馈
        BufferedReader brClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String data =brClient.readLine();
        System.out.println("服务器反馈：" + data);

        br.close();
        s.close();

    }

}
