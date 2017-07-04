package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/4.
 */

/**
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，
 * 另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。
 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */
public class Exercise_25 {

    public RandomListNode clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        // 1. 复制节点
        cloneNext(pHead);
        // 2. 设置random
        setRandom(pHead);
        // 3. 拆分
        RandomListNode result = splitList(pHead);
        RandomListNode resultWrap = new RandomListNode(result.label);
        resultWrap.next = result.next;
        return resultWrap;
        //return splitList(pHead);
    }

    // 将每一个克隆的节点放在原节点的后面
    private void cloneNext(RandomListNode pHead) {
        RandomListNode node = pHead;
        while (node != null) {
            RandomListNode newNext = new RandomListNode(node.label);
            RandomListNode originNext = null;
            if (node.next != null) {
                originNext = node.next;
            }
            node.next = newNext;
            newNext.next = originNext;
            node = originNext;
        }
    }

    // 设置每个克隆节点的random引用
    private void setRandom(RandomListNode pHead) {
        RandomListNode node = pHead;
        while (node != null) {

            if (node.random != null) {
                // 将原节点的下一复制节点的random引用为元节点random引用的下一个复制节点
                node.next.random = node.random.next;

            }
            // 然后跳到下一个元节点
            node = node.next.next;
        }
    }

    // 拆分链表
    private RandomListNode splitList(RandomListNode pHead) {
        RandomListNode headClone = pHead.next;
        RandomListNode nodeJ = pHead;
        RandomListNode nodeO = headClone;
        //RandomListNode travesalNode = pHead;

        while (nodeJ != null && nodeO != null) {

            // 原节点
            nodeJ.next = nodeO.next;
            nodeJ = nodeJ.next;
            // 复制节点
            if (nodeJ != null) {
                nodeO.next = nodeJ.next;
            }
            nodeO = nodeO.next;
        }
        return headClone;

    }

    public static void main(String[] args) {
        Exercise_25 exer = new Exercise_25();

        RandomListNode head = new RandomListNode(1);
        RandomListNode node1 = head.next = new RandomListNode(2);
        RandomListNode node2 = node1.next = new RandomListNode(3);
        RandomListNode node3 = node2.next = new RandomListNode(4);
        RandomListNode node4 = node3.next = new RandomListNode(5);

        head.random = node2;
        node1.random = node4;
        node3.random = node1;

        RandomListNode pnode = exer.clone(head);
        while (pnode != null) {
            System.out.print("node" + pnode.label);
            if (pnode.next != null) {
                System.out.print(" next == " + pnode.next.label);
            }
            if (pnode.random != null) {
                System.out.print(" random == " + pnode.random.label);
            }
            System.out.print("\n");
            pnode = pnode.next;
        }
    }

}

class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
