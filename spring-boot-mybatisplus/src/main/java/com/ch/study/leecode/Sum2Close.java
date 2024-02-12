package com.ch.study.leecode;

import java.util.Arrays;

public class Sum2Close {

    public static void main(String[] args) {
        int[] arr = new int[]{-1,2,1,-4};
        Sum2Close sum2Close = new Sum2Close();
        int result = sum2Close.threeSumClosest(arr, 1);
        System.out.println(result);
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = 100000;
        int offset = 100000;
        for (int i = 0; i < nums.length; i++) {
            if (i > 1 && nums[i] == nums[i - 1]) {
                continue;
            }
            int value = target - nums[i];
            int subResult = twoSumClose(nums, i + 1, nums.length - 1, value);
            offset = Math.min(offset, Math.abs(subResult+nums[i] - target));
            if (offset == Math.abs(subResult+nums[i] - target)) {
                result = subResult + nums[i];
            }
        }
        return result;
    }

    public int twoSumClose(int[] nums, int start, int end, int target) {
        int low = start;
        int high = end;
        int offset = 100000;
        int result = 100000;
        while (low < high) {
            int sum = nums[low] + nums[high];
            if (sum < target) {
                low++;
                offset = Math.min(Math.abs(target - sum), offset);
                if (offset == Math.abs(target - sum)) {
                    result = sum;
                }
            } else if (sum > target) {
                high--;
                offset = Math.min(Math.abs(target - sum), offset);
                if (offset == Math.abs(target - sum)) {
                    result = sum;
                }
            } else {
                return nums[low] + nums[high];
            }
        }
        return result;
    }
}
