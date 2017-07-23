package course.lesson2;

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
public class CountSort {
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int min = arr[0];
        int max = arr[0];
        // 找出最大最小值
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(arr[i], min);
            max = Math.max(arr[i], max);
        }
        // 创建最大值和最小值之间容量的数组
        int[] countArr = new int[max - min + 1];

        // 桶内数量计数
        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i] - min]++;
        }

        // 倒桶，将桶中的元素倒入原数组当中
        int index = 0;
        for (int i = 0; i < countArr.length; i++) {
            while (countArr[i]-- > 0) {
                arr[index++] = i + min;
            }
        }
    }

    public static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static int[] generateArray(int len, int range) {
        if (len < 1) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * range);
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
        int range = 10;
        int testTimes = 50000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateArray(len, range);
            countSort(arr);
            if (!isSorted(arr)) {
                System.out.println("Wrong Case:");
                printArray(arr);
                break;
            }
        }

        int len2 = 13;
        int range2 = 10;
        int testTimes2 = 50000;
        for (int i = 0; i < testTimes2; i++) {
            int[] arr = generateArray(len2, range2);
            countSort(arr);
            if (!isSorted(arr)) {
                System.out.println("Wrong Case:");
                printArray(arr);
                break;
            }
        }
    }
}
