package com.ch.study.leecode;

/**
 * 1. 从后往前找到第一个升序的位置i
 * 2. 从i开始往后找到第一个比i大的位置k
 * 3. 交换i和k的位置
 * 4. 反转i后面的所有元素
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        //find a[i]<a[j]
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        //表示不是最大排列
        if (i >= 0){
            // find a[i] < a[k]
            int k = nums.length - 1;
            while(k >= 0 && nums[i] >= nums[k]){
                k--;
            }
            //swap a[i] and a[k]
            swap(nums, i, k);
        }

        //reverse a[i+1] to a[n]
        //i 最小为-1 为-1时表示整个数组是降序的，直接反转整个数组
        reverse(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int i, int j){
        while(i < j){
            swap(nums, i, j);
            i++;
            j--;
        }
    }

}
