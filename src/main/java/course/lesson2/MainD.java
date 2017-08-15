package course.lesson2;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/12.
 */

import java.util.Scanner;
public class MainD {

    public static int doFind(int[] newXIndex,int [] newYIndex,int leastN,int num) {
        int[][] xy = count(newXIndex,newYIndex,num);

        for (int i = 0; i < num; i++) {
            for (int j = i; j <num ; j++) {

            }
        }

    }

    public int countNearOne(int[][] xy,int xIndex,int yIndex,int num) {

        int count = 0;

        int newXIndex = xIndex;
        int newYIndex = yIndex;

        while (true){
            if (newXIndex + 1 < num && xy[newXIndex + 1][newYIndex] > 0) {
                break;
            }
        }

    }

    public static int[][] count(int[] newXIndex,int[] newYIndex,int num) {
        int[][] xy = new int[num][num];
        for (int i = 1; i <= num; i++) {
           xy[newXIndex[i]][newYIndex[i]] += 1;
        }

        return xy;
    }

    public static int findMax(int[][] xy,int num){
        int max = 0;

        for (int i = 0; i < num; i++) {
            for (int j = i; j < num ; j++) {
                if (xy[i][j] > max){
                    max = xy[i][j];
                }
            }
        }
        return max;

    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();
        int[] xIndex = new int[num];

        for (int i = 0; i <= num; i++) {
            xIndex[i] = scanner.nextInt();
        }

        int[] yIndex = new int[num];

        for (int i = 0; i <= num; i++) {
            yIndex[i] = scanner.nextInt();
        }

        int[] result = new int[num];

        for (int i = 0; i < num; i++) {

        }
    }
}
