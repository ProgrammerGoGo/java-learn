package com.programmer.algorithm;

/**
 * @Author programmerGoGo
 * @Description 插入排序
 *  从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。
 */
public class InsertionSort {

    public static void insertionSort(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            int temp = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > temp) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            if (i != j){
                arr[j + 1] = temp;
            }
        }
    }
}
