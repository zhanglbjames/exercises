package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/30.
 */

import java.util.ArrayList;

/**
 * 把只包含因子2、3和5的数称作丑数（Ugly Number）。
 * 例如6、8都是丑数，但14不是，因为它包含因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */
public class Exercise_33 {
    public int getUglyNumber(int index){

        if (index <= 0){
            return 0;
        }
        if (index <= 5){
            return index;
        }

        ArrayList<Integer> uglyList = new ArrayList<Integer>();
        uglyList.add(1);
        uglyList.add(2);
        uglyList.add(3);
        uglyList.add(4);
        uglyList.add(5);

        int maxUgly = 5;



        for (int i = 6; i <= index; i++) {
            // 下一个大于List中最大丑数的乘以N（2,3,5）的数
            int nextUgly2 = 0;
            int nextUgly3 = 0;
            int nextUgly5 = 0;

            boolean flag_2 = false;
            boolean flag_3 = false;
            boolean flag_5 = false;

            for (Integer ugly : uglyList){
                if (flag_2 && flag_3 && flag_5){
                    break;
                }
                if (!flag_2 && ugly*2 > maxUgly){
                    flag_2 = true;
                    nextUgly2 = ugly*2;
                }
                if (!flag_3 && ugly*3 > maxUgly){
                    flag_3 = true;
                    nextUgly3 = ugly*3;
                }
                if (!flag_5 && ugly*5 > maxUgly){
                    flag_5 = true;
                    nextUgly5 = ugly *5 ;
                }
            }

            uglyList.add(maxUgly = findMin(nextUgly2,nextUgly3,nextUgly5));

        }
        return maxUgly;
    }
    private int findMin(int n1, int n2, int n3){
        return n1 < n2 ?
                n1 < n3 ? n1 : n3
                : n2 < n3 ? n2 : n3;
    }
}
