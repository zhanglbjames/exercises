package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/23.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 */
public class Exercise_49 {
    public boolean isNumeric(char[] str) {

        if (str == null || str.length == 0) {
            return false;
        }

        String regex1 = "^([\\+-]?)([0-9]+)?(([\\.]?)([0-9]+))?([eE]([\\+-]?)([0-9]+))?$";
        Pattern pattern = Pattern.compile(regex1);

        String aStr = new String(str);
        Matcher matcher = pattern.matcher(aStr);

        /*
        * 0- 开头的正负号
        * 1- 整数部分
        * 2- 小数部分
        * 3- 科学计数 后面的正负号
        * 4- 科学计数后面的数字
        * */

        String[] parts = new String[5];
        if (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println("group_" + i + ": "+ matcher.group(i));
            }
            return true;

        }
        return false;
    }

    public static void main(String[] args) {
        Exercise_49 exercise_49 = new Exercise_49();
        String str = "-.123";
        exercise_49.isNumeric(str.toCharArray());
    }

}
