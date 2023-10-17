package UDP.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class SendData implements Runnable{
    private int toPort;
    private String toIp;

    DatagramSocket datagramSocket = new DatagramSocket();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public SendData(int toPort, String toIp) throws SocketException {
        this.toPort = toPort;
        this.toIp = toIp;

    }

    @Override
    public void run() {
        while (true){
            try {
                System.out.print("请输入：");
                String data = bufferedReader.readLine();
                byte[] bys =data.getBytes();

                DatagramPacket datagramPacket = new DatagramPacket(bys,0,bys.length,new InetSocketAddress(this.toIp,this.toPort));

                datagramSocket.send(datagramPacket);
                System.out.println("已发送");

                if(data.equals("bye")){
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        datagramSocket.close();

    }

    public static void main(String[] args) throws SocketException {
         new SendData(10086,"192.168.31.222").run();
    }
}
