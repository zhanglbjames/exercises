package course.lesson2;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/21.
 */
/**
 * <p>Description</p>
 * 已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离可以不超过k，
 * 并且k相对于数组来说比较小。请选择一个合适的排序算法针对这个数据进行排序。
 * 给定一个int数组A，同时给定A的大小n和题意中的k，请返回排序后的数组。

 测试样例：
 [2,1,4,3,6,5,8,7,10,9],10,2
 返回：[1,2,3,4,5,6,7,8,9,10]
 */

public class ScaleSort {

    public  int[] sortElement(int[] A, int n, int k) {
        if (A == null || A.length == 0 || n < k) {
            return A;
        }
        int[] heap = getKHeap(A, k);
        for (int i = k; i < n; i++) {
            A[i - k] = heap[0];
            heap[0] = A[i];
            heapify(heap, 0, k);
        }
        for (int i = n - k; i < n; i++) {
            A[i] = heap[0];
            swap(heap, 0, k - 1);
            heapify(heap, 0, --k);
        }
        return A;
    }

    public  int[] getKHeap(int[] A, int k) {
        int[] heap = new int[k];
        for (int i = 0; i < k; i++) {
            heapInsert(heap, A[i], i);
        }
        return heap;
    }

    public  void heapInsert(int[] heap, int value, int index) {
        heap[index] = value;
        while (index != 0) {
            int parent = (index - 1) / 2;
            if (heap[parent] > heap[index]) {
                swap(heap, parent, index);
                index = parent;
            } else {
                break;
            }
        }
    }

    public  void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int smallest = index;
        while (left < heapSize) {
            if (arr[left] < arr[index]) {
                smallest = left;
            }
            if (right < heapSize && arr[right] < arr[smallest]) {
                smallest = right;
            }
            if (smallest != index) {
                swap(arr, smallest, index);
            } else {
                break;
            }
            index = smallest;
            left = index * 2 + 1;
            right = index * 2 + 2;
        }
    }

    public  void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }






}