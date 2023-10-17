package TCP.Exam01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10086);
//监听monitor
        Socket s = ss.accept();
//获取输入流
        InputStream is = s.getInputStream();
        byte[] bys = new byte[1024];
        String data = new String(bys, 0, (is.read(bys)));
        System.out.println("data is:" + data);
//        发出反馈
        OutputStream os=s.getOutputStream();
        os.write("data is received".getBytes());

        ss.close();
    }

}
