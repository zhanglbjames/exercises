package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/20.
 */

import java.util.ArrayList;

/**
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字
 */

/**
 * 异或的作用就是无进位的加法运算，凡是题目中出现 2次 的条件，都可以尝试转化为异或来计算
 * 同时抹去 2次出现的影响
 *
 * 即 两个相同的数异或 为 0
 * 三个数，如果有两个数相同，那么这三个数相互异或的结果就是那个不相同的数
 */
public class Exercise_36 {
    //num1,num2分别为长度为1的数组。传出参数
    //将num1[0],num2[0]设置为返回结果
    public void findNumberAppearOnce(int[] array, int[] num1, int[] num2) {

        if (array == null || array.length < 1) {
            return;
        }

        int oR = array[0];

        for (int i = 1; i < array.length; i++) {
            oR ^= array[i];
        }

        int bit1 = findFirstBit1(oR);

        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();



        for (int i = 0; i < array.length; i++) {
            if ((array[i] & (1 << bit1)) == 0) {
                arr1.add(array[i]);
            }else
                arr2.add(array[i]);
        }

        int oR1 = arr1.get(0);
        for (int i = 1; i < arr1.size(); i++) {
            oR1 ^= arr1.get(i);
        }

        int oR2 = arr2.get(0);
        for (int i = 1; i < arr2.size(); i++) {
            oR2 ^= arr2.get(i);
        }
        num1[0] = oR1;
        num2[0] = oR2;
    }

    private int findFirstBit1(int num) {
        for (int i = 0; i < 32; i++) {
            if ((num & (1 << i)) != 0) {
                return i;
            }
        }
        throw new IllegalArgumentException("can not find the first bit that value of 1");
    }

    public static void main(String[] args) {
        Exercise_36 exercise_36 = new Exercise_36();
        int[] arr = new int[]{2,4,3,6,3,2,5,5};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        exercise_36.findNumberAppearOnce(arr,num1,num2);

        System.out.println(num1[0] + " " + num2[0]);
    }
}
