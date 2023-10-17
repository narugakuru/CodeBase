package TCP.Exam04Text;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//        1.创建ServerSocket
//        2.监听客户端连接
//        3.获取输入流，读取数据，并把数据打印在控制台
//        4.释放资源
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10086);

        Socket s = ss.accept();
//BufferedReader从一个字符输入流中读取
//InputStreamReader桥接字节流字符流：将字节数组解码成文字使用指定的 charset;
//        获取输入流-》读取输入流-》转换成字符流
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
//把数据写入文本
        BufferedWriter bw = new BufferedWriter(new FileWriter("Project01/ProjectExam03Net/src/TCP/Exam04Text/Copy.text"));
        String line;
        while ((line = br.readLine()) != null) {
//            三者是绝对绑定，缺一不可
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
//发送反馈
        BufferedWriter bwServer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        bwServer.write("文件上传成功");
        bwServer.newLine();
        bwServer.flush();
        bw.close();
        ss.close();
    }
}
