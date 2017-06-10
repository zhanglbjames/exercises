package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/10.
 */

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * <p>Description</p>
 * 输入一个链表，从尾到头打印链表每个节点的值。
 */
public class Exercise_3 {

    // 单向链表
    private static class ListNode{
        int val;
        ListNode next = null;
        ListNode(int val){
            this.val = val;
        }
    }

    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        ArrayDeque<Integer> stack = new ArrayDeque<Integer>();

        if (listNode == null) {
            return new ArrayList<Integer>(stack);
        }
        for (ListNode node = listNode; node != null; node = node.next){
            stack.addFirst(node.val);
        }
        return new ArrayList<Integer>(stack);
    }

    // 递归打印
    private static void doDG(ListNode listNode){
        if (listNode.next == null) {
            System.out.println(listNode.val);
            return;
        }
        doDG(listNode.next);
        System.out.println(listNode.val);

    }

    // 递归添加
    private static void doDGA(ListNode listNode, ArrayList<Integer> tmp){
        if (listNode.next == null) {
            tmp.add(listNode.val);
            return;
        }
        doDGA(listNode.next,tmp);
        tmp.add(listNode.val);

    }

    // 主递归
    private static  ArrayList<Integer> DGA(ListNode listNode){
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        doDGA(listNode,tmp);
        return tmp;
    }
    public static void main(String[] args){
        ListNode node1 = new ListNode(1);
        ListNode node2 = node1.next = new ListNode(2);
        ListNode node3 = node2.next = new ListNode(3);
        ListNode node4 = node3.next = new ListNode(4);
        ListNode node5 = node4.next = new ListNode(5);
        ListNode node6 = node5.next = new ListNode(6);

        Integer[] result = printListFromTailToHead(node1).toArray(new Integer[0]);
        System.out.println(Arrays.toString(result));

        Integer[] result1 = DGA(node1).toArray(new Integer[0]);
        System.out.println(Arrays.toString(result));

        doDG(node1);

    }
}
