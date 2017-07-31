package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/30.
 */

/**
 * <p>Description</p>
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。
 * 即输出P%1000000007
 * <p>
 * 输入描述:
 * 题目保证输入的数组中没有的相同的数字
 * 数据范围：
 * 对于%50的数据,size<=10^4
 * 对于%75的数据,size<=10^5
 * 对于%100的数据,size<=2*10^5
 * <p>
 * 示例1
 * <p>
 * 输入
 * 1,2,3,4,5,6,7,0
 * <p>
 * 输出
 * 7
 */
public class Exercise_35 {

    // 利用归并的思想来排序
    public int inversePairs(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int[] tmp = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            tmp[i] = array[i];
        }
        return doMergeInverse(array, tmp, 0, array.length - 1);
    }


    private int doMergeInverse(int[] array, int[] tmp, int start, int end) {
        if (end - start <= 0) {
            return 0;
        }
        int middle = start + (end - start) / 2;
        int leftCount = doMergeInverse(array, tmp, start, middle);
        int rightCount = doMergeInverse(array, tmp, middle + 1, end);

        int leftTailIndex = middle;
        int rightTailIndex = end;
        int tmpTailIndex = end;
        int currentCount = 0;

        while (true) {
            // 都有元素
            if (leftTailIndex >= start && rightTailIndex > middle) {
                if (array[leftTailIndex] > array[rightTailIndex]) {
                    tmp[tmpTailIndex --] = array[leftTailIndex --];
                    // 增加右边剩余的元素数量，包括当前元素
                    currentCount += (rightTailIndex - middle);
                    if (currentCount > 1000000007){
                        currentCount = currentCount % 1000000007;
                    }


                } else {
                    tmp[tmpTailIndex --] = array[rightTailIndex --];
                }
            }
            // 只有右
            else if (rightTailIndex > middle) {
                while (rightTailIndex > middle){
                    tmp[tmpTailIndex --] = array[rightTailIndex --];
                }
                break;

            }
            // 只有左
            else if (leftTailIndex >= start) {
                while(leftTailIndex >= start){
                    tmp[tmpTailIndex --] = array[leftTailIndex --];
                }
                break;

            }
            // 都没有
            else {
                break;
            }
        }
        // 拷贝数组
        for (int i = start; i <= end; i++) {
            array[i] = tmp[i];
        }
        return (currentCount + rightCount + leftCount)%1000000007;
    }
    public static void main(String[] args){
        Exercise_35 test = new Exercise_35();
        int[] array = {7,5,6,4};
        System.out.println(test.inversePairs(array));
    }
}
