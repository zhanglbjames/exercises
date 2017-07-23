package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/4.
 */
/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
public class Exercise_26 {
    public static TreeNode convert(TreeNode pRootOfTree, boolean returnHead) {
        if (pRootOfTree == null || (pRootOfTree.right == null && pRootOfTree.left ==null)){
            return pRootOfTree;
        }
        TreeNode head = pRootOfTree;
        TreeNode tail = pRootOfTree;
        if (pRootOfTree.left != null){
            // 左递归
            TreeNode[] leftMinMax = traverse(pRootOfTree.left,pRootOfTree.left);
            leftMinMax[1].right = pRootOfTree;
            pRootOfTree.left = leftMinMax[1];
            head = leftMinMax[0];
            head.left = null;
        }
        if (pRootOfTree.right != null) {
            // 右递归
            TreeNode[] rightMinMax = traverse(pRootOfTree.right, pRootOfTree.right);
            rightMinMax[0].left = pRootOfTree;
            pRootOfTree.right = rightMinMax[0];
            tail = rightMinMax[1];
            tail.right = null;

        }


        if (returnHead)
            return head;

        else
            return tail;


    }

    // 中序遍历
    // 核心思想：将一个子树根据顺序建立双向链表的链接，然后将其最大和最小值返回
    // 父节点，将左子树的最大值设置为自己的左节点，然后将右子树的最小值设置为自己的右节点
    // 然后上上级返回左子树的最小节点和右子树的最大节点。
    private static TreeNode[]  traverse(TreeNode root, TreeNode parentBranch) {
        TreeNode[] minLeftMaxRight= {root,root};

        if (root.left == null && root.right == null){
            return minLeftMaxRight;
        }

        else {
            if (root.left != null){
                TreeNode[] minMax = traverse(root.left,root.left);
                minMax[0].left = parentBranch;
                minMax[1].right = root;
                root.left = minMax[1];
                minLeftMaxRight[0] = minMax[0];

            }
            if (root.right != null) {
                TreeNode[] minMax = traverse(root.right,root.right);
                minMax[0].left = root;
                root.right = minMax[0];
                minLeftMaxRight[1] = minMax[1];
            }

            return minLeftMaxRight;
        }


    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode node6 = root.left = new TreeNode(6);
        TreeNode node4 = node6.left = new TreeNode(4);
        TreeNode node8 = node6.right = new TreeNode(8);

        TreeNode node14 = root.right = new TreeNode(14);
        TreeNode node12 = node14.left = new TreeNode(12);
        TreeNode node16 = node14.right = new TreeNode(16);

        TreeNode head = convert(root,true);

        // 正向测试
        while(head != null){
            System.out.println(head.val);
            head = head.right;
        }

//        // 反向测试
//        TreeNode tail = convert(root,false);
//        // 正向测试
//        while(tail != null){
//            System.out.println(tail.val);
//            tail = tail.left;
//        }

    }

}
