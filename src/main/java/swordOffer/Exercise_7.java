package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/12.
 */

/**
 * <p>Description</p>
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。n<=39
 */
public class Exercise_7 {
    public static int Fibonacci(int n) {
        int[] result = {0, 1};
        if (n < 2) {
            return result[n];
        }
        int fibNMinusOne = 1;
        int fibNMinusTwo = 0;
        int fibN = 0;
        for (int i= 2;i<=n;++i){
            fibN = fibNMinusOne + fibNMinusTwo;
            fibNMinusTwo = fibNMinusOne;
            fibNMinusOne = fibN;
        }
        return fibN;
    }
}
