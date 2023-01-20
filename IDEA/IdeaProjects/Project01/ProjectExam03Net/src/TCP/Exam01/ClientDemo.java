package TCP.Exam01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
//
        Socket s = new Socket("10.100.8.159", 10086);
//获取输出流，写入数据
        OutputStream os = s.getOutputStream();
        os.write("konodiodaze".getBytes());
//接收服务器反馈
        InputStream is = s.getInputStream();
        byte[] bys = new byte[1024];
//        int len =is.read(bys);
        String data = new String(bys, 0, is.read(bys) );
        System.out.println("客户端收到的反馈" + data);

//释放资源

        s.close();
    }

}
