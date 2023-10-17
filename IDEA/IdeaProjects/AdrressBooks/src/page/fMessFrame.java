package page;

import dao.groupDao;
import dao.groupDaoImp;
import org.omg.CORBA.PUBLIC_MEMBER;
import util.jdbc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ProjectName: AdrressBooks
 * @Package: page
 * @ClassName: fMessFrame
 * @Author: JN
 * @Description: 1
 * @Date: 2020/12/4 9:29
 * @Version: 1.0
 */
public class fMessFrame extends JFrame {

    JPanel jPanel1 = null;
    JLabel jLabel1 = null;
    JLabel jLabel2 = null;
    JLabel jLabel3 = null;
    JLabel jLabel4 = null;
    JLabel jLabel5 = null;
    JLabel jLabel6 = null;

    JTextField jTextField1 = null;
    JTextField jTextField2 = null;
    JTextField jTextField3 = null;
    JTextField jTextField4 = null;
    JComboBox  jComboBox   = null;

    JButton jButton1 = null;
    JButton jButton2 = null;
    JRadioButton jRadioButton1 = null;
    JRadioButton jRadioButton2 = null;
    ButtonGroup bg;				//定义按钮组（注意这个不是组件，它是个作用域，我只是把它定义在这里而已）


    public fMessFrame(String name,String bName) throws SQLException {

        jPanel1=new JPanel();

        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jTextField4 = new JTextField();
        jComboBox = new JComboBox();

        jdbc jdbc = new jdbc();
        Connection con = jdbc.getConnection();
        groupDao gD = new groupDaoImp();
        List<String> groups = gD.findGroup(con);

        jComboBox.addItem(null);

        for (String group:groups){
            jComboBox.addItem(group);
        }



        jLabel1 = new JLabel("     姓名:");
        jLabel2 = new JLabel("     手机:");
        jLabel3 = new JLabel("     电话");
        jLabel4 = new JLabel("     地址:");
        jLabel5 = new JLabel("     归属组:");
        jLabel6 = new JLabel( "    性别");

        jButton1 = new JButton(bName);
        jButton2 = new JButton("取消");

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.add(jPanel1);

        jPanel1.add(jLabel1);
        jPanel1.add(jTextField1);
        jPanel1.add(jLabel2);
        jPanel1.add(jTextField2);
        jPanel1.add(jLabel3);
        jPanel1.add(jTextField3);
        jPanel1.add(jLabel4);
        jPanel1.add(jTextField4);
        jPanel1.add(jLabel5);
        jPanel1.add(jComboBox);
      //jPanel1.add(jLabel6);



        jRadioButton1=new JRadioButton("男");			//创建单选框
        jRadioButton2=new JRadioButton("女");
        bg=new ButtonGroup();				                //创建按钮组
        bg.add(jRadioButton1);
        bg.add(jRadioButton2);
        jPanel1.add(jRadioButton1);
        jPanel1.add(jRadioButton2);

        jPanel1.add(jButton1);
        jPanel1.add(jButton2);

        jTextField1.setBounds(10,10,50,20);

        jPanel1.setLayout(new GridLayout(7,2,5,10));

        this.setTitle(name);		          //设置界面标题
        this.setBounds(20, 50, 350, 380);
        this.setVisible(true);				//设置界面可视化

    }

    public static void main(String[] args) throws SQLException {

        new fMessFrame("1","");
    }

}
