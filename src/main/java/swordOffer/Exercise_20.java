package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/18.
 */

import java.util.Stack;

/**
 * <p>Description</p>
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。
 */
public class Exercise_20 {
    // 数据栈
    private Stack<Integer> s_data;
    // 存储每一步最小值的辅助栈
    private Stack<Integer> s_min;
    // 当前的最小值
    private Integer min;

    public Exercise_20(){
        s_data = new Stack<Integer>();
        s_min = new Stack<Integer>();
        min = null;
    }
    public void push(int node) {
        s_data.push(node);
        // 更新min
        if (min == null){
            min = node;
        }else {
            min = min <= node ? min : node;
        }
        s_min.push(min);
    }

    public void pop() {
        if (s_data.empty() || s_min.empty()){
            return;
        }
        s_min.pop();
        s_data.pop();
        min = s_min.peek();
    }

    public int top() {
        if (s_data.empty() || s_min.empty()){
            return -1;
        }
        return s_data.peek();
    }

    public int min() {
        if (s_min.empty()){
            return -1;
        }
        else {
            return s_min.peek();
        }
    }
    public static void main(String[] args){
        Exercise_20 e = new Exercise_20();
        e.push(3);
        System.out.println(e.min());
        e.push(4);
        System.out.println(e.min());
        e.push(2);
        System.out.println(e.min());
        e.push(3);
        System.out.println(e.min());
        e.pop();
        System.out.println(e.min());
        e.pop();
        System.out.println(e.min());
        e.pop();
        System.out.println(e.min());
        e.push(0);
        System.out.println(e.min());
    }
}
