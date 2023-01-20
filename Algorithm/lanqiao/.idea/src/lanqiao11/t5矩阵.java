package lanqiao11;

public class t5矩阵 {
/*    public static void main(String[] args) {
        int n = 2020;
        // dp[i][j]表示，从i个数选j个数
        int[][] dp = new int[2021][2021];
        dp[1][1] = 1; // 1必然放在第一行
        // 只要保证第一行的数比第二行的数多就可以了，后面的数会越来越大，会符合小到大的规律
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] += dp[i - 1][j - 1]; // 放到第一层
                if (i - j <= j) {
                    // 当进入if说明，i没有超过j的两倍了，
                    // （如果超过j的两倍，说明第一行肯定比第二行少了）
                    // 本身就是取一半的数字，如果超过两倍，说明取不到一半，不能参与计算
                    dp[i][j] += dp[i - 1][j]; // 放到第二层
                }
                dp[i][j] %= 2020;
            }
        }
        System.out.println(dp[n][n / 2]);
    }*/


    public static void main(String[] args) {
        int[][] dp = new int[2021][2021];
        dp[1][1] = 1; // 1必然放在第一行
        for (int i = 2; i <= 2020; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] += dp[i - 1][j - 1];
                if (i - j <= j) {//只能放到第二层
                    dp[i][j] += dp[i - 1][j];
                }
                dp[i][j] %= 2020;
            }
        }
        System.out.println(dp[2020][1010]);
    }



}
