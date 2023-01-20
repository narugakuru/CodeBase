package exercise;

import java.util.*;

/*
mokit
题目描述
小明今天收了N个鸡蛋，每个鸡蛋各有重量，现在小明想找M个重量差距最小的鸡蛋摆成一盒出售，输出符合条件的最重一盒鸡蛋的总重量.。
输入说明
第一行，用空格分隔的2个整数，分别表示鸡蛋个数N(N<1000)和每盒个数M(M<N)；
第二行，N个鸡蛋重量（浮点）。
输出说明
1行，符合条件的最重一盒鸡蛋的总重量（保留2位小数）

* */
//8 4
//11 9 12 5 10 19 8 6
public class java2_1 {
    public static void main(String[] args) {
        solution();

    }

    //参考答案
    static void solution() {
        Scanner input = new Scanner(System.in);
        int N = 8, M = 4;

        ArrayList<Double> weight = new ArrayList<>();
        Collections.addAll(weight, 11.0, 9.0, 12.0, 5.0, 10.0, 19.0, 8.0, 6.0);

/*        int N = input.nextInt();
        int M = input.nextInt();
        for (int i = 0; i < N; i++) {
            weight.add(input.nextDouble());
        }*/

        Collections.sort(weight, new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                if (o1 > o2) {
                    return 1;
                } else if (o1 == o2) {
                    return 0;
                }
                return -1;
            }
        });

        double maxSum = 0;
        double minDifferValue = Double.MAX_VALUE;
        for (int i = 0; i < N - M; i++) {
            double sum = 0;
            double differValue = weight.get(i + M - 1) - weight.get(i);
            for (int j = 0; j < M; j++) {
                sum += weight.get(j + i); //以i为起点的一组鸡蛋的重量
            }
            if (differValue <= minDifferValue) {
                maxSum = sum;
                minDifferValue = differValue;
            }
        }
        System.out.println(String.format("%.2f", maxSum));
    }

    //？
    void demo() {
        int n = 8, m = 4;
        float[] arr = new float[]{11, 9, 12, 5, 10, 19, 8, 6};
        float[] ans = new float[n - 1];
        float[] res = new float[n - m + 1];
        System.arraycopy(arr, 0, ans, 0, 4);
        //M个重量差距最小
        //先排序nlogn，再求得个鸡蛋的重量差n，即转化为滑动窗口找最小值？
        Arrays.sort(arr);
        //重量差数组
        for (int i = 1; i < n; i++) {
            ans[i - 1] = arr[i] - arr[i - 1];
        }
        float tmp = 0;
        for (int i = 0; i < m; i++) {
            tmp += ans[i];
        }
        res[0] = tmp;
        for (int i = 1; i < res.length; i++) {
            res[i] = tmp - ans[i - 1] + ans[i + m - 1];
        }


//        maxSlidingWindow(ans, 4);
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> queue = new ArrayDeque<>(); //双端队列
        int[] res = new int[n - k + 1];
        for (int i = 0, j = 0; i < n; i++) {
            //判断队头是否在滑动窗口范围内
            while (!queue.isEmpty() && i - k + 1 > queue.getFirst()) queue.pollFirst();
            //维护单调递减队列
            while (!queue.isEmpty() && nums[i] > nums[queue.getLast()]) queue.pollLast();
            queue.offer(i);    //将当前元素插入队尾
            //滑动窗口的元素达到了k个，才可以将其加入答案数组中
            if (i - k + 1 >= 0) res[j++] = nums[queue.getFirst()];
        }
        return res;
    }

    //最大子串数组
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        f[0] = nums[0];
        int ans = f[0];
        for (int i = 1; i < n; i++) {
            f[i] = Math.max(nums[i], f[i - 1] + nums[i]);
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

}
