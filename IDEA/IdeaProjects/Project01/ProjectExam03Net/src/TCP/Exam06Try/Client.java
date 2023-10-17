package TCP.Exam06Try;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket =null;
        OutputStream outputStream =null;
        try {
            //server address
            InetAddress serverIp = InetAddress.getByName("192.168.31.222");
            int prot = 9090;
            //connect
             socket = new Socket(serverIp,prot);
            //IO
             outputStream = socket.getOutputStream();
            outputStream.write("hello fuck you".getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            outputStream.close();
            socket.close();
            //直接方法抛出异常，和在此处抛出异常，有何区别？

        }


    }

}
