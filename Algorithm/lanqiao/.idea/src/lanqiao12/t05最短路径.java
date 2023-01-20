package lanqiao12;

import java.text.*;
/*
用辗转相除法求最大公约数 x%y ? gcd(y, x % y) : y
然后用最大公约数求最小公倍数 x * y / gcd(x, y)
这样就可以得到一个2021*2021的无向图，用邻接矩阵存储这张图，然后dijkstra算法求最短路径
* */
public class t05最短路径 {
    public static void main(String[] args) {
        int map[][] = new int[2050][2050];//存大点下面初始化的时候不用考虑数据越界

        for (int i = 1; i <= 2021; i++) {
            for (int j = i; j <= i + 21; j++) {
                //大的数放前面，或者在求最大公约数的时候加一个交换操作
                map[i][j] = lcm(j, i);
            }
        }
        boolean bj[] = new boolean[2050];//用来表示该点是否已经是最短
        int dis[] = new int[2050];//用来存储源点到其他顶点的初始路径
        for (int i = 1; i <= 2021; i++) dis[i] = map[1][i];

        int min, minIdx = 0;
        while (!bj[2021]) {//如果2021点的最短路径还没求到就一直循环
            min = Integer.MAX_VALUE;
            for (int i = 2; i <= 2021; i++) {
                if (!bj[i] && dis[i] != 0 && dis[i] < min) {
                    min = dis[i];
                    minIdx = i;
                }
            }
            bj[minIdx] = true;
            for (int i = minIdx + 1; i <= minIdx + 21; i++) {
                if (map[minIdx][i] != 0) {//我初始化的时候没有把邻接矩阵的初始值赋一个大的数，所以这里要特判一下
                    if (dis[i] == 0) dis[i] = dis[minIdx] + map[minIdx][i];
                    else {
                        if (dis[minIdx] + map[minIdx][i] < dis[i]) dis[i] = dis[minIdx] + map[minIdx][i];
                    }
                }
            }
        }
        System.out.println(dis[2021]);

    }

    public static int gcd(int x, int y) {//欧几里得算法
        return x % y != 0 ? gcd(y, x % y) : y;
    }

    public static int lcm(int x, int y) {//最小公倍数
        return x * y / gcd(x, y);
    }
}
