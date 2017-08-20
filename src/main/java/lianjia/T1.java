package lianjia;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/19.
 */

public class T1 {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int mValue = scanner.nextInt();
            int[] numArr = new int[mValue];
            for (int i = 0; i < mValue; i++) {
                numArr[i] = scanner.nextInt();
            }

            int nValue = scanner.nextInt();
            int[] queryValue = new int[nValue];
            for (int i = 0; i < nValue; i++) {
                queryValue[i] = scanner.nextInt();
            }
            ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
            function(numArr, queryValue, integerArrayList);

            for (int element : integerArrayList) {
                System.out.println(element);
            }
        }
    }

    public static void function(int[] numArr, int[] queryValue, ArrayList<Integer> integerArrayList) {
        int countValue = 0;
        int allValue = 0;
        for (int n : numArr) {
            allValue += n;
        }
        for (int i = 0; i < queryValue.length; i++) {
            countValue = queryValue[i];
            if (countValue < 1 || countValue > allValue) {
                integerArrayList.add(0);
                continue;
            }
            for (int j = 0; j < numArr.length; j++) {
                if (countValue <= numArr[j]) {
                    integerArrayList.add(j + 1);
                    break;
                } else {
                    countValue = countValue - numArr[j];
                }
            }
        }
    }
}


