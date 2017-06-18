package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/13.
 */

/**
 * <p>Description</p>
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 */
public class Exercise_12 {
    // 需要注意 base的取值和exponent的取值
    public static double power(double base, int exponent) {
        if ((base-0d) < 0.0000001)
            return 0d;
        if (base == 1 || exponent == 0)
            return 1d;

        // 判断exponent是否为负数
        boolean negativeExponent = false;
        if (exponent < 0)
            negativeExponent = true;

        return negativeExponent ? 1d / doPositivePower(base, -exponent) : doPositivePower(base, exponent);
    }

    private static double doPositivePower(double base, int exponent) {
        if (exponent <= 1) {
            return exponent > 0 ? base : 1;
        }
        double result = 0d;
        result = doPositivePower(base, exponent >>> 1);
        result *= result;
        // 判断是否为偶数
        if ((exponent & 1) == 0)
            return result;
        else
            return result * base;
    }


    public static void main(String[] args) {
        System.out.println(power(0.00000001, -3));
    }
}
