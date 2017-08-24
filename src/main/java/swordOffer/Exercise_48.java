package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/23.
 */

import java.util.Arrays;

/**
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
 *
 * 不能使用除法。
 */
public class Exercise_48 {
    public int[] multiply(int[] A) {

        if (A == null || A.length == 0) {
            return new int[0];
        }

        int[] b =new int[A.length];


        for (int i = 0; i < b.length; i++) {
            if (i == 0) {
                b[i] = 1;
            }else {
                b[i] = b[i -1]* A[i -1];
            }
        }

        int d = 1;
        for (int i = b.length -1; i >= 0 ; i--) {
            if (i != b.length -1){
                b[i] *= (d * A[i + 1]);
                d = d * A[i +1];
            }
        }
        return b;
    }

    public static void main(String[] args) {
        Exercise_48 exercise_48 = new Exercise_48();
         int[] a = {1,2,3,4,5,6,7,8};
         int[] b = exercise_48.multiply(a);
         System.out.println(Arrays.toString(b));
    }
}
