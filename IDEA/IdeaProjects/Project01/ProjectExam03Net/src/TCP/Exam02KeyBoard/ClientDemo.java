package TCP.Exam02KeyBoard;

import java.io.*;
import java.net.Socket;
//        1.创建客户端Socket对象
//        2.获取输出流，写入数据
//        3.释放资源
public class ClientDemo {
    public static void main(String[] args) throws IOException {
//        Socket s=new Socket("fe80::81e3:4c:b031:e924%5",1978);//IPv6
        Socket s=new Socket("192.168.31.222",1978);//IPv4
//由输入流获取键盘输入，并封装成BufferedReader
//BufferedReader从一个字符输入流中读取文本，缓冲字符，以便提供字符、数组和行的有效读取。
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//将文本写入到字符输出流中，缓冲字符，以便提供对单个字符、数组和字符串的有效写入。
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        String line;
        while ((line=br.readLine())!=null){
            if (line.equals("886")){

                break;
            }
//            OutputStream os=s.getOutputStream();//获取输入流
//            os.write(line.getBytes());//把数据由输入流写入缓存区
            bw.write(line);
            bw.newLine();//换行
            bw.flush();//刷新
        }

        s.close();
    }

}
