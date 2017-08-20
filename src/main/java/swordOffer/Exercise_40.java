package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/20.
 */

/**
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
 * 同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
 * 例如，“student. a am I”。
 * 后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。
 * Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 */
public class Exercise_40 {
    public String reverseSentence(String str) {
        if (str == null || str.trim().equals("")){
            return str;
        }

        String newString = doReserve(str);

        String[] words = newString.split(" ");

        /*
        * for each 和 for i 的区别
        *
        * 1. for each : 特别在对象数组迭代中
        *      for e : array
        *          e = function(e);
        *    则 其实e 还是等于原来的值，并没有因为方法调用返回从新赋值，参见注释
        *
        * 2. for i 则相反，可以更改指定位置元素
        * */
//
//        for(String word : words){
//            word = doReserve(word);
//        }
        for (int i = 0; i < words.length; i++) {
            words[i] = doReserve(words[i]);
        }
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            resultString.append(words[i]);
            if (i < words.length -1){
                resultString.append(" ");
            }
        }
        return resultString.toString();
    }

    private String doReserve(String str){
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = str.length() -1; i >= 0; i--) {
            stringBuilder.append(str.charAt(i));
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Exercise_40 exercise_40 = new Exercise_40();
        System.out.println(exercise_40.reverseSentence(" "));
    }
}
