package aiqiyi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author zhanglbjames@163.com
 * @version Created on 17-9-11.
 */
public class Main3 {
    /*
     * 爱奇艺笔试第三题：
     * AB序列问题： ssl(A,B)为整数的有序数对
     *
     * ssl(A,B) = (sqrt(A) + sqrt(B))^2
     *
     *  A - [1,n]
     *  B - [1,m]
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] s = scanner.nextLine().trim().split(" ");
        if (s.length != 2) {
            System.out.println(0);
            return;
        }
        int n = Integer.valueOf(s[0]);
        int m = Integer.valueOf(s[1]);

        ArrayList<Integer> nList = findK(n);
        ArrayList<Integer> mList = findK(m);

        /*
        * 满足条件如下：
        *
        * 1. A,B都是平方幂
        * 2. A == B
        * 3. A不是平方幂，B是
        * 4. A是平方幂，B不是
        * */

        // 用于存储已经访问过的 3,4情况的AB对(key为A)
        HashMap<Integer, ArrayList<Integer>> visited = new HashMap<>();

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (nList.contains(i)) {
                for (int j = 1; j <= m; j++) {
                    // 已经访问过
                    if (visited.get(i) != null) {
                        if (visited.get(i).contains(j)) {
                            continue;
                        }
                    }
                    // B为平方幂或者AB相等
                    if (mList.contains(j) || i == j) {
                        count++;
                    }
                    // 4.
                    else if (i * j <= n && j != i * j) {
                        count++;
                        if (visited.get(i * j) == null) {
                            ArrayList<Integer> list = new ArrayList<>();
                            list.add(j);
                            visited.put(i * j, list);
                        } else {
                            visited.get(i * j).add(j);
                        }
                    }
                }
            } else {
                for (int j = 1; j < m; j++) {
                    // 已经访问过
                    if (visited.get(i) != null) {
                        if (visited.get(i).contains(j)) {
                            continue;
                        }
                    }

                    // 3.
                    if (i == j) {
                        count++;
                    } else if (mList.contains(j) && i * j <= m && i * j != i) {
                        count++;
                        if (visited.get(i) == null) {
                            ArrayList<Integer> list = new ArrayList<>();
                            list.add(i * j);
                            visited.put(i, list);

                        } else {
                            visited.get(i).add(i * j);
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }

    private static ArrayList<Integer> findK(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        int i = 1;
        while (i * i <= n) {
            list.add(i * i);
            i += 1;
        }
        return list;
    }
}
