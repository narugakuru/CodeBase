package GUI;

import javax.swing.*;
import java.awt.*;

public class GridLayoutDemo extends JFrame {
    public GridLayoutDemo(){
        setSize(480,320);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container c=getContentPane();
        c.setLayout(new GridLayout(5,5,5,5));//网格布局

        for(int i=1;i<=25;i++){
            c.add(new JButton("按钮"+i));
        }
    }

    public static void main(String[] args) {
        new GridLayoutDemo();
    }

}
