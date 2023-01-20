package TCP;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
//        1.创建ServerSocket
//        2.监听客户端连接
//        3.获取输入流，读取数据，并把数据打印在控制台
//        4.释放资源
public class ReceiveBase {
    public static void main(String[] args) throws IOException {
//        1.创建ServerSocket
        ServerSocket ss = new ServerSocket(10086);
//        2.监听客户端连接
        Socket s = ss.accept();
//        3.获取输入流，读取数据，并把数据打印在控制台
        InputStream is = s.getInputStream();
        byte[] bys = new byte[1024];
//        int len=is.read(bys);
//        System.out.println("len is：" + len);
//        System.out.println(is.read(bys));//输出结果为8
//        read(bys)从输入流读入字节数组数据，实际读取的字节数作为一个整数返回。
        String data = new String(bys,0,is.read(bys));
        System.out.println("data is：" + data);
//        4.释放资源
//        s.close();
        ss.close();
    }

}
