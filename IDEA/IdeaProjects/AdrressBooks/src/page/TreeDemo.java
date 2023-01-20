package page;

import dao.groupDao;
import dao.groupDaoImp;
import dao.personDaoImp;
import poji.person;
import util.jdbc;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TreeDemo {

    public JTree  getTree() throws SQLException {

        jdbc jdbc = new jdbc();
        Connection con = jdbc.getConnection();
        groupDao gD = new groupDaoImp();
        personDaoImp pD = new personDaoImp();
        List<String> groups = gD.findGroup(con);//获取所有组名
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("通讯录");

        for (String group:groups){
            DefaultMutableTreeNode Node = new DefaultMutableTreeNode(group);
            System.out.println(  Node.toString());
            List<person> people = pD.findPByGName(con, group);//获取当前组名下所有的人
            for (person pers:people){
                String name = pers.getName();
                DefaultMutableTreeNode node =  new DefaultMutableTreeNode(name);
                System.out.println(node);
                Node.add(node);
            }

            top.add(Node);
        }

        final JTree tree = new JTree(top);
        return tree;
    }

    public static void main(String[] args) throws SQLException {
        TreeDemo treeDemo = new TreeDemo();
        JTree tree = treeDemo.getTree();
        String string = tree.getLastSelectedPathComponent().toString();
        System.out.println(string);

    }

}

