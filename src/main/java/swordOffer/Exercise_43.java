package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/22.
 */

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 */
public class Exercise_43 {
    ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();


        if (pRoot == null){
            return list;
        }

        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.push(pRoot);
        doPrint(linkedList,list);

        return list;
    }
    private void doPrint(LinkedList<TreeNode> list,ArrayList<ArrayList<Integer>> intList) {

        if (list.size() == 0) {
            return;
        }

        ArrayList<Integer> arrayList = new ArrayList<>();

        int size = list.size();

        for (int i = 0; i < size; i++) {
            TreeNode treeNode = list.poll();
            arrayList.add(treeNode.val);
            if (treeNode.left != null) {
                list.offer(treeNode.left);
            }
            if (treeNode.right != null) {
                list.offer(treeNode.right);
            }
        }
        intList.add(arrayList);

        doPrint(list,intList);
    }
}
