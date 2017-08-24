package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/22.
 */

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 题目描述 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。
 * 数值为0或者字符串不是一个合法的数值则返回0
 *
 * 输入描述:输入一个字符串,包括数字字母符号,可以为空
 * 输出描述:如果是合法的数值表达则返回该数字，否则返回0
 *
 * 示例1
 * 输入
 * +2147483647 1a33
 * 输出
 * 2147483647 0
 */
public class Exercise_46 {
    public double strToInt(String str) {

        if (str == null || str.trim().equals("")) {
            return 0;
        }

        String regex1 = "^([\\+-]?)([0-9]+)(([\\.]?)([0-9]+))?([eE]([\\+-]?)([0-9]+))?$";
        Pattern pattern = Pattern.compile(regex1);

        Matcher matcher = pattern.matcher(str);

        /*
        * 0- 开头的正负号
        * 1- 整数部分
        * 2- 小数部分
        * 3- 科学计数 后面的正负号
        * 4- 科学计数后面的数字
        * */

        String[] parts = new String[5];
        if (matcher.find()) {
           parts[0] = matcher.group(1);
           parts[1] = matcher.group(2);
           parts[2] = matcher.group(5);
           parts[3] = matcher.group(7);
           parts[4] = matcher.group(8);

        }
        return doComputeWithE(parts);
    }

    private double doComputeWithE(String[] parts) {

        double result = 0.0;
        // 不是科学计数
        if (parts[4] == null) {
           result =  doComputeWithoutE(parts);
        }
        // 是科学计算
        else {
            if (parts[3] == null || parts[3].equals("+")) {
                result = doComputeWithoutE(parts) * Math.pow(10,computeN(parts[4]));
            }else {
                result = doComputeWithoutE(parts) * Math.pow(10,-computeN(parts[4]));
            }
        }
        return result;
    }

    private double doComputeWithoutE(String[] parts) {
        int zhengshu = 0;
        if (parts[1] != null){
            zhengshu = computeN(parts[1]);
        }
        double xiaoshu = 0.0;

        // 是否含有小数
        if (parts[2] != null) {
            xiaoshu = computeN(parts[2]) / Math.pow(10,parts[2].length());
        }

        if (parts[0] == null || parts[0].equals("+")){
            return zhengshu + xiaoshu;
        }else {
            return -zhengshu;
        }
    }
    private int computeN(String str) {
        int zhengshu = 0;
        int zhengshuBase = 1;
        for (int i = str.length()-1; i >= 0; i--) {
            zhengshu += (str.charAt(i) - '0') * zhengshuBase;
            zhengshu *= 10;
        }
        return zhengshu;
    }

    public static void main(String[] args) {
        Exercise_46 exercise_46 = new Exercise_46();

        String string = "+5.112e4";
        System.out.println(exercise_46.strToInt(string));
    }

}
