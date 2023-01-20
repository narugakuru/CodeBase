package page;

import dao.groupDao;
import dao.groupDaoImp;
import dao.personDao;
import dao.personDaoImp;
import util.jdbc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.EventListener;
import java.util.List;

/**
 * @ProjectName: AdrressBooks
 * @Package: page
 * @ClassName: groupFrame
 * @Author: JN
 * @Description: 1
 * @Date: 2020/11/27 16:28
 * @Version: 1.0
 */
public class groupFrame extends JFrame {

    JLabel jLabel2 = null;
    JLabel jLabel1 = null;
    JButton EXIT = null;
    JButton Add = null;
    JButton Del= null;
    JButton Upd = null;
    JPanel jPanel = null;
    JTextField jTextField1 = null;
    JTextField jTextField2 = null;
    public groupFrame(String name){
        this.setTitle(name);
    }


    //增加组
    public void  addGroup(){
        jPanel = new JPanel();
        jPanel.setBounds(450, 250, 300, 200);
        jPanel.setLayout(null);
        this.add(jPanel);

        this.setVisible(true);
        this.setBounds(450, 250, 300, 200);

        jTextField1 = new JTextField();
        jPanel.add(jTextField1);
        jTextField1.setBounds(70,30,160,30);


        EXIT = new JButton("退出");
        EXIT.setBounds(200, 100, 60, 30);
        jPanel.add(EXIT);

        EXIT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("退出");
                dispose();//只退出当前窗口
            }
        });
        jLabel1 = new JLabel("组名");
        jLabel1.setBounds(40,30,50,30);
        Add = new JButton("增加");
        Add.setBounds(30, 100, 60, 30);
        jPanel.add(Add);
        jPanel.add(jLabel1);



        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = jTextField1.getText();
                groupDaoImp gD = new groupDaoImp();
                jdbc jb = new jdbc();
                Connection con = jb.getConnection();
                try {
                    if (!gD.exitGroup(con,text)){
                        int i = gD.addGroup(con, text);
                        if (i>0){
                            JOptionPane.showMessageDialog(jPanel, "添加成功", "success",JOptionPane.WARNING_MESSAGE);
                            jTextField1.setText("");
                            System.out.println("添加成功");
                        }else {
                            JOptionPane.showMessageDialog(jPanel, "添加失败", "fail",JOptionPane.WARNING_MESSAGE);
                            jTextField1.setText("");
                            System.out.println("添加失败");
                        }
                    }else {
                        JOptionPane.showMessageDialog(jPanel, "组名已存在", "fail",JOptionPane.WARNING_MESSAGE);
                        System.out.println("组名已存在");
                        jTextField1.setText("");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    //删组
    public void  delGroup(){
        jPanel = new JPanel();
        jPanel.setBounds(450, 250, 300, 200);
        jPanel.setLayout(null);
        this.add(jPanel);

        this.setVisible(true);
        this.setBounds(450, 250, 300, 200);

        jTextField1 = new JTextField();
        jPanel.add(jTextField1);
        jTextField1.setBounds(70,30,160,30);


        EXIT = new JButton("退出");
        EXIT.setBounds(200, 100, 60, 30);
        jPanel.add(EXIT);

        EXIT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("退出");
                dispose();//只退出当前窗口
            }
        });
        jLabel1 = new JLabel("组名");
        jLabel1.setBounds(40,30,50,30);
        Del = new JButton("删除");
        Del.setBounds(30, 100, 60, 30);
        jPanel.add(Del);
        jPanel.add(jLabel1);

        Del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String GName = jTextField1.getText();
                groupDaoImp gD = new groupDaoImp();
                jdbc jb = new jdbc();
                Connection con = jb.getConnection();
                try {

                    boolean flag = gD.exitGroup(con, GName);
                    if (flag){
                        if (gD.GExitFriend(con,GName)){
                            JOptionPane.showMessageDialog(jPanel, "请先删除改组中好友", "",JOptionPane.WARNING_MESSAGE);
                        }else {
                            int i = gD.deleteGroup(con, GName);
                            if (i>0){
                                JOptionPane.showMessageDialog(jPanel, "删除成功", "success",JOptionPane.WARNING_MESSAGE);
                                jTextField1.setText("");
                                System.out.println("删除成功");
                            }else {
                                JOptionPane.showMessageDialog(jPanel, "删除失败", "",JOptionPane.WARNING_MESSAGE);
                            }

                        }

                    }else {
                        JOptionPane.showMessageDialog(jPanel, "不存在组名", "fail",JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    //改组
    public void  updGroup() throws SQLException {

        jPanel = new JPanel();
        jPanel.setBounds(450, 250, 300, 250);
        jPanel.setLayout(null);
        this.add(jPanel);
        this.setVisible(true);
        this.setBounds(450, 250, 300, 250);

        jLabel1 = new JLabel("旧组名");
        jLabel1.setBounds(30,10,50,30);
        jTextField1 = new JTextField();
        jTextField1.setBounds(70,35,160,25);
        jLabel2 = new JLabel("新组名");
        jLabel2.setBounds(30,30,50,30);
        jTextField2 = new JTextField();
        jTextField2.setBounds(70,10,160,25);

        jPanel.add(jLabel1);
        jPanel.add(jTextField1);
        jPanel.add(jLabel2);
        jPanel.add(jTextField2);

        EXIT = new JButton("退出");
        EXIT.setBounds(200, 100, 60, 30);
        jPanel.add(EXIT);

        EXIT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("退出");
                dispose();//只退出当前窗口
            }
        });

        Upd = new JButton("修改");
        Upd.setBounds(30, 100, 60, 30);
        jPanel.add(Upd);


        Upd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("修改组名");
                String OName = jTextField2.getText();
                System.out.println(OName);
                String NName = jTextField1.getText();
                jdbc jdbc = new jdbc();
                Connection con = jdbc.getConnection();
                groupDao gD = new groupDaoImp();
                personDao pD = new personDaoImp();
                try {
                    System.out.println(gD.exitGroup(con,OName));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                try {
                    if (gD.exitGroup(con,OName)){
                        int flag = gD.updateGroup(con, OName, NName);
                        if (flag>0){
                            JOptionPane.showMessageDialog(jPanel, "修改成功", "success",JOptionPane.WARNING_MESSAGE);
                            jTextField1.setText("");
                            jTextField2.setText("");
                            //修改好友的组名
                            List<String> friends = gD.FindFriendByGroup(con, OName);
                            int i = 0 ;
                            for (String friend:friends){
                                i = pD.updateFGName(con, friend, NName);
                            }
                            System.out.println(i);

                        }else {
                            JOptionPane.showMessageDialog(jPanel, "修改失败", "success",JOptionPane.WARNING_MESSAGE);
                        }
                    }else {
                        JOptionPane.showMessageDialog(jPanel, "不存在该组名", "success",JOptionPane.WARNING_MESSAGE);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

}
