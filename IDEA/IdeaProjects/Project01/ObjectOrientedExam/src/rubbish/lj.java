package rubbish;

import java.util.Scanner;

public class lj {
    public static void main(String[] args) {
        int h,m,s,t;

        Scanner sc = new Scanner(System.in);

        t=sc.nextInt();

        h=t/3600;
        m=t%3600/60;
        s=t%60;

        System.out.println(h+":"+m+":"+s);


    }

}
