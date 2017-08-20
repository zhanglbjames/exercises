package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/20.
 */

import java.util.ArrayList;

/**
 * 题目描述：
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
 * 如果有多对数字的和等于S，输出两个数的乘积最小的。
 *
 * 输出描述:
 * 对应每个测试案例，输出两个数，小的先输出。
 */
public class Exercise_38 {
    public ArrayList<Integer> findNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> list = new ArrayList<>();
        if (array == null || array.length == 0){
            return list;
        }

        int num1 = array[0];
        int num2 = array[array.length-1];


        int startIndex = 0;
        int endIndex = array.length-1;

        while (startIndex < endIndex){
            if (num1 + num2 > sum){
                num2 = array[--endIndex];
            }else if (num1 + num2 == sum){
                list.add(num1);
                list.add(num2);
                return list;
            }else
                num1 = array[ ++startIndex];
        }
        return list;
    }

    public static void main(String[] args) {
        Exercise_38 exercise_38 = new Exercise_38();

        int[] array = new int[]{1,2,3,7,11,15};
        int[] array1 = new int[]{1,2,4,7,11,15};


        ArrayList<Integer> result =exercise_38.findNumbersWithSum(array1,15);

        System.out.println(result.get(0) + " " + result.get(1));
    }
}
