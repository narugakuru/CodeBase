package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JDialogDemo extends JDialog {

    public JDialogDemo(JFrame frame){
        super(frame,"子对话框",true);//阻塞父窗口
        Container c=getContentPane();
        Label l=new Label("这是一个子对话框");
        l.setFont(new Font("微软雅黑",Font.BOLD,15));
        c.add(l);

        setBounds(500,500,300,200);
    }

    public static void main(String[] args) {
        JFrame f=new JFrame("父窗口");
        f.setVisible(true);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setBounds(700,400,500,300);
        Container c=f.getContentPane();
        c.setLayout(new FlowLayout());

        JButton btn=new JButton("新的对话框");//组件名


        c.add(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialogDemo d= new JDialogDemo(f);
                d.setVisible(true);
            }
        });


    }

}
