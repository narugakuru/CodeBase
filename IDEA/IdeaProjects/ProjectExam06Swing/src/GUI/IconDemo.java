package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class IconDemo extends JFrame {
    public IconDemo(){
        setTitle("fall girl");
        setBounds(500,300,500,350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Container con=getContentPane();
        JLabel l1=new JLabel("");
//        l1.setFont(new Font("微软黑体",Font.BOLD,25));

        URL url1=IconDemo.class.getResource("75.png");//获取文件路径
        Icon icon1=new ImageIcon(url1);//获取路径下的图片
        l1.setIcon(icon1);//添加图片
        l1.setSize(720,480);//即使设置标签大小也改变不了图片大小
        con.add(l1);
    }

    public static void main(String[] args) {
        new IconDemo();
    }


}
