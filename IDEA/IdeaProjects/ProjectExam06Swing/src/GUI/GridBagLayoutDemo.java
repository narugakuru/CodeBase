package GUI;

import javax.swing.*;
import java.awt.*;

public class GridBagLayoutDemo extends JFrame {

    static JFrame f=new JFrame("窗口");


    public GridBagLayoutDemo() {
        JFrame frame=new JFrame("主窗口");
        Container c=getContentPane();
        frame.setSize(480,320);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(GridBagLayoutDemo.EXIT_ON_CLOSE);
    }
    public static void Init(){
        f.setSize(500,350);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton b=new JButton("顶哦");
        f.add(b);
    }

    public static void main(String[] args) {
        Init();
        getFrames();
        GridLayoutDemo g=new GridLayoutDemo();
        g.getFrames();
        new GridBagLayoutDemo();
    }
}
