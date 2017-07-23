package course.lesson2;

import java.util.ArrayList;
import java.util.LinkedList;
/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/21.
 */

/**
 * <p>Description</p>
 * 对于一个int数组，请编写一个计数排序算法，对数组元素排序。

 给定一个int数组A及数组的大小n，请返回排序后的数组。

 测试样例：
 [1,2,3,5,2,3],6
 [1,2,2,3,3,5]
 */
public class RadixSort {
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 统计负数元素的数量
        int negNum = 0;
        for (int i = 0; i < arr.length; i++) {
            negNum += arr[i] < 0 ? 1 : 0;
        }
        // 创建负数和整数的数组
        int[] negArr = new int[negNum];
        int[] posArr = new int[arr.length - negNum];

        // 将原数组元素放入正负数桶中
        int negi = 0;
        int posi = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                negArr[negi++] = -arr[i];
            } else {
                posArr[posi++] = arr[i];
            }
        }
        // 将正负数进行分开排序
        radixSortForPositive(negArr);
        radixSortForPositive(posArr);

        // 将排序的负数数组，按照逆序取负号添加到原数组当中
        int index = 0;
        for (int i = negArr.length - 1; i >= 0; i--) {
            arr[index++] = -negArr[i];
        }
        // 将排序的正数数组，添加到原数组当中。
        for (int i = 0; i < posArr.length; i++) {
            arr[index++] = posArr[i];
        }
    }


    public static void radixSortForPositive(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 创建0-9的两个桶
        ArrayList<LinkedList<Integer>> qArr1 = new ArrayList<>();
        ArrayList<LinkedList<Integer>> qArr2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            qArr1.add(new LinkedList<Integer>());
            qArr2.add(new LinkedList<Integer>());
        }
        // 将所有元素依次倒入桶1中（个位）
        for (int i = 0; i < arr.length; i++) {
            qArr1.get(arr[i] % 10).offer(arr[i]);
        }

        // 用于取每一位的进制数
        long base = 10;

        // 终止条件为base达到整数的最大值，其实也可以遍历数组找出最大值（正负分开）然后缩小终止条件。
        while (base <= Integer.MAX_VALUE) {
            // 将桶1的元素依次倒入桶2中
            for (int i = 0; i < 10; i++) {
                LinkedList<Integer> queue = qArr1.get(i);
                while (!queue.isEmpty()) {
                    int value = queue.poll();
                    qArr2.get((int) (value / base) % 10).offer(value);
                }
            }
            // 转换桶
            ArrayList<LinkedList<Integer>> tmp = qArr1;
            qArr1 = qArr2;
            qArr2 = tmp;
            // 更新base
            base *= 10;
        }

        // 将排序的数组返回
        int index = 0;
        for (int i = 0; i < 10; i++) {
            LinkedList<Integer> queue = qArr1.get(i);
            while (!queue.isEmpty()) {
                arr[index++] = queue.poll();
            }
        }
    }

    public static int[] generateArray(int len, int range) {
        if (len < 1) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * range) - 500000000;
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(int[] arr) {
        if (arr == null || arr.length < 2) {
            return true;
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int len = 10;
        int range = Integer.MAX_VALUE;
        int testTimes = 50000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateArray(len, range);
            radixSort(arr);
            if (!isSorted(arr)) {
                System.out.println("Wrong Case:");
                printArray(arr);
                break;
            }
        }
    }
}
