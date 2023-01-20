package exercise;

import java.util.Arrays;
import java.util.Scanner;

/*
题目描述
给出长度为N的各不相同整数组成的数组，求解2个数相加为M的情况个数。

输入说明
第一行，2个空格分隔的整数，分别表示数组中元素个数N(N<1000) 和值M；

第二行，N个用空格分隔的数组元素(整数)。*/
public class demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] array = new int[]{1, 4, 2, 5, 3, 19, 8, 6};
        int num = 8, m = 10;
        int count = 0;
/*
        num = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < num; i++) {
            array[i] = sc.nextInt();
        }
*/
        Arrays.sort(array);
//        System.out.println(Arrays.toString(array));
        int left = 0,right= array.length-1;
        //最佳用时n/2，最坏为n-1，时间复杂度n；
        while(left<right){
            int tmp = array[left]+array[right];
            if (tmp==m){
                count++;
                left++;
                right--;
            }else {
                if (tmp>m){
                    right--;
                }else{
                    left++;
                }
            }
        }
        System.out.println(count);
    }
}
