package swordOffer;

import java.util.Arrays;
/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/10.
 */


/**
 * <p>Description</p>
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class Exercise_1 {
    public static boolean find(int[][] array, int target){
        if (array == null){
            return false;
        }
        int rows  = array.length;
        int columns = array[0].length;

        for(int i = 0, j = columns -1; i < rows && j >= 0; ){
            int comparator = target - array[i][j];

            if (comparator == 0){
                return true;
            }
            else if (comparator > 0){
                ++i;
            }else{
                --j;
            }
        }
        return false;
    }
    public static void main(String[] args){
        int[][] b = new int[10][5];

        System.out.println(find(b,1));

    }
}
