package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/20.
 */

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
 * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
 * 现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 *
 * 输出描述:输出所有和为S的连续正数序列。
 * 序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 */
public class Exercise_37 {

    public ArrayList<ArrayList<Integer>> find(int sum){

        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<ArrayList<Integer>>();
        if (sum < 3){
            return arrayLists;
        }

        // 主要思路是计算余数和
        for (int i = 2;i <= sum - sum1(i-1); i++) {
            if ((sum - sum1(i-1)) % i == 0){
                int start = (sum - sum1(i-1))/i;
                add(arrayLists,start,i);

                // 如果开始位置为1，则说明已经到达最大长度，则跳出循环
                if (start ==1){
                    break;
                }
            }
        }

        // 排序
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for (int i = arrayLists.size()-1; i >= 0; i--) {
            result.add(arrayLists.get(i));
        }
        return result;
    }

    private void add(ArrayList<ArrayList<Integer>> list,int start,int n) {
        ArrayList<Integer> aList  = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            aList.add(start + i);
        }
        list.add(aList);
    }

    private int sum1(int n) {
        return  (1 + n) * n /2;
    }

    public static void main(String[] args) {
        Exercise_37 exercise_37 = new Exercise_37();
        ArrayList<ArrayList<Integer>> result =   exercise_37.find(100);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(Arrays.toString(result.get(i).toArray()));
        }
    }
}
