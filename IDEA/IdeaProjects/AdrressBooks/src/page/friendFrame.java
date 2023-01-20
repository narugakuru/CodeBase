package page;

import dao.groupDao;
import dao.groupDaoImp;
import dao.personDao;
import dao.personDaoImp;
import poji.person;
import util.jdbc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ProjectName: AdrressBooks
 * @Package: page
 * @ClassName: friendFrame
 * @Author: JN
 * @Description: 2
 * @Date: 2020/11/27 16:29
 * @Version: 1.0
 */
public class friendFrame extends JFrame {

    JLabel jLabel = null;
    JButton EXIT = null;
    JButton Add = null;
    JButton Del= null;
    JButton Upd = null;
    JPanel jPanel = null;

    public friendFrame(){

    }

    //查询好友
    public void  findFriend(String name){
        jPanel = new JPanel();
        this.setTitle(name);
        jPanel.setBounds(450, 250, 300, 200);
        jPanel.setLayout(null);
        this.add(jPanel);
        this.setTitle(name);
        this.setVisible(true);
        this.setBounds(450, 250, 300, 200);

        JTextField jTextField = new JTextField();
        jPanel.add(jTextField);
        jTextField.setBounds(90,30,160,30);


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
        jLabel = new JLabel("好友名");
        jLabel.setBounds(30,30,50,30);
        Add = new JButton("查询");
        Add.setBounds(30, 100, 60, 30);
        jPanel.add(Add);
        jPanel.add(jLabel);


        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fMessFrame fF = null;
                String text = jTextField.getText();
                personDao Pd = new personDaoImp();
                jdbc jb = new jdbc();
                Connection con = jb.getConnection();
                try {
                    if (Pd.exitFriend(con,text)){
                        fF = new fMessFrame("好友信息","修改");
                        person person = Pd.selectFriend(con, text);
                        fF.jTextField1.setText(person.getName());
                        fF.jTextField2.setText(person.getPhone());
                        fF.jTextField3.setText(person.getMobile());
                        fF.jTextField4.setText(person.getAddress());
                        fF.jComboBox.setSelectedItem(person.getGroupName());
                        System.out.println(person.getGender());
                        if (person.getGender()=="女"){
                            fF.jRadioButton2.setSelected(true);
                        }else {
                            fF.jRadioButton1.setSelected(true);
                        }
                        //修改
                        fMessFrame finalFF = fF;
                        fF.jButton1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String name = finalFF.jTextField1.getText();
                                String phone = finalFF.jTextField2.getText();
                                String mobile = finalFF.jTextField3.getText();
                                String address = finalFF.jTextField4.getText();
                                String groupName = finalFF.jComboBox.getSelectedItem().toString();
                                String gender = null;
                                if (finalFF.jRadioButton1.isSelected()){
                                    gender=finalFF.jRadioButton1.getText();
                                }else {
                                    gender=finalFF.jRadioButton2.getText();
                                }
                                poji.person per = new person(name, phone, mobile, address, gender, groupName);
                                try {
                                    int i = Pd.updateFriend(con, per);
                                    if (i>0){
                                        JOptionPane.showMessageDialog(jPanel, "修改成功", "success",JOptionPane.WARNING_MESSAGE);
                                    }else {
                                        JOptionPane.showMessageDialog(jPanel, "修改失败", "fail",JOptionPane.WARNING_MESSAGE);
                                    }
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                    }else{
                        JOptionPane.showMessageDialog(jPanel, "不存在该好友", "fail",JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    //增加好友
    public void  addFriend(String name) throws SQLException {
        fMessFrame fF = new fMessFrame(name,"提交");
        fF.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = fF.jTextField1.getText();
                String phone = fF.jTextField2.getText();
                String mobile = fF.jTextField3.getText();
                String address = fF.jTextField4.getText();
                String gender = null;
                if(fF.jRadioButton1.isSelected()){
                    gender=fF.jRadioButton1.getText();
                }else {
                    gender=fF.jRadioButton2.getText();
                }
                String groupName = fF.jComboBox.getSelectedItem().toString();


                person person = new person(name,phone,mobile,address,gender,groupName);
                personDao Pd = new personDaoImp();
                jdbc jb = new jdbc();
                Connection con = jb.getConnection();
                try {
                    int i = Pd.addFriend(con, person);
                    if (i>0){
                        JOptionPane.showMessageDialog(jPanel, "添加成功", "success",JOptionPane.WARNING_MESSAGE);
                        fF.jTextField1.setText("");
                        fF.jTextField2.setText("");
                        fF.jTextField3.setText("");
                        fF.jTextField4.setText("");
                        System.out.println("添加成功");
                    }else {
                        JOptionPane.showMessageDialog(jPanel, "添加失败", "fail",JOptionPane.WARNING_MESSAGE);
                        fF.jTextField1.setText("");
                        fF.jTextField2.setText("");
                        fF.jTextField3.setText("");
                        fF.jTextField4.setText("");
                        System.out.println("添加失败");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }




    //修改好友
    public void  updFriend(String name){
        jPanel = new JPanel();
        this.setTitle(name);
        jPanel.setBounds(450, 250, 300, 200);
        jPanel.setLayout(null);
        this.add(jPanel);
        this.setTitle(name);
        this.setVisible(true);
        this.setBounds(450, 250, 300, 200);

        JTextField jTextField = new JTextField();
        jPanel.add(jTextField);
        jTextField.setBounds(90,30,160,30);


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
        jLabel = new JLabel("好友名");
        jLabel.setBounds(30,30,50,30);
        Upd = new JButton("修改");
        Upd.setBounds(30, 100, 60, 30);
        jPanel.add(Upd);
        jPanel.add(jLabel);


        Upd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personDao Pd = new personDaoImp();
                jdbc jb = new jdbc();
                Connection con = jb.getConnection();

                String text = jTextField.getText();
                try {
                    if (Pd.exitFriend(con,text)){
                        fMessFrame fF = new fMessFrame("修改好友", "提交");
                        fF.jTextField1.setText(jTextField.getText());
                        fF.jButton1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String name = fF.jTextField1.getText();
                                String phone= fF.jTextField2.getText();
                                String mobile = fF.jTextField3.getText();
                                String address = fF.jTextField4.getText();
                                String  gender = null;
                                if(fF.jRadioButton1.isSelected()){
                                    gender=fF.jRadioButton1.getText();
                                }else {
                                    gender=fF.jRadioButton2.getText();
                                }
                                String groupName = fF.jComboBox.getSelectedItem().toString();

                                person person = new person(name,phone,mobile,address,gender,groupName);
                                int i = 0;
                                try {
                                    i = Pd.updateFriend(con, person);
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                                if (i>0){
                                    JOptionPane.showMessageDialog(jPanel, "修改成功", "success",JOptionPane.WARNING_MESSAGE);
                                    System.out.println("修改成功");
                                }else {
                                    JOptionPane.showMessageDialog(jPanel, "修改失败", "fail",JOptionPane.WARNING_MESSAGE);
                                    fF.jTextField1.setText("");
                                    fF.jTextField2.setText("");
                                    fF.jTextField3.setText("");
                                    fF.jTextField4.setText("");

                                    System.out.println("修改失败");
                                }
                            }
                        });

                    }else {
                        JOptionPane.showMessageDialog(jPanel, "不存在该好友", "fail",JOptionPane.WARNING_MESSAGE);
                        jTextField.setText("");
                        System.out.println("修改失败");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    //删除好友
    public void delFriend(String name) throws SQLException {
        JLabel jLabel2 = new JLabel("归属组");
        jLabel2.setBounds(30,60,50,30);
        JComboBox jComboBox = new JComboBox();
        jComboBox.addItem(null);

        groupDao gD = new groupDaoImp();
        jdbc jb = new jdbc();
        Connection con = jb.getConnection();
        List<String> groups = gD.findGroup(con);
        for (String group:groups){
            jComboBox.addItem(group);
        }

        jComboBox.setBounds(90,70,160,30);
        jPanel = new JPanel();
        this.setTitle(name);
        jPanel.setBounds(450, 250, 300, 250);
        jPanel.setLayout(null);
        this.add(jPanel);
        this.setTitle(name);
        this.setVisible(true);
        this.setBounds(450, 250, 300, 200);

        JTextField jTextField = new JTextField();
        jPanel.add(jTextField);
        jTextField.setBounds(90,30,160,30);




        EXIT = new JButton("退出");
        EXIT.setBounds(200, 120, 60, 30);
        jPanel.add(EXIT);

        EXIT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("退出");
                dispose();//只退出当前窗口
            }
        });
        this.jLabel = new JLabel("好友名");
        this.jLabel.setBounds(30,30,50,30);
        Del = new JButton("删除");
        Del.setBounds(30, 120, 60, 30);

        Del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personDao Pd = new personDaoImp();
                jdbc jb = new jdbc();
                Connection con = jb.getConnection();
                String Fname = jTextField.getText();
                String groupName= jComboBox.getSelectedItem().toString();
                try {
                    if (Pd.exitFriend(con,Fname)){

                        int i = Pd.deleteFriendByDG(con, Fname, groupName);
                        if (i>0){
                            JOptionPane.showMessageDialog(jPanel, "删除成功", "success",JOptionPane.WARNING_MESSAGE);
                            jTextField.setText("");
                            jComboBox.setSelectedItem(null);
                        }else {
                            JOptionPane.showMessageDialog(jPanel, "删除失败,该组下不存在该好友", "fail",JOptionPane.WARNING_MESSAGE);
                        }

                    }else{
                        JOptionPane.showMessageDialog(jPanel, "不存在该好友", "fail",JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        jPanel.add(Del);
        jPanel.add(this.jLabel);
        jPanel.add(jLabel2);
        jPanel.add(jComboBox);

    }

}
