package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/4.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 */
public class Exercise_24 {

    private class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 前序遍历 + 栈轨迹
    public ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> storage = new ArrayList<ArrayList<Integer>>();
        Stack<Integer> stack = new Stack<Integer>();
        traversal(root, stack, target, storage);
        return storage;
    }

    private void traversal(TreeNode root, Stack<Integer> stack, int target, ArrayList<ArrayList<Integer>> storage) {
        // 0. 上一次遍历不为叶子节点，但只有一个子节点
        if (root == null) { //(递归结束)
            return;
        }
        // 1. 每访问一个节点，则记录这个节点到路径上
        stack.add(root.val);

        // 2. 获得求和的结果
        Iterator<Integer> iter = stack.iterator();
        int count = 0;
        while (iter.hasNext()) {
            count += iter.next();
        }
        // 3. 和相等，而且为叶子节点
        if (count == target && root.left == null && root.right == null) {
            // 添加结果
            storage.add(new ArrayList<Integer>(stack));
        }
        // 4. 不相等或者非叶子节点(继续前序遍历)
        else {
            traversal(root.left, stack, target, storage);
            traversal(root.right, stack, target, storage);
        }
        // 5. 不管匹不匹配，都要在从子节点返回父节点时删除路径上的此节点
        stack.pop();
    }
}

