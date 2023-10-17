package base21.二分查找;

import org.junit.jupiter.api.Test;
/*
用二分法查找，需要始终将目标值（这里是最小值）套住，并不断收缩左边界或右边界。
左、中、右三个位置的值相比较，有以下几种情况：
左值 < 中值, 中值 < 右值 ：没有旋转，最小值在最左边，可以收缩右边界
        右
     中
 左
左值 > 中值, 中值 < 右值 ：有旋转，最小值在左半边，可以收缩右边界
 左
         右
     中
左值 < 中值, 中值 > 右值 ：有旋转，最小值在右半边，可以收缩左边界
     中
 左
         右
左值 > 中值, 中值 > 右值 ：单调递减，不可能出现
 左
    中
        右

分析前面三种可能的情况，会发现情况1、2是一类，情况3是另一类。
如果中值 < 右值，则最小值在左半边，可以收缩右边界。
如果中值 > 右值，则最小值在右半边，可以收缩左边界。
通过比较中值与右值，可以确定最小值的位置范围，从而决定边界收缩的方向。

而情况1与情况3都是左值 < 中值，但是最小值位置范围却不同，这说明，如果只比较左值与中值，不能确定最小值的位置范围。
所以我们需要通过比较中值与右值来确定最小值的位置范围，进而确定边界收缩的方向。
接着分析解法里的一些问题：

首先是while循环里的细节问题。
这里的循环不变式是left < right, 并且要保证左闭右开区间里面始终套住最小值。
中间位置的计算：mid = left + (right - left) / 2
这里整数除法是向下取整的地板除，mid更靠近left，

再结合while循环的条件left < right，
可以知道left <= mid，mid < right，
即在while循环内，mid始终小于right。

因此在while循环内，nums[mid]要么大于要么小于nums[right]，不会等于。
这样else {right = mid;}这句判断可以改为更精确的
else if (nums[mid] < nums[right]) {right = mid;}。

再分析一下while循环退出的条件。
如果输入数组只有一个数，左右边界位置重合，left == right，不会进入while循环，直接输出。
如果输入数组多于一个数，循环到最后，会只剩两个数，nums[left] == nums[mid]，以及nums[right]，这里的位置left == mid == right - 1。
如果nums[left] == nums[mid] > nums[right]，则左边大、右边小，
需要执行left = mid + 1，使得left == right，左右边界位置重合，循环结束，nums[left]与nums[right]都保存了最小值。
如果nums[left] == nums[mid] < nums[right]，则左边小、右边大，
会执行right = mid，使得left == right，左右边界位置重合，循环结束，nums[left]、nums[mid]、nums[right]都保存了最小值。

*/
public class findMin二分查找旋转数组的Min值 {

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;                /* 左闭右闭区间，如果用右开区间则不方便判断右值 */
        while (left < right) {                      /* 循环不变式，如果left == right，则循环结束 */
            int mid = left + (right - left) / 2;    /* 地板除，mid更靠近left */
            if (nums[mid] > nums[right]) {          /* 中值 > 右值，最小值在右半边，收缩左边界 */
                left = mid + 1;                     /* 因为中值 > 右值，中值肯定不是最小值，左边界可以跨过mid */
            } else if (nums[mid] < nums[right]) {   /* 明确中值 < 右值，最小值在左半边，收缩右边界 */
                right = mid;                        /* 因为中值 < 右值，中值也可能是最小值，右边界只能取到mid处 */
            }
        }
        return nums[left];    /* 循环结束，left == right，最小值输出nums[left]或nums[right]均可 */
    }


    @Test
    void t() {

    }

}
