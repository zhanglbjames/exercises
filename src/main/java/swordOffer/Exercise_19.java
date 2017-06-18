package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/18.
 */

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>Description</p>
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下矩阵：
 * 1  2  3  4
 * 5  6  7  8
 * 9  10 11 12
 * 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class Exercise_19 {
    public static ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<Integer>(0);
        if (matrix == null) {
            return result;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        if (rows == 0 && columns == 0){
            return result;
        }

        // two points
        // leftTop (0,0) , rightBottom (columns-1,rows-1)
        // 每一次打印完一圈，则leftTop都加1，rightBottom都减1

        // 判断结束条件
        // 当且仅当 左上角横或纵坐标大于右下坐标的对应的横或纵坐标
        for (int point1Column = 0, point1Row = 0, point2Column = columns - 1, point2Row = rows - 1;
             point1Column <= point2Column && point1Row <= point2Row;
             ++point1Column, ++point1Row, --point2Column, --point2Row) {

            printCycle(point1Column, point1Row, point2Column, point2Row, result, matrix);
        }
        return result;
    }

    private static void printCycle(int point1Column, int point1Row, int point2Column, int point2Row,
                                   ArrayList<Integer> result, int[][] matrix) {

        // 只有一个元素
        if (point1Column == point2Column && point1Row == point2Row){
            result.add(matrix[point1Row][point1Row]);
            return;
        }

        // 横条 ----
        if (point1Row == point2Row){
            for (int i = point1Column; i <= point2Column; ++i) {
                result.add(matrix[point1Row][i]);
            }
            return;
        }
        // 竖条 |
        if (point1Column == point2Column){
            for (int i = point1Row; i <= point2Row; ++i) {
                result.add(matrix[i][point2Column]);
            }
            return;
        }

        // --------横竖都大于1的情况-----------
        // print ---o
        for (int i = point1Column; i < point2Column; ++i) {
            result.add(matrix[point1Row][i]);
        }
        //print |
        //      o
        for (int i = point1Row; i < point2Row; ++i) {
            result.add(matrix[i][point2Column]);
        }
        // print o----
        for (int i = point2Column; i > point1Column; --i) {
            result.add(matrix[point2Row][i]);
        }
        // print o
        //       |
        for (int i = point2Row; i > point1Row; --i) {
            result.add(matrix[i][point1Column]);
        }
    }
    public static void main(String[] args){
        int[][] matrix = {{1, 2, 3, 4},
                          {5, 6, 7, 8},
                          {9, 10, 11, 12},
                          {13, 14, 15, 16}};
        int[][] matrix1 = {{1, 2, 3, 4},
                           {5, 6, 7, 8},
                           {9, 10, 11, 12}};

        int[][] matrix2 = {{1},{2},{3},{4}};
        ArrayList<Integer> result = printMatrix(matrix2);
        System.out.println(Arrays.toString(result.toArray(new Integer[0])));
    }

}
