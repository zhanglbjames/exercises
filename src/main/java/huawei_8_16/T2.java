package huawei_8_16;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/16.
 */
public class T2 {
    private static HashMap<String, String> pathMap = new HashMap<>();
    private static Set<String> circlePoints = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String patternStr = "([A-Za-z0-9]+)";
        Pattern pattern = Pattern.compile(patternStr);
        while (true) {
            String str = scanner.nextLine();
            Matcher matcher = pattern.matcher(str);

            String key = "";
            if (matcher.find())
                key = matcher.group(0);

            String value = "";
            if (matcher.find())
                value = matcher.group(0);

            pathMap.put(key, value);

            if (!str.endsWith(",")) {
                break;
            }
        }

        if (pathMap.size() <= 1) {
            System.out.println("");
        }
        Iterator<String> iterator = pathMap.keySet().iterator();
        while (iterator.hasNext()) {
            String point = iterator.next();
            floyd(point);
        }
        print();
    }

    private static void print() {
        int size = pathMap.size();
        int count = 0;

        Iterator<Map.Entry<String, String>> iterator = pathMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String point = entry.getKey();

            StringBuilder pointStr1 =  new StringBuilder("");
            StringBuilder pointStr2 =  new StringBuilder("");
            if (circlePoints.contains(point)) {
               pointStr1.append("{" + point + " true},");
            } else {
                pointStr1.append("{" + point + " false},");
            }

            String nextPoint = entry.getValue();
            if (circlePoints.contains(nextPoint)) {
                pointStr2.append("{" + point + " true}");
            } else {
                pointStr2.append("{" + point + " false}");
            }

            if (count <= size -1){
                pointStr2.append(",");
            }

            System.out.println(pointStr1);
            System.out.println(pointStr2);
        }
    }

    private static void setCircle(int[] result, String startPoint) {
        if (result[1] > 1) {
            String nextpPoint = findStartPoint(result[0], startPoint);
            for (int i = 0; i < result[1]; i++) {
                circlePoints.add(nextpPoint);
                nextpPoint = pathMap.get(nextpPoint);
            }
        }
    }

    private static String findStartPoint(int startIndex, String startPoint) {
        String nextPoint = startPoint;
        for (int i = 1; i < startIndex; i++) {
            nextPoint = pathMap.get(nextPoint);
        }
        return nextPoint;
    }

    private static void floyd(String startPoint) {
        String tortoise = pathMap.get(startPoint);
        String hare = pathMap.get(tortoise);

        while (tortoise != null && hare != null && !tortoise.equals(hare)) {
            tortoise = pathMap.get(tortoise);
            hare = pathMap.get(hare);
        }

        int mu = 0;
        tortoise = startPoint;

        while (tortoise != null && hare != null && !tortoise.equals(hare)) {
            tortoise = pathMap.get(tortoise);
            hare = pathMap.get(hare);
            mu += 1;
        }

        int lam = 1;
        hare = pathMap.get(tortoise);
        while (tortoise != null && hare != null && !tortoise.equals(hare)) {
            hare = pathMap.get(hare);
            lam += 1;
        }
        int[] result = new int[2];
        result[0] = mu;
        result[1] = lam;

        setCircle(result, startPoint);
    }

}
