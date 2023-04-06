package com.programmer.algorithm;

public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            boolean flag = true;
            for (int j = i + 1; j < len; j++) {
                if (arr[i] > arr[j]) {
                    Tool.swap(arr, i, j);
                    flag = false;
                }
            }
            if (flag) {
                // 如果本次内层循环没有交换元素，说明数组中的数据已经是有序的了，直接跳出循环。
                break;
            }
        }
    }

}
