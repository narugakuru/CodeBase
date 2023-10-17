package UDP.Chat;

import java.net.SocketException;

public class two {
    public static void main(String[] args) throws SocketException {

        new Thread(new SendData(10011,"localhost")).start();
        new Thread(new ReceiveData(10086)).start();


    }


}
