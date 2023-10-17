package Class;

import java.awt.*;
import java.util.*;
import javax.swing.*;
public class HyClock extends JFrame implements Runnable {
    public static JLabel tm;
    public void run() {
        while (true) {
            Calendar c = Calendar.getInstance();
            String nowtime = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
            tm.setText(nowtime);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }

    public static void main(String[] agrs) {
        HyClock  hytime = new HyClock();
        hytime.setLayout(new FlowLayout());
        tm = new JLabel();
        tm.setFont(new Font("宋体", 1, 40));
        tm.setForeground(Color.RED);
        hytime.add(tm);
        hytime.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        hytime.setSize(300, 100);
        hytime.setVisible(true);
        hytime.setLocation(400, 400);
        Thread t = new Thread(hytime);
        t.start();
    }
}