package UDP.Chat;

import java.net.SocketException;

public class one {
    public static void main(String[] args) throws SocketException {

        new Thread(new SendData(10086,"localhost")).start();
        new Thread(new ReceiveData(10011)).start();

    }

}
