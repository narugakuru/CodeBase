package lanqiao12;

import java.text.DecimalFormat;
import java.util.Scanner;

public class t06时间显示 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("00");
        long time = sc.nextLong() / 1000;
        int hour, minute, cecond;

        time %= 3600 * 24;
        hour = (int) time / 3600;
        time %= 3600;
        minute = (int) time / 60;
        cecond = (int) time % 60;

        System.out.println(df.format(hour) + ":" + df.format(minute) + ":" + df.format(cecond));
    }


}
