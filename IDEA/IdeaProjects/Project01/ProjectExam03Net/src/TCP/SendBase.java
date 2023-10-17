package TCP;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
//        1.创建客户端Socket对象
//        2.获取输出流，写入数据
//        3.释放资源

public class SendBase {
    public static void main(String[] args) throws IOException {
//        1.创建客户端Socket对象
//        Socket s=new Socket(InetAddress.getByName("192.168.148.81"),114514);
        Socket s = new Socket("10.100.8.159", 10086);
//        2.获取输出流，写入数据
        OutputStream os = s.getOutputStream();
        os.write("hello fq".getBytes());
//        3.释放资源
        s.close();

    }

}
