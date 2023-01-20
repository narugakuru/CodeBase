package UDP.NetworkBase;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveData {

    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(10086);


        byte[] bys = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bys, bys.length);

        ds.receive(dp);
//        byte[] datas = dp.getData();
        String datastring = new String(dp.getData(), 0, dp.getLength());
//        System.out.println("数据："+new String(dp.getData(),0, dp.getLength()));
        System.out.println("" + datastring);
        ds.close();//无用
    }
}
