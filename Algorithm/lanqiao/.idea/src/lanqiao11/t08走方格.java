package lanqiao11;

import java.util.Scanner;

/*
【问题描述】

在平面上有一些二维的点阵。
这些点的编号就像二维数组的编号一样，从上到下依次为第 1 至第 n 行，从左到右依次为第 1 至第 m 列，
每一个点可以用行号和列号来表示。

现在有个人站在第 1 行第 1 列，要走到第 n 行第 m 列。只能向右或者向下走。
注意，如果行号和列数都是偶数，不能走入这一格中。

问有多少种方案。

对于所有评测用例，1 ≤ n ≤ 30, 1 ≤ m ≤ 30。
*/
public class t08走方格 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            grid[i][0] = 1;
        }
        for (int i = 0; i < m; i++) {
            grid[0][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (i % 2 == 1 && j % 2 == 1) {
                    continue;
                }
                grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
            }
        }
        System.out.println(grid[n - 1][m - 1]);
    }

}
