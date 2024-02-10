package com.ch.study.algorithm;


public class BucketSort {
    public static void main(String[] args) {
        int[] array = new int[]{2, 34, 1, 2, 4, 6, 21};
        bucketSort(array);
        for (int i : array) {
            System.out.println(i);
        }
    }

    public static void bucketSort(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        int bucketNum = (max - min) / arr.length + 1;
        int[][] bucket = new int[bucketNum][arr.length];
        int[] indexArr = new int[bucketNum];
        for (int value : arr) {
            int num = (value - min) / arr.length;
            bucket[num][indexArr[num]++] = value;
        }
        int k = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (indexArr[i] == 0) {
                continue;
            }
            QuickSort.quickSort(bucket[i], 0, indexArr[i] - 1);
            for (int j = 0; j < indexArr[i]; j++) {
                arr[k++] = bucket[i][j];
            }
        }
    }
}
