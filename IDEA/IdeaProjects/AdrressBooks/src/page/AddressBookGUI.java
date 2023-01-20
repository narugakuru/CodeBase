package page;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

class AddressBookGUI extends JFrame  {


    //菜单栏
    JMenuBar jMenuBar = new JMenuBar();
    JMenu    jMenu1 =  new JMenu("组操作");
    JMenu    jMenu2 =  new JMenu("好友操作");
    JMenu    jMenu3 =  new JMenu("帮助");

    JMenuItem jMenu1Item1 = new JMenuItem("添加组");
    JMenuItem jMenu1Item2 = new JMenuItem("删除组");
    JMenuItem jMenu1Item3 = new JMenuItem("修改组名");
    JMenuItem jMenu1Item4 = new JMenuItem("退出");

    JMenuItem jMenu2Item1 = new JMenuItem("查询好友");
    JMenuItem jMenu2Item2 = new JMenuItem("添加好友");
    JMenuItem jMenu2Item3 = new JMenuItem("修改好友");
    JMenuItem jMenu2Item4 = new JMenuItem("删除好友");

    JScrollPane jsp = null;

    // 面板
    JPanel panel = new JPanel();
  //  JPanel panel2=new JPanel();


    //按钮
    JButton flush = new JButton("刷新");
    JButton exit = new JButton("退出");

    JLabel jLabel =  new JLabel("bgm: ");
    JButton begin = new JButton("begin");
    JButton stop =  new JButton("stop");

    JLabel wel = null;

    private ActionEvent e;


    public AddressBookGUI() throws SQLException {
/*
        Login login = new Login();
        Class<? extends ActionListener[]> aClass = login.okButton.getActionListeners().getClass();

        System.out.println("1111111111111111111111111");
        //System.out.println(loginName);
        System.out.println("1111111111111111111111111");
        login.dispose();*/
       // wel = new JLabel("欢迎"+loginName);
       /* this.add(wel);
        wel.setBounds(40, 440, 120, 40);*/
        panel.setLayout(null);
       /* panel2.setBounds(20, 50, 350, 380);
        panel2.setBackground(Color.gray);
        panel.add(panel2);*/
      //  panel.setBackground(Color.gray);

        jLabel.setBounds(40, 250, 120, 40);
        panel.add(jLabel);
        begin.setBounds(75, 280, 100, 30);
        panel.add(begin);
        stop.setBounds(200, 280, 100, 30);
        panel.add(stop);

        flush.setBounds(40, 400, 120, 40);
        panel.add(flush);
        exit.setBounds(230, 400, 120, 40);
        panel.add(exit);

        jMenu1.add(jMenu1Item1);
        jMenu1.add(jMenu1Item2);
        jMenu1.add(jMenu1Item3);
        jMenu1.add(jMenu1Item4);
        jMenu2.add(jMenu2Item1);
        jMenu2.add(jMenu2Item2);
        jMenu2.add(jMenu2Item3);
        jMenu2.add(jMenu2Item4);

/*      TreeDemo tree = new TreeDemo();
        JTree tree1 = tree.getTree();
        tree1.setBounds(0,0,400,500);
        panel.add(tree1);
*/

        JPopMenu jPopMenu = new JPopMenu();
        jPopMenu.dispose();
        panel.add(jPopMenu.tree);

        Music music = new Music();
        music.play();

        begin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                music.play();
                System.out.println("播放音乐");
            }
        });
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                music.stop();
                System.out.println("暂停音乐");
            }
        });

       /* jsp = new JScrollPane(jPopMenu.tree);
        panel.add(jsp);*/

        //监听器
        jMenu1Item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("添加组");
                groupFrame gF = new groupFrame("添加组");
                gF.addGroup();
            }
        });
        jMenu1Item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("删除组");
                groupFrame  gF = new groupFrame("删除组");
                gF.delGroup();
            }
        });jMenu1Item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("修改组名");
                groupFrame  gF = new groupFrame("修改组");
                try {
                    gF.updGroup();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        jMenu1Item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("退出");
                System.exit(0);
            }
        });
        jMenu2Item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                friendFrame fF = new friendFrame();
                fF.findFriend("查询好友");
                System.out.println("查询好友");
            }
        });
        jMenu2Item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                friendFrame fF = new friendFrame();
                try {
                    fF.addFriend("添加好友");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.out.println("添加好友");
            }
        });
        jMenu2Item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                friendFrame fF = new friendFrame();
                fF.updFriend("修改好友");
                System.out.println("修改好友");
            }
        });
        jMenu2Item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                friendFrame fF = new friendFrame();
                try {
                    fF.delFriend("删除好友");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.out.println("删除好友");
            }
        });
        flush.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("刷新");
                dispose();
                try {
                    new AddressBookGUI();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        jMenuBar.add(jMenu1);
        jMenuBar.add(jMenu2);
        jMenuBar.add(jMenu3);

        setJMenuBar(jMenuBar);

        this.add(panel);
        this.setBounds(400, 150, 400, 550);
        this.setTitle("通讯录");
        this.setVisible(true);
        this.setResizable(false);

    }


    public static void main(String[] args) throws SQLException {
        AddressBookGUI addressBookGUI = new AddressBookGUI();
    }
}