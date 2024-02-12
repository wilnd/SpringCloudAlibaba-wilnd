package com.ch.study.leecode;

public class ContainerMostWater {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int area = maxArea(arr);
        System.out.println(area);
    }

    /**
     * 最多盛水的容器，使用双指针法
     * 思路：双指针法，两个指针分别指向数组的头和尾，然后计算两个指针之间的面积，然后移动较小的那个指针
     * @param height 数组
     * @return 最大面积
     */
    public static int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

}
