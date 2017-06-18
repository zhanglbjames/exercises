package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/18.
 */


/**
 操作给定的二叉树，将其变换为源二叉树的镜像。
 输入描述:
 二叉树的镜像定义：源二叉树
   8
 /  \
  6   10
 / \  / \
 5  7 9 11
 镜像二叉树
  8
 /  \
 10   6
 / \  / \
 11 9 7  5

 */

public class Exercise_18 {
    // 向下遍历 + 旋转左右子树
    public static void Mirror(TreeNode root) {
        doMirror(root);
    }
    private  static void doMirror(TreeNode root) {
        // 递归结束条件为 节点为null
        if (root == null){
            return;
        }
        // 旋转
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        // 递归
        doMirror(root.left);
        doMirror(root.right);
    }
}
class TreeNode{
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }
}
