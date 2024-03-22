package com.ch.study.architecture.array.doublepoint;

public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        int slow = 0;
        int fast = 0;
        int temp;
        while (fast < nums.length) {
            if (nums[fast] == val) {
                while (fast < nums.length && nums[fast] == val) {
                    fast++;
                }
                if (fast >= nums.length) {
                    return slow;
                }
                temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow] = temp;
            } else {
                fast++;
            }
            slow++;
        }
        return slow;
    }
}
