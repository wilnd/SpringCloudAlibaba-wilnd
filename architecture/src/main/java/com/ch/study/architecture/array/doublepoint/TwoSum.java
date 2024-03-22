package com.ch.study.architecture.array.doublepoint;

public class TwoSum {

    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        int[] resultArray = new int[2];
        while (i < j) {
            if (numbers[i] + numbers[j] < target) {
                i++;
            } else if (numbers[i] + numbers[j] > target) {
                j--;
            } else {
                resultArray[0] = i + 1;
                resultArray[1] = j + 1;
                return resultArray;
            }
        }
        return resultArray;
    }

}
