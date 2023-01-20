package Test15;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SumOfNumber {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {//判断是否在hash表内
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);//映射到hash表，检索值为i（数组地址下标）
        }
        return new int[0];
    }

    @Test
    void test() {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 36, 134, 45}, 47)));
    }
}
