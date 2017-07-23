package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/23.
 */

/**
 * <p>Description</p>
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
 * 如果不存在则输出0。
 */
public class Exercise_28 {
    // 二分partion
    private static int doPartion(int[] array, int start, int end) {
        // 只有一个元素
        if (end == start) {
            return array[start];
        }
        // 首尾交换
        swap(array, start, end);

        // 小于等于空间
        int lessEqualSpace = start;

        // 迭代
        for (int i = start, j = end - 1; i <= j; ) {
            if (array[i] <= array[end]) {
                lessEqualSpace++;
                i++;
            } else if (array[j] <= array[end]) {
                swap(array, i, j);
                lessEqualSpace++;
                i++;
                j--;
            } else {
                j--;
            }
        }
        // 首尾再交换
        swap(array, lessEqualSpace, end);


        // 奇数
        if ((array.length & 0x01) == 1) {
            int middle = array.length / 2 + 1;

            // 排序的元素在中间位置则返回
            if (lessEqualSpace + 1 == middle) {
                return array[lessEqualSpace];
            }
            // 位于左半边则继续向右
            else if (lessEqualSpace +1  < middle) {
                return doPartion(array, lessEqualSpace + 1 >= end ? end : lessEqualSpace + 1, end);
            }
            // 位于右边则继续向左
            else {
                return doPartion(array, start, lessEqualSpace - 1 <= start ? start : lessEqualSpace-1);
            }
        }
        else {
            int middle = array.length /2;
            // 排序的元素在中间位置则返回
            if (lessEqualSpace + 1 == middle || lessEqualSpace +1 == middle +1) {
                return array[lessEqualSpace];
            }
            // 位于左半边则继续向右
            else if (lessEqualSpace +1  < middle) {
                return doPartion(array, lessEqualSpace + 1 >= end ? end : lessEqualSpace + 1, end);
            }
            // 位于右边则继续向左
            else {
                return doPartion(array, start, lessEqualSpace - 1 <= start ? start : lessEqualSpace-1);
            }

        }

    }

    private static void swap(int[] array, int index1, int index2) {
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;

    }

    public static int moreThanHalfNum(int[] array) {
        if (array == null || array.length <= 0)
            return 0;

        int result = doPartion(array, 0, array.length - 1);

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == result){
                count ++;
            }
        }

        return count > array.length /2 ? result : 0;

    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 2, 2, 2, 5,8,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,66,6,6,6,6,6,3,5,6,7,8,5,3,3, 4, 2};
        int[] array1 = {1,2,3,2,4,2,5,2,3};
        System.out.println(moreThanHalfNum(array1));

    }
}
