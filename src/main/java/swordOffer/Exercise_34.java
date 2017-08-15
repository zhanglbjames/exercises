package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/30.
 */

import java.util.HashMap;

/**
 * <p>Description</p>
 * 在一个字符串(1<=字符串长度<=10000，全部由字母组成)中
 * 找到第一个只出现一次的字符,并返回它的位置
 */
public class Exercise_34 {

    // 使用哈希表来做，因为是固定长度的字符串

    public int firstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) {
            return str == null ? 0 : -1;
        }

        HashMap<Character, Integer> hashChar = new HashMap<>();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (hashChar.containsKey(charArray[i])) {
                hashChar.put(charArray[i], hashChar.get(charArray[i]) + 1);
            } else {
                hashChar.put(charArray[i], 1);
            }
        }

        int first = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (hashChar.containsKey(charArray[i]) && hashChar.get(charArray[i]) == 1) {
                first = i;
                break;
            }
        }
        return first;
    }
}
