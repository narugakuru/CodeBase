package lanqiao12;

import java.util.Scanner;

public class t08杨辉三角 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n;
        System.out.println(solve(sc.nextLong()));

    }

    public static long solve(long n) {
        long res = 0L;
        long[] san = new long[50010];
        san[1] = 1;
        //san[x]表示当前层的第x个位置，这是为了方便后面在每层第一个位置执行san[j] = san[j-1]+san[j]不出现数组越界的情况
        if (n == 1) return 1;
        long cur = 0L;
        for (int i = 2; i < 50000; i++) {
            //cur现在所在的位置是上上层的最后一个位置
            //然后通过+i是到了本层第一个位置，再加i-1就到了这层最后一个位置
            //加一起也就是cur+2*i-2
            cur += 2 * i - 1;
            for (int j = i; j >= 1; j--) {
                san[j] = san[j - 1] + san[j];
                if (san[j] == n) res = cur;
                cur--;
            }
            //这里一直减到了本层第一后位置后还要--，所以一层循环完cur的位置就到了上一层的最后一个位置
            //下一次循环的时候i++了，所以下一次循环开始前cur的位置是上上层的最后一个位置
            if (res != 0) return res;
        }
        //在前五万行都找不到n这个数，也就是说n这个数不可能出现在五万行以后的第二个位置
        //因为五万行以后的第二个位置的值都比1000000000大，所以n第一次出现的位置必然是第n+1行的第三个位置，也就是C上标1，下标n的值

        return (n + 1) * n / 2 + 2;
        //第一行是1个数，第n行是n个数，而且是等差数列，用等差数列求和公式算出前n行的值加2就是n+1行第二个数的位置了
    }


//    public static void main(String[] args) {
//        new lanqiao12.t08杨辉三角().run();
//
//    }

    int N;

    void run() {
        N = new Scanner(System.in).nextInt();
        if (N == 1)
            System.out.println(1);
        else {
            long ans = (N + 1L) * N / 2 + 2;
            for (int m = 2; m < 16; m++) {
                int start = m * 2, end = N;
                while (start <= end) {
                    int mid = start + end >> 1;
                    if (C(mid, m) == N) {
                        ans = min(ans, (mid + 1L) * mid / 2 + m + 1);
                        break;
                    }
                    if (C(mid, m) > N)
                        end = mid - 1;
                    else
                        start = mid + 1;
                }
            }
            System.out.println(ans);
        }
    }

    long min(long a, long b) {
        return a < b ? a : b;
    }

    long C(int n, int m) {
        long num = 1;
        for (int nm = 1; nm <= m; n--, nm++)
            if ((num = num * n / nm) > N)
                return num;
        return num;
    }


}
