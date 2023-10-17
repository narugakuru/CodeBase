package UDP.NetworkBase;

import java.io.IOException;
import java.net.*;

public class SendData {

    public static void main(String[] args) throws IOException {
//        创建发送端socket对象
        DatagramSocket ds=new DatagramSocket();
//        创建数据
        byte[] bys="Hello Receiver".getBytes();
/*        int length=bys.length;
        InetAddress address =InetAddress.getByName("192.168.148.81");
        int port=10086;
        DatagramPacket dp=new DatagramPacket(bys,length,address,port);*/
//        数据打包
        DatagramPacket dp=new DatagramPacket(bys,bys.length,InetAddress.getByName("192.168.31.222"),10086);
//        发送数据
        ds.send(dp);
//        关闭数据发送
        ds.close();

    }

}
