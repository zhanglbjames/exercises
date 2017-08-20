package lianjia;

import java.util.Scanner;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/19.
 */
public class T3 {
    public static void main(String[] args) {

       String line1 = "100 200 150 140 129 134 167 198 200 111";
        int[] apples = new int[10];

        String[] input1 = line1.split(" ");

        for (int i = 0; i < input1.length; i++) {
            apples[i] = Integer.valueOf(input1[i]);
        }

        String input2 = "110";
        int maxHeight = Integer.valueOf(input2) + 30;

        int result = 0;
        for (int i = 0; i < apples.length; i++) {
            if (apples[i] <= maxHeight)
                result++;
        }

        System.out.println(result);
    }
}
