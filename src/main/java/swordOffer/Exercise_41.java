package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/20.
 */

import java.util.Arrays;

/**
 * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
 * 他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,
 * 他决定去买体育彩票,嘿嘿！！“红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....
 * LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
 * 上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。
 * LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何。
 * 为了方便起见,你可以认为大小王是0。
 */
public class Exercise_41 {
    public boolean isContinues(int[] numbers) {
        if (numbers == null || numbers.length < 1){
            return false;
        }
        if (numbers.length == 1){
            return true;
        }

        Arrays.sort(numbers);

        // 统计0的数量
        int countZero = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0) {
                countZero ++;
            }else break;
        }

        // 统计空缺，或者遇见重复返回false
        int countAbsent = 0;
        int lastNum = -1;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0)
                continue;
            if (lastNum == -1) {
                lastNum = numbers[i];
                continue;
            }
            if (lastNum == numbers[i]){
                return false;
            }else {
                countAbsent += (numbers[i] -lastNum -1);
                lastNum = numbers[i];
            }
        }
        return countZero >= countAbsent;

    }

    public static void main(String[] args) {
        Exercise_41 exercise_41 = new Exercise_41();
        int[] array = new int[]{2,3,5,7,0,0};
        System.out.println(exercise_41.isContinues(array));
    }
}
