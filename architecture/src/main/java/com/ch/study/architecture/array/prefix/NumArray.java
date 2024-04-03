package com.ch.study.architecture.array.prefix;

public class NumArray {
    int[] tempArray;

    public NumArray(int[] nums) {
        tempArray = new int[nums.length + 1];
        tempArray[0] = 0;
        int addResult = 0;
        for (int i = 0; i < nums.length; i++) {
            tempArray[i + 1] = tempArray[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return tempArray[right + 1] - tempArray[left];
    }
}
