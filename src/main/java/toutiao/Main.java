package toutiao;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/22.
 */

import java.util.*;

/**
 * P为给定的二维平面整数点集。
 * 定义 P 中某点x，如果x满足 P 中任意点都不在 x 的右上方区域内（横纵坐标都大于x），则称其为“最大的”。
 * 求出所有“最大的”点的集合。（所有点的横坐标和纵坐标都不重复, 坐标轴范围在[0, 1e9) 内）
 * 如下图：实心点为满足条件的点的集合。
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nPoints = Integer.valueOf(scanner.nextLine().trim());

        TreeMap<Integer,Integer> xOrderPoints = new TreeMap<>();

        HashMap<Integer,Integer> hashMap = new HashMap<>();

        int xMax = Integer.MIN_VALUE;
        int[] xMaxPoint = new int[2];

        int yMax = Integer.MIN_VALUE;
        int[] yMaxPoint = new int[2];
        for (int i = 0; i < nPoints; i++) {
            String[] xyString = scanner.nextLine().split(" ");

            int x = Integer.valueOf(xyString[0]);
            int y = Integer.valueOf(xyString[1]);

            xOrderPoints.put(x,y);
            if (x > xMax) {
                xMax =x;
                xMaxPoint[0] =x;
                xMaxPoint[1] =y;
            }
            if (y > yMax) {
                yMax = y;
                yMaxPoint[0] =x;
                yMaxPoint[1] =y;
            }
        }

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(xMaxPoint[0],xMaxPoint[1]);
        treeMap.put(yMaxPoint[0],yMaxPoint[1]);


        TreeMap<Integer,Integer> treeMapTmp = new TreeMap<>();
        for (int i = yMaxPoint[0] +1; i < xMaxPoint[0]; i++) {
            treeMapTmp.clear();

            if (xOrderPoints.get(i) == null){
                continue;
            }
            int y = xOrderPoints.get(i);

            Map.Entry<Integer,Integer> entry = xOrderPoints.ceilingEntry(i + 1);
            while (entry != null) {
                treeMapTmp.put(entry.getValue(), entry.getKey());
                entry = treeMap.ceilingEntry(entry.getKey() + 1);
            }

            if (treeMapTmp.ceilingKey(y) == null) {
                treeMap.put(i,xOrderPoints.get(i));
            }
        }

        Iterator<Map.Entry<Integer, Integer>> iterator = treeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }
}
