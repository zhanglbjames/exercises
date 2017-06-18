package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/13.
 */
/**
 * <p>Description</p>
 * 输入一个链表，输出该链表中倒数第k个结点
 */
public class Exercise_14 {
    private static class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode findKthToTail(ListNode head, int k) {
        if (head == null || k <= 0){
            return null;
        }
        ListNode aHead = head;
        ListNode kHead = head;
        // 注意边界
        for (int i = 1; i < k; i++) {
            if (kHead.next == null){
                return null;
            }else{
                kHead = kHead.next;
            }
        }
        while(kHead.next != null){
            kHead = kHead.next;
            aHead = aHead.next;
        }
        return aHead;
    }
    public static void main(String[] args){
        ListNode node1 = new ListNode(1);
        ListNode node2 = node1.next = new ListNode(2);
        ListNode node3 = node2.next = new ListNode(3);
        ListNode node4 = node3.next = new ListNode(4);
        ListNode node5 = node4.next = new ListNode(5);
        System.out.println(findKthToTail(node1,0).val);

    }
    
}
