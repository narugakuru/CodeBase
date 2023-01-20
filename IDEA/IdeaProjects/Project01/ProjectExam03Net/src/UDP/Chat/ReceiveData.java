package UDP.Chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ReceiveData implements Runnable {
    private int port;
    DatagramSocket datagramSocket = null;

    public ReceiveData(int port) throws SocketException {
        this.port = port;
        datagramSocket = new DatagramSocket(port);
    }

    @Override
    public void run() {

        while (true) {
            try {
                byte[] bys = new byte[1024];
                DatagramPacket datagramPacket = new DatagramPacket(bys, 0, bys.length);
                //接收数据
                System.out.println("初始化完成");
                datagramSocket.receive(datagramPacket);
                System.out.println("已接收数据");
                String data = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

                System.out.println(":" + data);

                if (data.equals("bye")){
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) throws SocketException {
        new ReceiveData(10086).run();
    }


}
