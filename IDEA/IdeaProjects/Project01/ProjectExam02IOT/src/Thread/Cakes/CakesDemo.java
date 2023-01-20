package Thread.Cakes;

import javax.swing.*;
import java.awt.*;

public class CakesDemo extends Frame {

    public CakesDemo() {
        setSize(500, 500);
        setVisible(true);
        setTitle("konodiodaze");
        setBackground(Color.cyan);
        Button b = new Button("开始运行");
        setLayout(new FlowLayout());
//        b.addActionListener();
        add(b);
    }

    public static void main(String[] args) {

    }

}
