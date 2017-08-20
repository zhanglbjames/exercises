package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/20.
 */

/**
 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，
 * 就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，
 * 请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,
 * 要求输出循环左移3位后的结果，即“XYZdefabc”。
 *
 * 是不是很简单？OK，搞定它！
 */
public class Exercise_39 {
    public String leftRotateString(String str, int n){

        if (str == null || str.length() == 0){
            return str;
        }

        StringBuilder stringBuilder1 = new StringBuilder(str);

        for (int i = 0; i < n; i++) {
            char element = stringBuilder1.charAt(0);

            stringBuilder1 = new StringBuilder(stringBuilder1.substring(1,stringBuilder1.length()));
            stringBuilder1.append(element);
        }
        return stringBuilder1.toString();
    }

    public static void main(String[] args) {
        Exercise_39 exercise_39 = new Exercise_39();

        System.out.println(exercise_39.leftRotateString("abcXYZdef",3));

    }
}
