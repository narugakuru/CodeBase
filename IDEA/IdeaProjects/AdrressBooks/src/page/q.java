package page;

import javax.swing.*;

/**
 * @ProjectName: AdrressBooks
 * @Package: page
 * @ClassName: q
 * @Author: JN
 * @Description:
 * @Date: 2020/12/14 10:36
 * @Version: 1.0
 */
public class q extends JFrame {

   JComboBox jComboBox;
   JPanel jPanel;
   JRadioButton jRadioButton1 = null;
   JRadioButton jRadioButton2 = null;
   ButtonGroup bg;


    public q(){
       jPanel = new JPanel();
     /*  jComboBox = new JComboBox();
       jComboBox.addItem("1");
       jComboBox.addItem("2");
       jComboBox.addItem("3");*/
       jRadioButton1=new JRadioButton("男");			//创建单选框
       jRadioButton2=new JRadioButton("女");
       bg=new ButtonGroup();				                //创建按钮组
       bg.add(jRadioButton1);
       bg.add(jRadioButton2);


       jPanel.add(jRadioButton1);
       jPanel.add(jRadioButton2);
       this.add(jPanel);

       this.setBounds(100,100,300,300);
       setVisible(true);
       setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

   }

    public static void main(String[] args) {
        q q = new q();

       /* q.jComboBox.setSelectedItem("3");//设置下框的值
        String string = q.jComboBox.getSelectedItem().toString();//获取下框的值
        System.out.println(string);*/



     //  q.jRadioButton1.setSelected(true);//设置单选框选中

       String a = "男";
       /*if (q.jRadioButton1.isSelected()){//获取单选框的值
           a=q.jRadioButton1.getText();
       }else {
           a=q.jRadioButton1.getText();
       }

        System.out.println(a);*/


       if (a=="女"){
           q.jRadioButton1.setSelected(true);
       }else {
           q.jRadioButton2.setSelected(true);
       }
    }
}
