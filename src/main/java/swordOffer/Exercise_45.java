package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/22.
 */
/**
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 */


public class Exercise_45 {
    /**
     * 借位和进位效果是一样的，因为每一位的状态都是0或1，所以此题不用考虑正数加负数问题
     */
    public int add(int num1, int num2) {

        int sum, carry;

        do {
            sum = num1 ^ num2;
            carry = (num1 & num2) <<1;

            num1 = sum;
            num2 = carry;
        }while (num2 != 0);

        return num1;
    }
}
