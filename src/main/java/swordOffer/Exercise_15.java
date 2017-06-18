package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/13.
 */

/**
 * <p>Description</p>
 * 输入一个链表，反转链表后，输出链表的所有元素
 */
public class Exercise_15 {
    public static ListNode reverseList(ListNode head) {
        ListNode newHead = head;

        ListNode preNode = null;
        ListNode successNode = null;
        while (newHead != null) {
            successNode = newHead.next;
            newHead.next = preNode;

            preNode = newHead;
            newHead = successNode;

        }
        return preNode;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = node1.next = new ListNode(2);
        ListNode node3 = node2.next = new ListNode(3);
        ListNode node4 = node3.next = new ListNode(4);
        ListNode node5 = node4.next = new ListNode(5);
        ListNode head = reverseList(node1);
        System.out.println(head.val);
        System.out.println(head.next.val);
        System.out.println(head.next.next.val);
        System.out.println(head.next.next.next.val);
        System.out.println(head.next.next.next.next.val);
        System.out.println(head.next.next.next.next.next.val);
        //System.out.println(reverseList(node1).next.next.val);
        //System.out.println(reverseList(node1).next.next.next.val);
//        System.out.println(reverseList(node1).next.next.next.next.val);
    }

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
