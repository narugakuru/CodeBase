package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FlowLayoutDemo extends JFrame {
    public FlowLayoutDemo(){
        setBounds(500,350,480,320);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Container c=getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.LEFT,30,30));

        JLabel l1=new JLabel("斯巴拉西");
        l1.setFont(new Font("宋体",Font.CENTER_BASELINE,30));
        c.add(l1);

        for(int i=1;i<10;i++){
            JButton b=new JButton("按钮"+i);
            b.setSize(100,100);
            c.add(b);
        }

/*        JLabel l2=new JLabel();
        URL url1=FlowLayoutDemo.class.getResource("75.png");
        Icon icon1=new ImageIcon(url1);
        l2.setIcon(icon1);
        c.add(l2);*/
    }

    public static void main(String[] args) {
        new FlowLayoutDemo();
    }
}
