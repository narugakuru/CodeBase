package GUI;

import javax.swing.*;
import java.awt.*;

public class FrameDemo extends JFrame {
    public FrameDemo() {
//        JFrame f = new JFrame("yaleyale");//新建窗体
        setTitle("顶哦");
        setVisible(true);//可见
        //setSize(500,500);
        //setLocation(400,400);
        setBounds(400, 400, 300, 300);//大小坐标
        setLayout(new FlowLayout());
        setResizable(false);//不可改变大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//隐藏并关闭程序

        Container c = getContentPane();
        c.setBackground(Color.white);

        JLabel l = new JLabel("ofq");
        c.add(l);
    }

    public static void main(String[] args) {
        new FrameDemo();
    }
}
