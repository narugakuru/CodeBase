

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int limit;

    public static void main(String[] args) {
        int n, m;
        int sum = 0;
        n = 3;
        m = 4;
//        int[][] matrix = new int[n][m];
        int[][] matrix = {{2, 0, 7, 9}, {0, 6, 9, 7}, {8, 4, 6, 4}};
        limit = 8;
        int x1, y1;
        int x2, y2;
        System.out.println("开始！！！");
        for (int i = n; i > 0; i--) {
            x1 = 0;
            y1 = 0;
            int flag = 1;
            for (int j = m; j > 0; j--) {
                x2 = i;
                y2 = j;
                System.out.println("开始while!!!");
                while (true) {
                    //什么时候遍历完
                    if ((m - j) % 2 == 0) {
                        if (x2 == n && y2 == m) {
                            if (cheat(matrix, x1, y1, x2, y2)) {
                                sum++;
                            }
                            break;
                        }
                    } else {
                        if (x2 == i && y2 == m) {
                            break;
                        }
                    }
                    //右移
                    if (x2 < n && flag == 1) {
                        x1 += 1;
                        x2 += 1;
                        if (x2 == n) {
                            flag = 2;
                        }
                        if (cheat(matrix, x1, y1, x2, y2)) {
                            sum++;
                        }
                        System.out.println("右移");
                        continue;
                    }
                    //下移
                    if (flag == 2 && y2 < m) {
                        y1 -= 1;
                        y2 -= 1;
                        if (cheat(matrix, x1, y1, x2, y2)) {
                            sum++;
                        }
                        flag = 3;
                        System.out.println("下移");
                        continue;
                    }
                    //左移
                    if (x1 > 0 && flag == 3) {
                        x1 -= 1;
                        x2 -= 2;
                        if (cheat(matrix, x1, y1, x2, y2)) {
                            sum++;
                        }
                        if (x1 == 0) {
                            flag = 1;
                        }
                        System.out.println("左移");
                        continue;
                    }
                }
            }
        }

        System.out.println(sum);

    }

    //检验小矩阵的是否符合limit
    static boolean cheat(int[][] matrix, int x1, int y1, int x2, int y2) {
        int max = matrix[x1][y1];
        int min = matrix[x1][y1];
        int temp = matrix[x1][y1];
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                temp = matrix[i][j];
                if (temp > max) {
                    max = temp;
                }
                if (temp < min) {
                    temp = min;
                }
            }
        }
        int cha = max - min;
        return cha <= limit;
    }

}







