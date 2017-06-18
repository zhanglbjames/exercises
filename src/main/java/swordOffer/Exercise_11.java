package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/12.
 */
/**
 * <p>Description</p>
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 */
public class Exercise_11 {
    public static int NumberOf1(int n){
        if (n == 0){
            return 0;
        }
        int countOne = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1){
                ++ countOne;
            }
            n = n >>> 1;
        }
        return countOne;
    }
    public static void main(String[] args){
        System.out.println(NumberOf1(-4));
    }
}
