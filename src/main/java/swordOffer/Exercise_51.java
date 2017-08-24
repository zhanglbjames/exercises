package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/24.
 * <p>
 * 一个链表中包含环，请找出该链表的环的入口结点。
 * <p>
 * 一个链表中包含环，请找出该链表的环的入口结点。
 */

/**
 * 一个链表中包含环，请找出该链表的环的入口结点。
 */

/**
 * 1. 首先判断是否存在环：定义两个指针，一个快一个慢，看这两个指针是否相遇
 *    第一个每次走一步，第二个每次走两步
 *
 * 2. 判断环的数量，以及环的入口节点
 */
public class Exercise_51 {

    private ListNode checkLoop(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head.next;
        if (slow == null) {
            return null;
        }
        ListNode fast = slow.next;

        while (slow != null && fast != null) {
            if (slow == fast) {
                return slow;
            }
            // 每次走一步
            slow = slow.next;

            // 每次走两步
            fast = fast.next;
            if (fast.next.next != null) {
                fast = fast.next;
            }
        }
        return null;

    }

    private ListNode getFirstLoopNode(ListNode node, ListNode head) {

        int loopCount = 1;
        ListNode tmp = node;
        while ((tmp = tmp.next) != node) {
            loopCount++;
        }

        ListNode node1 = head;
        ListNode node2 = head;

        // 先走环数量的步数
        for (int i = 0; i < loopCount; i++) {
            node2 = node2.next;
        }

        // 然后再一起走
        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }

        return node1;
    }

    public ListNode entryLoopNode(ListNode head) {
        ListNode node = checkLoop(head);
        if (node == null) {
            return null;
        } else {
            return getFirstLoopNode(node, head);
        }
    }


}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}