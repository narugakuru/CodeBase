/*
 * @lc app=leetcode.cn id=11 lang=java
 *
 * [11] 盛最多水的容器
 */

// @lc code=start
class Solution {
    public int maxArea(int[] height) {
        int high = height.length-1;
        int low = 0;

        int maxArea = 0;
        while (low < high){
            int tmp = (high-low)*Math.min(height[low],height[high]);
            
            if(tmp > maxArea){
                maxArea = tmp;
            }

            if(height[low]>height[high]){
                high--;
            }else{
                low++;
            }
        
        }

        return maxArea;
    }
}
// @lc code=end

