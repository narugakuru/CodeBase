package lanqiao12;

import java.util.Scanner;

public class t07最少砝码 {
//当砝码为{1}的时候，可以称出任意小于等于1的正整数重量。
//当砝码为{1,3}的时候，可以称出任意小于等于4的正整数重量。
//当砝码为{1,3,9}的时候，可以称出任意小于等于13的正整数重量。
//当砝码为{1,3,9,27}的时候，可以称出任意小于等于40的正整数重量。
//当砝码为{1,3,9,27,81}的时候，可以称出任意小于等于121的正整数重量。
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long ans = 0;
        while (n > 0) {
            n -= Math.pow(3, ans);
            ans++;
        }
        System.out.println(ans);
    }
}
