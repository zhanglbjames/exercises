package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/23.
 */

import java.util.ArrayList;
import java.util.HashSet;

/**
 * <p>Description</p>
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * 输入描述:
 * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 */
public class Exercise_27 {
    public static ArrayList<String> permutation(String str) {

        ArrayList<String> result = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return result;
        }

        char[] chars = str.toCharArray();
        StringBuilder aPermutation = new StringBuilder();
        doPermutation(result, aPermutation, chars);

        return new ArrayList<String>(new HashSet<String>(result));
    }

    private static void doPermutation(ArrayList<String> result, StringBuilder aPermutation, char[] remain) {
        if (remain.length <= 1) {
            StringBuilder complete = new StringBuilder();
            complete.append(aPermutation);
            complete.append(remain[0]);
            result.add(complete.toString());
        } else {
            for (int i = 0; i < remain.length; i++) {
                // 复制新数组
                char[] newRemain = new char[remain.length - 1];
                System.arraycopy(remain, 0, newRemain, 0, i);
                System.arraycopy(remain, i + 1, newRemain, i, remain.length - 1 - i);

                StringBuilder sub = new StringBuilder();
                sub.append(aPermutation);

                doPermutation(result, sub.append(remain[i]), newRemain);
            }
        }

    }

    public static void main(String[] args) {
        String str = "abccc";
        ArrayList<String> result = permutation(str);
        for (String stri : result) {
            System.out.println(stri);
        }
    }

}
