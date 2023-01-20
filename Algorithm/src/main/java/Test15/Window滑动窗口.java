package Test15;/*
* 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7]
解释:

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

作者：Krahets
链接：https://leetcode-cn.com/leetbook/read/illustration-of-algorithm/58o46i/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/


import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Window滑动窗口 {

    @Test
    void test() {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] res = maxSlidingWindow(nums, 3);
        System.out.println(Arrays.toString(res));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int max = nums[0];
        int[] maxNums = new int[nums.length - k + 1];

        for (int i = 0; i < k; i++) {
            if (max < nums[i]) max = nums[i];
        }
        maxNums[0] = max;
        for (int i = k, j = 1; i < nums.length; i++, j++) {
            if (max < nums[i]) {
                max = nums[i];
            }
            maxNums[j] = max;

        }

        return maxNums;
    }


}
