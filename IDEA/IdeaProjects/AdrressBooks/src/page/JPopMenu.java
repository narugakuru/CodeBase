package page;

/**
 * @ProjectName: AdrressBooks
 * @Package: page
 * @ClassName: JPopMenu
 * @Author: JN
 * @Description:
 * @Date: 2020/12/16 15:46
 * @Version: 1.0
 */
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import dao.personDao;
import dao.personDaoImp;
import util.jdbc;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class JPopMenu extends JFrame {

    JPopupMenu popupMenu1 = null;
    JPopupMenu popupMenu2 = null;

    JMenuItem jMenu1Item1 = null;
    JMenuItem jMenu1Item2 = null;
    JMenuItem jMenu1Item3 = null;
    JMenuItem jMenu1Item4 = null;
    JMenuItem jMenu2Item1 = null;
    JMenuItem jMenu2Item2 = null;
    JMenuItem jMenu2Item3 = null;
    JMenuItem jMenu2Item4 = null;

    JScrollPane jsp = null;
    JTree tree = null;


    public JPopMenu() throws SQLException {


        TreeDemo treeDemo = new TreeDemo();
        tree = treeDemo.getTree();
        tree.setBounds(0,0,400,550);
        this.add(tree);
        jMenu1Item1 = new JMenuItem("添加组");
        jMenu1Item2 = new JMenuItem("删除组");
        jMenu1Item3 = new JMenuItem("修改组名");
        jMenu1Item4 = new JMenuItem("退出");

        jMenu2Item1 = new JMenuItem("查询好友");
        jMenu2Item2 = new JMenuItem("添加好友");
        jMenu2Item3 = new JMenuItem("修改好友");
        jMenu2Item4 = new JMenuItem("删除好友");

        // 实例化弹出菜单
        popupMenu1 = new JPopupMenu();
        popupMenu2 = new JPopupMenu();

        // 增加菜单项到菜单上
        popupMenu1.add(jMenu1Item1);
        popupMenu1.add(jMenu1Item2);
        popupMenu1.add(jMenu1Item3);
        popupMenu1.add(jMenu1Item4);

        popupMenu2.add(jMenu2Item1);
        popupMenu2.add(jMenu2Item2);
        popupMenu2.add(jMenu2Item3);
        popupMenu2.add(jMenu2Item4);

        jsp = new JScrollPane(tree);
        add(jsp);


        tree.addMouseListener(new TreeMouseListener());

        setSize(350, 300); // 设置窗口大小
        setLocation(400, 200);
        setVisible(true); // 设置窗口为可视
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭窗口时退出程序


    }

    private class TreeMouseListener extends MouseAdapter {

        public void  mousePressed(MouseEvent evt)
        {

                int x = evt.getX();
                int y = evt.getY();
                tree = (JTree) evt.getSource();
                TreePath treePath = tree.getPathForLocation(x, y);
                System.out.println(treePath);
                if (treePath != null)
                {
                    tree.setSelectionPath(treePath);
                    int pathCount = treePath.getPathCount();
                    if (pathCount == 2)
                    {
                        //获取被选中的标签名称
                        String treeLabel = tree.getLastSelectedPathComponent().toString();
                        System.out.println(treeLabel);
                        popupMenu1.show(evt.getComponent(), evt.getX(),
                                evt.getY());

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
                        });
                        jMenu1Item3.addActionListener(new ActionListener() {
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
                    }else if (pathCount == 3)
                    {
                        String treeLabel = tree.getLastSelectedPathComponent().toString();
                        System.out.println(treeLabel);
                        popupMenu2.show(evt.getComponent(), evt.getX(),
                                evt.getY());
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
                        System.out.println("3");
                    }
                }
            }
        }

    public static void main(String[] args) throws SQLException {
        JPopMenu jPopMenu = new JPopMenu();


    }


}