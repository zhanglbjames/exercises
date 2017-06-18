package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/11.
 */

/**
 * <p>Description</p>
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 */
import java.util.EmptyStackException;
import java.util.Stack;

public class Exercise_5 {
    private Stack<Integer> stack1 = new Stack<Integer>();
    private Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node){
        stack1.push(node);

    }
    public int pop(){
        if (stack2.empty()){
            while(!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
        if (stack2.empty()) {
            throw new EmptyStackException();
        }

        return stack2.pop();
    }

    public static void main(String[] args){
        Exercise_5 exercise5 = new Exercise_5();
        exercise5.push(1);
        exercise5.push(2);
        exercise5.push(3);
        exercise5.push(4);
        exercise5.push(5);
        exercise5.push(6);
        System.out.println(exercise5.pop());
        System.out.println(exercise5.pop());
        System.out.println(exercise5.pop());
        System.out.println(exercise5.pop());
        System.out.println(exercise5.pop());
        System.out.println(exercise5.pop());

    }
}
