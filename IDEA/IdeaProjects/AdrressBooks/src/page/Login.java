package page;

import dao.personDao;
import dao.personDaoImp;
import util.jdbc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ProjectName: class_in_java
 * @Package: com.jn
 * @ClassName: loginFrame
 * @Author: JN
 * @Description:
 * @Date: 2020/11/30 9:53
 * @Version: 1.0
 */

public class Login extends JFrame implements ActionListener {

    JPanel jPanel = new JPanel();

    JLabel jLabel1;
    JLabel jLabel2;

    JTextField jTextField1;
    JPasswordField jPasswordField;

    JButton okButton;
    JButton updButton;



    public Login()  {
        this.setBounds(200,200,400,300);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setTitle("登录窗口");
        this.setLayout(null);//关闭布局管理器
        validate();
        initCom();
    }


    public  void  initCom(){


        jPanel.setBounds(100, 50, 200, 200);
        jPanel.setLayout(new GridLayout(3, 2, 10, 20));
        this.add(jPanel);

        jLabel1 = new JLabel("用户名");
        jTextField1 = new JTextField();
        jLabel2 = new JLabel("密码");
        //jTextField2 = new JTextField();
        jPasswordField = new JPasswordField();
        okButton = new JButton("登录");
        updButton = new JButton("重置");

        okButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {

                personDao Pd = new personDaoImp();
                jdbc jb = new jdbc();
                Connection con = jb.getConnection();
                String name = jTextField1.getText();
                System.out.println(name);
                String phone = jPasswordField.getText();
                boolean flag = false;
                try {
                    flag = Pd.Login(con, name, phone);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if (jTextField1.getText().equals("")||jPasswordField.equals("")){
                    JOptionPane.showMessageDialog(null,"用户名或密码不能为空");
                }else {
                    if (flag){
                        try {
                            new AddressBookGUI();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        dispose();//本对话框关闭
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"用户名或密码不能错误");
                        jPasswordField.setText("");
                        jTextField1.setText("");
                    }
                }
            }
        });

        updButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPasswordField.setText("");
                jTextField1.setText("");
            }
        });
        jPanel.add(jLabel1);
        jPanel.add(jTextField1);
        jPanel.add(jLabel2);

        jPanel.add(jPasswordField);
        jPanel.add(okButton);
        jPanel.add(updButton);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new Login();
    }
}
