package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/21.
 */

import java.util.ArrayList;

/**
 * <p>Description</p>
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4，5,3,2,1是
 * 该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 * （注意：这两个序列的长度是相等的）
 */
public class Exercise_21 {
    public boolean isPopOrder(int[] pushA, int[] popA) {

        if ((popA == null || pushA == null) ||
                (popA.length == 0 || pushA.length == 0) ||
                popA.length != pushA.length) {
            return false;
        }
        //boolean flag = true;
        ArrayList<Integer> stack = new ArrayList<Integer>();
        //---------------注意j的返回，因为是每add一次则自增1，所以j范围[1,size]
        for (int i = 0, j=0, num = popA.length; i < num ; ++i) {
            // 当pop还需要弹出，而push已经越界了，说明找不到对应的元素，则返回false
            if (j > num){
                return false;
            }
            // 当栈为空，并且还有待压入元素，则压入一个元素，保证下面的stack不为空
            if (stack.size() == 0 ){
                stack.add(pushA[j++]);
            }
            // 栈顶元素和弹出元素相等
            if (stack.get(stack.size()-1) == popA[i]){
                stack.remove(stack.size()-1);
            }
            // 不相等，则继续压入后续元素，直到相等或则压入序列为空
            else {
                push : while (j <= num){
                    if (stack.get(stack.size()-1) == popA[i]){
                        stack.remove(stack.size()-1);
                        break push;
                    }
                    else if (j < num){
                        stack.add(pushA[j++]);
                    }else return false;

                }
            }

        }
        // 前面循环都通过，则返回true
        return true;
    }
    public static void main(String[] args){
        Exercise_21 e = new Exercise_21();
        int[] a = {1,2,3,4,5};
        int[] b ={4,3,5,1,2};
         System.out.println(e.isPopOrder(a,b));
    }
}
