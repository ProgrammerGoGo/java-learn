package com.programmer.algorithm;

/**
 * @Author programmerGoGo
 * @Description
 */
public class MergeSort {

    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = (right - left) / 2 + left;
        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);
        // 合并两个排序好的子数组，用middle下标分割
        merge(arr, left, middle, right);
    }

    /**
     * 合并两个有序的子数组 left ~ middle 和 middle+1 ~ right
     *
     * @param arr
     * @param left
     * @param middle
     * @param right
     */
    private static void merge(int[] arr, int left, int middle, int right) {
        // 有序子数组1的开始下标
        int s1 = left;
        // 有序子数组2的开始下标
        int s2 = middle + 1;
        int size = right - left + 1;
        // 缓存合并后的两个数组
        int[] temp = new int[size];
        int i = 0;
        // 双指针遍历两个子数组，数值小的依次放到缓存temp中，知道其中一个子数组遍历完
        while (s1 <= middle && s2 <= right) {
            if (arr[s1] < arr[s2]) {
                temp[i++] = arr[s1++];
            } else {
                temp[i++] = arr[s2++];
            }
        }

        // 继续遍历上面未扫描完的数组
        while (s1 <= middle) {
            temp[i++] = arr[s1++];
        }
        while (s2 <= right) {
            temp[i++] = arr[s2++];
        }

        // 把排序好的缓存值放到目标数组
        for (int j = 0; j < size; j++) {
            arr[left + j] = temp[j];
        }
    }
}
