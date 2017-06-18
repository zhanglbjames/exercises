package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/13.
 */
/**
 * <p>Description</p>
 * 输入两个单调递增的链表，输出两个链表合成后的链表，
 * 当然我们需要合成后的链表满足单调不减规则。
 */


public class Exercise_16 {
    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode Merge(ListNode list1,ListNode list2){
        ListNode list1Head = list1;
        ListNode list2Head = list2;
        // 新的头结点
        ListNode newHead = null;
        // 新的尾节点
        ListNode newTail = null;

        // 检查 list1和list2 都不为null
        if (list1Head == null){
            return list2Head;

        }
        if (list2Head == null){
            return list1Head;
        }
        // 都不为null，则进行下面循环
        for (;;){
            if (list1Head == null){
                newTail.next = list2Head;
                break;
            }
            if (list2Head == null){
                newTail.next = list1Head;
                break;
            }
            // list1 a, list2 b, newList c
            // 由于链表是不减的，所以总存在 c <= a && a <= b
            // 主要就是找出a和b中较小的一个，当a==b时取a
            if (list1Head.val <= list2Head.val){
                // 当newHead为null，则初始化newHead、listNew
                if (newHead == null){
                    newHead = newTail = list1Head;
                }
                // 当不为null，则需要链接较小的节点，并且需要更新新节点的尾指针
                else {
                    newTail.next = list1Head;
                    newTail = newTail.next;
                }

                list1Head = list1Head.next;
            }
            else {
                // 当listNew为初始null节点
                if (newHead == null){
                    newHead = newTail = list2Head;
                }else {
                    newTail.next = list2Head;
                    newTail = newTail.next;
                }

                list2Head = list2Head.next;
            }
        }
        return newHead;

    }
    public static void main(String[] args){
        ListNode node1 = new ListNode(1);
        ListNode node2 = node1.next = new ListNode(2);
        node2.next = new ListNode(5);

        ListNode node3 = new ListNode(1);
        ListNode node4 = node3.next = new ListNode(1);
        node4.next = new ListNode(2);

        ListNode newNode = Merge(node1,node3);
        while(newNode != null){
            System.out.println(newNode.val);
            newNode = newNode.next;
        }

    }

}