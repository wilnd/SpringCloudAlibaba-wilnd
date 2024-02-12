package com.ch.study.leecode;

import java.util.*;

public class Sum3 {
    public static void main(String[] args) {
        int[] arr = new int[]{-1, 0, 1, 2, -1, -4};
        Sum3 sum3 = new Sum3();
        List<List<Integer>> result = sum3.threeSum(arr);
        System.out.println(result);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        //1. 先排序
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        //2. 遍历数组
        for (int i = 0; i < nums.length; i++) {
            //3. 如果当前元素大于0，那么后面的元素肯定大于0，所以不可能有三个数的和等于0
            if (nums[i] > 0) {
                break;
            }
            //4. 如果当前元素和前一个元素相等，那么跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //5. 使用双指针法
            List<List<Integer>> lists = twoSum(nums, i + 1, nums.length - 1, -nums[i]);
            for (List<Integer> list : lists) {
                list.add(nums[i]);
                result.add(list);
            }
        }
        return result;
    }

    public List<List<Integer>> twoSum(int[] array, int start, int end, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int low = start;
        int high = end;
        while (low < high) {
            int sum = array[low] + array[high];
            int left = array[low];
            int right = array[high];
            if (sum < target) {
                while (low < high && array[low] == left) {
                    low++;
                }
            } else if (sum > target) {
                while (low < high && array[high] == right) {
                    high--;
                }
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(left);
                list.add(right);
                result.add(list);
                while (low < high && array[low] == left) {
                    low++;
                }
                while (low < high && array[high] == right) {
                    high--;
                }
            }
        }
        return result;
    }


}
