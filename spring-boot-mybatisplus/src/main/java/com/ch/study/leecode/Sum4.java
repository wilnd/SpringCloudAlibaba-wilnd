package com.ch.study.leecode;

import java.util.Arrays;
import java.util.List;

public class Sum4 {

    public static void main(String[] args) {
        int[] arr = new int[]{1000000000, 1000000000, 1000000000, 1000000000};
        Sum4 sum4 = new Sum4();
        List<List<Integer>> result = sum4.fourSum(arr, -294967296);
        System.out.println(result);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new java.util.ArrayList<>();
        for (int i = 0; i < nums.length-3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> subResult = threeSum(nums, i + 1, nums.length - 1, target - nums[i]);
            for (List<Integer> list : subResult) {
                list.add(nums[i]);
                result.add(list);
            }
        }
        return result;
    }

    public List<List<Integer>> threeSum(int[] nums, int start, int end, int target) {
        List<List<Integer>> result = new java.util.ArrayList<>();
        for (int i = start; i <= end-2; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> subResult = twoSum(nums, i + 1, end, target - nums[i]);
            for (List<Integer> list : subResult) {
                list.add(nums[i]);
                result.add(list);
            }
        }
        return result;
    }

    public List<List<Integer>> twoSum(int[] nums, int start, int end, int target) {
        List<List<Integer>> result = new java.util.ArrayList<>();
        int low = start;
        int high = end;
        while (low < high) {
            int sum = nums[low] + nums[high];
            int left = nums[low];
            int right = nums[high];
            if (sum < target) {
                while (low < high && nums[low] == left) {
                    low++;
                }
            } else if (sum > target) {
                while (low < high && nums[high] == right) {
                    high--;
                }
            } else {
                List<Integer> list = new java.util.ArrayList<>();
                list.add(left);
                list.add(right);
                result.add(list);
                while (low < high && nums[low] == left) {
                    low++;
                }
                while (low < high && nums[high] == right) {
                    high--;
                }
            }
        }
        return result;
    }
}
