package com.ch.study.architecture.array.doublepoint;

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int slow = 0;
        int fast = 0;
        int temp;
        while (fast < nums.length) {
            if (nums[fast] == 0) {
                while (fast < nums.length && nums[fast] == 0) {
                    fast++;
                }
                if (fast > nums.length - 1) {
                    return;
                }
                temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow] = temp;
            } else {
                fast++;
            }
            slow++;
        }
    }
}
