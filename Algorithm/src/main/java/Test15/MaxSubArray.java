package Test15;

import org.junit.jupiter.api.Test;

/*
思路和算法

因此我们只需要求出每个位置的 f(i)，然后返回 ff 数组中的最大值即可。那么我们如何求 f(i) 呢？
我们可以考虑 \textit{nums}[i]nums[i] 单独成为一段还是加入 f(i-1)f(i−1) 对应的那一段，
这取决于 \textit{nums}[i]nums[i] 和 f(i-1) + \textit{nums}[i]f(i−1)+nums[i] 的大小，
我们希望获得一个比较大的，于是可以写出这样的动态规划转移方程：
f(i) = \max \{ f(i-1) + \textit{nums}[i], \textit{nums}[i] \}
f(i)=max{f(i−1)+nums[i],nums[i]}

不难给出一个时间复杂度 O(n)O(n)、空间复杂度 O(n)O(n) 的实现，即用一个 ff 数组来保存 f(i) 的值，
用一个循环求出所有 f(i)。考虑到 f(i) 只和 f(i-1)f(i−1) 相关，
于是我们可以只用一个变量 \textit{pre}pre 来维护对于当前 f(i) 的 f(i-1)f(i−1) 的值是多少，
从而让空间复杂度降低到 O(1)O(1)，这有点类似「滚动数组」的思想。

*/

public class MaxSubArray {

    int[] nums = new int[]{-2, 1, -3, 4};

    public int maxSubArray1(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);//判断标准
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;//最大字串和
    }

    @Test
    void test() {
        System.out.println(maxSubArray1(nums));
    }

}
