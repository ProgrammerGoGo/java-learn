package com.programmer.algorithm;

/**
 * @Author programmerGoGo
 * @Description 快速排序
 */
public class QuickSort {

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        // 找到基准排好序后的下标
        int index = partition(arr, left, right);
        quickSort(arr, left, index);
        quickSort(arr, index + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        // 以最左侧的数为基准
        // index下标用于分割比基准小和比基准大的值
        int index = left + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[left]) {
                // 以index为分割线，把比基准小的数放在分割线左边，比基准大的数放到分割线右边
                Tool.swap(arr, i, index);
                // 把分割线下标右移，因为刚刚交换后，原index下标变为了比基准小的值
                index++;
            }
        }
        // 此时index下表左边都是比基准小的数，右边都是比基准大的数
        // 交换基准left和index左边的值
        Tool.swap(arr, index - 1, left);
        return index - 1;
    }
}
