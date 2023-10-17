package rubbish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class lj2 {
    public static void main(String[] args) throws IOException {
        String a_str, b_str;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("输入A,B字符串: ");
//        a_str = br.readLine();
//        b_str = br.readLine();
        a_str = "beijing";
        b_str = "BeiJing";
//        1，长度不同
        if (a_str.length() != b_str.length()) {
            System.out.println("1");
        }
//        2，完全相同
        else if (a_str.equals(b_str)) {
            System.out.println("2");
        }
//        3，长度相同，大小写不一致
        else if (a_str.compareToIgnoreCase(b_str) == 0) {
            System.out.println("3");
        }
//        4，仅仅长度相同
//        因为上面已经把其他可能筛除，所以只需考虑长度
        else if (a_str.length() == b_str.length()) {
            System.out.println("4");
        }


    }


}
