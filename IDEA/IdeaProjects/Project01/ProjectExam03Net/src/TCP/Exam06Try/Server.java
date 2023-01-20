package TCP.Exam06Try;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        Socket socket =null;
        InputStream inputStream =null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        try {
            //address
            serverSocket = new ServerSocket(9090);
            //connect
            socket = serverSocket.accept();
            //InputStream
            inputStream = socket.getInputStream();
            //pipeline
            byteArrayOutputStream =new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len=inputStream.read(buffer))!=-1){
                byteArrayOutputStream.write(buffer,0,len);
            }
            System.out.println(byteArrayOutputStream.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            byteArrayOutputStream.close();
            socket.close();
            serverSocket.close();
        }

    }



}
