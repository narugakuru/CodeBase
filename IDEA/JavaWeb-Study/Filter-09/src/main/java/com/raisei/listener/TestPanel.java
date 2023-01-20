package com.raisei.listener;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestPanel extends Frame {
    public static void main(String[] args) {
        Frame frame = new Frame("hello");
        Panel panel = new Panel(null);//面板
        frame.setLayout(null);//布局

        frame.setBounds(0,0,500,500);

        panel.setBounds(0,0,200,200);
        panel.setBackground(new Color(0,255,0));

        frame.add(panel);
        frame.setVisible(true);//可见

//        监听事件关闭事件
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                super.windowClosing(e);
                System.exit(0);
            }
        });

    }

}
