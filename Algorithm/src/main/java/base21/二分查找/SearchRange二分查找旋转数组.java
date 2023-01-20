package base21.二分查找;

import org.junit.jupiter.api.Test;

//区间两端都闭while用等号，mid必须加减1
public class SearchRange二分查找旋转数组 {
//手撕最优解
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        if (right == -1) {
            return -1;
        }
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;//终止
            }
//            !!!=号不能漏
            if (nums[mid] <= nums[right]) {//先判断数组的分布状态
                if (nums[mid] < target && target <= nums[right]) {//再看target值在【mid，right】区间内
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {//中间大于左端
                if (nums[mid] > target && target >= nums[left]) {//target值在【left，mid】区间内
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return -1;
    }

    public int searchGF(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
/*      //这一步可以直接省略
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }*/
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
    @Test
    void test() {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println(search(nums, 0));
    }
}
