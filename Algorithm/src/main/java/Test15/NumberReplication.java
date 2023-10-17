package Test15;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

public class NumberReplication {

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            if (hashSet.add(num)) {
                return true;
            }
        }

        return false;
    }

    public boolean excellence(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }


    @Test
    void test() {
//        TimeMemory.start();
        System.out.println(Runtime.getRuntime().freeMemory());
        int[] nums = new int[]{11, 5, 5, 6};
        System.out.println(containsDuplicate(nums));
        System.out.println(Runtime.getRuntime().freeMemory());
//        TimeMemory.end();
    }


}
