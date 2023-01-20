package TCP.Exam02KeyBoard;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
//        1.创建ServerSocket
//        2.监听客户端连接
//        3.获取输入流，读取数据，并把数据打印在控制台
//        4.释放资源
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(1978);

        Socket s=ss.accept();

//        InputStream is=s.getInputStream();
//        把输入流转换成字符并封装成BufferedReader
        BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
        String line;
        while ((line=br.readLine())!=null) {
            if (line.equals("886")) {
                break;
            }
            System.out.println("data is：" + line);
        }

        ss.close();
    }

}
