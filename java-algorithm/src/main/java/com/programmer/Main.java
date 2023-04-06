package com.programmer;

import com.programmer.algorithm.MergeSort;
import com.programmer.algorithm.Tool;

public class Main {

    public static void main(String[] args) {
        int[] arr = {2, 3, 8, 5, 6, 1, 0};
        System.out.println("排序前：");
        Tool.printArr(arr);

        MergeSort.mergeSort(arr);
//        QuickSort.quickSort(arr);
//        HeapSort.heapSort(arr);
//        InsertionSort.insertionSort(arr);
//        SelectionSort.selectionSort(arr);
//        BubbleSort.bubbleSort(arr);

        System.out.println("排序后：");
        Tool.printArr(arr);

    }
}
