package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/23.
 */

/**
 * 题目描述:
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 *
 * 输出描述:如果当前字符流没有存在出现一次的字符，返回#字符。
 */

public class Exercise_50 {
    private int[] chars = new int[256];
    private int index;

    public Exercise_50() {
        for (int i = 0; i < chars.length; i++) {
            chars[i] = -1;
        }
    }
    public void insert(char ch) {

        if (chars[ch] == -1){
            chars[ch] = index;
        }else if (chars[ch] > -1){
            chars[ch] = -2;
        }
        index ++;
    }

    public char firstAppearingOnce() {
        int result = -1;
        int indexAppear = Integer.MAX_VALUE;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > -1) {
                if (chars[i] < indexAppear) {
                    indexAppear = chars[i];
                    result = i;
                }
            }
        }

        return result > 0 ? (char) result :'#';
    }
}
