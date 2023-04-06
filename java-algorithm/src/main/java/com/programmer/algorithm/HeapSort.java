package com.programmer.algorithm;

/**
 * @Author programmerGoGo
 * @Description 堆排序
 * 大顶堆：每个节点的值都大于或者等于它的左右子节点的值。
 * 小顶堆：每个节点的值都小于或者等于它的左右子节点的值。
 * <p>
 * 堆排序的基本思想是：
 * 1、将带排序的序列构造成一个大顶堆，根据大顶堆的性质，当前堆的根节点（堆顶）就是序列中最大的元素；
 * 2、将堆顶元素和最后一个元素交换，然后将剩下的节点重新构造成一个大顶堆；
 * 3、重复步骤2，如此反复，从第一次构建大顶堆开始，每一次构建，我们都能获得一个序列的最大值，然后把它放到大顶堆的尾部。最后，就得到一个有序的序列了。
 */
public class HeapSort {

    public static void heapSort(int[] arr) {
        int len = arr.length;

        // 构建大顶堆
        buildMaxHeap(arr, len);

        for (int i = len - 1; i > 0; i--) {
            // 交换堆顶和堆尾元素
            Tool.swap(arr, 0, i);

            // 此时数组最后元素已经是排好序的了，后面只对剩下的len-1个元素排序
            len--;

            // 由于是在大顶堆的技术上交换堆顶和堆尾元素，除堆顶外，其他非叶子节点已经满足大顶堆，所以只需要对交换后的根节点重新构建大顶堆
            heapify(arr, 0, len);
        }
    }

    private static void buildMaxHeap(int[] arr, int length) {
        for (int i = length / 2 - 1; i >= 0; i--) {
            //从最后一个根节点开始构建大顶堆
            heapify(arr, i, length);
        }
    }

    // 核心方法。对i位置的节点构建堆，如果构建过程中父节点下移成为了子节点，需要再次对下移的父节点再次构建堆
    private static void heapify(int[] arr, int i, int len) {
        // 先根据堆性质，找出i左右节点的索引
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int maxIndex = i;
        if (left < len && arr[left] > arr[maxIndex]) {
            // 如果有左节点，并且左节点的值更大，更新最大值的索引
            maxIndex = left;
        }
        if (right < len && arr[right] > arr[maxIndex]) {
            // 如果有右节点，并且右节点的值更大，更新最大值的索引
            maxIndex = right;
        }
        if (maxIndex != i) {
            // 如果最大值不是当前非叶子节点的值，那么就把当前节点和最大值的子节点值互换
            Tool.swap(arr, maxIndex, i);
            // 因为互换之后，子节点的值变了，如果该子节点也有自己的子节点，仍需要再次调整，直到 max==i 即当前i节点已经是堆了。
            heapify(arr, maxIndex, len);
        }
    }
}
