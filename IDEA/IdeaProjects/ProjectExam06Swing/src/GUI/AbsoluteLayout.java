package GUI;

import javax.swing.*;
import java.awt.*;

public class AbsoluteLayout extends JFrame {

    public AbsoluteLayout() {
        setBounds(500, 300, 720, 480);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container con = getContentPane();

        con.setLayout(null);//AbsoluteLayout
        JButton b1 = new JButton("按钮one"), b2 = new JButton("按钮two");
        b1.setBounds(200,200,100,100);//相对于小窗口的绝对位置
        b2.setBounds(50,50,150,150);
        con.add(b1);
        con.add(b2);

    }

    public static void main(String[] args) {
        new AbsoluteLayout();
    }
}
