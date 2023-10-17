import javax.swing.*;
import java.io.*;
import java.util.*;

//如何以图形界面输入非字符串数据 JOptionpane.ShowInputDialog
public class Input_Sum {

    public static class Date {

        static void input_SC() throws IOException {//非图形
            System.out.println("Scanner：int,double,next,line.");

            Scanner sc = new Scanner(System.in);
            //char c=(char)System.in.read();//输入单个字符，不常用
//            int a = sc.nextInt();//输入一个整数

//            double b = sc.nextDouble();//输入一个双精度的浮点数

            String str=sc.next();//输入一个单词，遇到空格则输入终止

            System.out.println("to ma runn jia ne yo !");
            String str2 = sc.nextLine();//输入一行，中间可有多个空格，回车结束输入

//            System.out.println("saki input is " + a + b);
            System.out.println("这是1号哒！？"+str);
            System.out.println("这是2号哒？！" + str2);
        }

        static void input_BF() throws IOException {
            System.out.println("BufferedReader：readline,Integer,Double");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//专用于字符输入
            String str = br.readLine();//输入一行
            System.out.println(": " + str);

            String str2 = br.readLine();
            int a = Integer.parseInt(str2);//将str2转换为int,并复制给a
            System.out.println(": " + a);

            String str3 = br.readLine();
            double b = Double.parseDouble(str3);//将str3转换为double,并复制给b
            System.out.println(": " + b);

        }

        static void input_JOP() {
			/*错误的结果
			JOptionPane jop=new JOptionPane();
			String jj=jop.showInputDialog();
			jop.showMessageDialog(null,"fa***?"+);
			*/

            String str = JOptionPane.showInputDialog("input A number: ");
            double dd = Double.parseDouble(str);//java字符串型数据向double型转换，有时不是必要语句
            //转换成其他类型格式也一样：Integer.parseInt()等；
            JOptionPane.showMessageDialog(null, "fa**k?" + str);

            ///////////////////////
            //另一种输入与转化结合，代码更为简洁；
            int ss = Integer.parseInt(JOptionPane.showInputDialog("int嘤嘤嘤："));
            JOptionPane.showMessageDialog(null, "ko no dio daze：" + ss);

        }
    }

    public static void main(String args[]) throws IOException {
//        Date.input_SC();
//        Date.input_BF();
        Date.input_JOP();
    }


}