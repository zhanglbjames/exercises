package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/27.
 */

import java.util.ArrayList;

/**
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 */
public class Exercise_32 {

    private Integer[] getBit10(int n){

        ArrayList<Integer> arrayList = new ArrayList<>();

        int base = 1;

        while(n >= base){
            arrayList.add((n % (10* base)) / base);
            base *= 10;
        }
        return arrayList.toArray(new Integer[0]);
    }

    // 比较n1和n2的大小，如果n1大于n2则返回true，否则返回false
    public boolean comparaTwo(Integer n1, Integer n2) {
        // 参数不能小于等于0
        if (n1.intValue() <= 0 || n2.intValue() <= 0){
            throw new IllegalArgumentException("int element is below zero");
        }
        // 如果相等则立即返回false
        if (n1.equals(n2)){
            return false;
        }

        // 进行每一位上的比较
        Integer[] n1Bits = getBit10(n1);
        Integer[] n2Bits = getBit10(n2);

        Integer[] large = n2Bits;
        Integer[] small = n1Bits;

        if (n1Bits.length >= n2Bits.length){
            large = n1Bits;
            small = n2Bits;
        }

        for (int i = large.length-1,j = small.length-1; i >= 0;--i){
            if (j < 0){
                j = small.length-1;
            }

            if (large[i] > small[j]){
                return large == n1Bits;
            }else if (large[i] < small[j]){
                return small == n1Bits;
            }else {
                -- i;
                -- j;
            }
        }
        return false;
    }
    private void doSort(int[] numbers){
        partition(numbers,0,numbers.length -1);

    }
    private void partition(int[] numbers, int start, int end){
        if (start >= end){
            return;
        }

        int base = start;
        exchange(numbers,base,end);

        int index = start -1;
        for (int i = start; i < end; i++) {
            if (!comparaTwo(numbers[i],numbers[end])){
                exchange(numbers,++ index,i);
            }
        }
        exchange(numbers,index +1,end);
        partition(numbers,start,index);
        partition(numbers,index + 2, end);
    }
    private void exchange(int[] numbers, int index1, int index2){
        int tmp = numbers[index1];
        numbers[index1] = numbers[index2];
        numbers[index2] = tmp;
    }

    public String printMinNumber(int[] numbers){
        if (numbers == null || numbers.length == 0){
            return "";
        }
        doSort(numbers);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            Integer intI = new Integer(numbers[i]);
            stringBuilder.append(intI.toString());
        }

        return stringBuilder.toString();

    }

    public static void main(String[] args) {
        Exercise_32 test = new Exercise_32();

        int[] numbers = {3,32,321,423,212};
        System.out.println(test.printMinNumber(numbers));

    }
}
