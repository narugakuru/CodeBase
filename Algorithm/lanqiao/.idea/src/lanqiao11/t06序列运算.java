package lanqiao11;

import java.util.Scanner;

public class t06序列运算 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Long n = sc.nextLong();
        sc.close();

        System.out.print(n);
        n = n >> 1;
        while (n != 0) {
            System.out.print(" " + n);
            n = n >> 1; // 等价于/2，位运算相对快一些
        }
    }


}
