package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/13.
 */

import java.util.Arrays;

/**
 * <p>Description</p>
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class Exercise_13 {
    public static void reOrderArray(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        doPartition(array);
    }
    private static void doPartitionII(int[] array) {
        int[] oSwapIndex = new int[(array.length+1) >>> 1 ];
        int[] jSwapIndex = new int[(array.length+1) >>> 1 ];
        int[] oSwap = new int[(array.length+1) >>> 1 ];
        int swapCount = 0;

        int startIndex = 0, endIndex = array.length - 1;
        for (;endIndex > startIndex; ) {
            if ((array[endIndex] & 1) == 1) {
                if ((array[startIndex] & 1) == 0) {
                    // 统计每一次交换的位置都是逆序的位置，但是不进行交换
                    oSwapIndex[swapCount] = endIndex;
                    jSwapIndex[swapCount] = startIndex;
                    ++ swapCount;
                } else {
                    ++startIndex;
                }
            } else {
                --endIndex;
            }
        }
        // 判断交换后的奇偶边界
        if ((array[startIndex] & 1) == 1){

        }
    }
    private static void adjustArray(int[] array, int[] oSwapIndex, int[] jSwapIndex, int jIndex){

    }

    private static void doPartition(int[] array) {

        for (int startIndex = 0, endIndex = array.length - 1; endIndex > startIndex; ) {
            if ((array[endIndex] & 1) == 1) {
                if ((array[startIndex] & 1) == 0) {
                    // 交换
                    int swap = array[startIndex];
                    array[startIndex] = array[endIndex];
                    array[endIndex] = swap;
                } else {
                    ++startIndex;
                }
            } else {
                --endIndex;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 4, 5, 6, 8, 3, 8, 9, 99, 333, 44, 0};
        reOrderArray(array);
        System.out.println(Arrays.toString(array));
    }
}
