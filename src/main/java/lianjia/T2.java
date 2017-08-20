package lianjia;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/19.
 */
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class T2 {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] aValue = new int[n];

        for (int i = 0; i < n; i++) {
            aValue[i] = scanner.nextInt();
        }
        Set<Integer> treeSst = new TreeSet<Integer>();
        for (int i = 0; i < aValue.length; i++) {
            treeSst.add(aValue[i]);
        }
        System.out.println(treeSst.size());
        int aCount = 1;
        for (int result : treeSst) {
            if (aCount < treeSst.size()) {
                System.out.print(result + " ");
                aCount++;
            } else {
                System.out.print(result);
            }
        }
    }
}
