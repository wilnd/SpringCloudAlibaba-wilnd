package com.ch.study.leecode;

/**
 * 1. 从后往前找到第一个升序的位置i
 * 2. 从i开始往后找到第一个比i大的位置j
 * 3. 交换i和j的位置
 * 4. 反转i后面的所有元素
 * 5. 如果没有找到i，说明是最大的排列，直接反转整个数组
 * 6. 如果找到了i，但是没有找到j，说明是最大的排列，直接反转i后面的所有元素
 * 7. 如果找到了i和j，交换i和j的位置，然后反转i后面的所有元素
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
