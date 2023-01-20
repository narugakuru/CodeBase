package Class;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.io.IOException;

public class graph {

    public static void main(String[] args) throws IOException{

        JTextArea outputArea = new JTextArea();
        outputArea.setText("这就是要输出显示的字符串内容!");
        JOptionPane.showMessageDialog(null,outputArea,"标题栏信息显示在此",JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);

    }

}
