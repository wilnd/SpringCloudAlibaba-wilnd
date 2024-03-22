package com.ch.study.architecture.array.doublepoint;

public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int slow = 0;
        int fast = 1;
        int lastValue = nums[0];
        while (fast < nums.length) {
            if (nums[fast] == lastValue) {
                fast++;
            } else {
                lastValue = nums[fast];
                nums[slow + 1] = nums[fast];
                fast++;
                slow++;
            }
        }
        return slow + 1;
    }


}
