package lanqiao12;

import java.util.*;

public class t04货物摆放 {

    public static void main(String[] args) {

        long n = 2021041820210418L;

//     * 创建因子数组，为什么是128长度，这是因为事先输出了 count = 64，
//     *  然后因为 因子是对称的，且 根号n 无法开整数方根，所以 因子的个数为 2 *count = 128
        long[] zhi = new long[128];
        int count = 0;
        long i;
        for (i = 1; i * i < n; i++) {
            if (n % i == 0) {
                zhi[count] = i; //如果n能整除 i ,则将i装入数组中
                count++; // 求因子个数，并且作为 数组的索引值
            }
        }
        int j = 0;
        for (j = 64; j < 128; j++) {  //求n的另一部分因子，另一部分的因子 = n / 对称因子（比如63和64是对称的，62和65是对称的）
            zhi[j] = n / zhi[127 - j];
        }

        int s = 0;    //开始遍历因子数组，如果相乘等于 n ，则计数器计数一次
        for (int x = 0; x < 128; x++) {
            for (int p = 0; p < 128; p++) {
                if (zhi[x] * zhi[p] > n) break; // 减少一定的 “余坠”，大于n直接跳出，减少复杂度和运算时间
                for (int q = 0; q < 128; q++) {
                    if (zhi[x] * zhi[p] * zhi[q] == n) {
                        s++;
                    }
                }
            }
        }
        System.out.println(s);
    }


    //递归解
//    public static void main(String[] args) {
//        new lanqiao12.t04货物摆放().run();
//    }

    long n = 2021041820210418L;

    void run() {
        List<Integer> exps0 = new ArrayList();
        ArrayDeque<Integer> exps1 = new ArrayDeque();
        for (int k = 2; k <= n; k++)
            if (n % k == 0) {
                int e = 0;
                while (n % k == 0) {
                    n /= k;
                    e++;
                }
                exps0.add(e);
            }
        System.out.println(dfs(exps0, exps1, 0)); // 2430
    }

    int dfs(List<Integer> exps0, ArrayDeque<Integer> exps1, int cur) {
        if (cur == exps0.size()) {
            int comb = 1;
            for (int exp : exps1)
                comb *= exp + 1;
            return comb;
        }
        int ans = 0;
        for (int i = exps0.get(cur); i >= 0; i--) {
            exps1.push(i);
            ans += dfs(exps0, exps1, cur + 1);
            exps1.pop();
        }
        return ans;
    }


}
