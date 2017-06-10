package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/10.
 */

/**
 * <p>Description</p>
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
 * 则重建二叉树并返回。
 */
public class Exercise_4 {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in){
        if (pre == null || in == null || pre.length != in.length){
            return null;
        }
        return doConstruct(pre,in,0,pre.length-1,0,in.length-1);
    }
    private static TreeNode doConstruct(int[] pre, int[] in,int preStartIndex, int preEndIndex, int inStartIndex, int inEndIndex){

        if (preEndIndex-preStartIndex != inEndIndex-inStartIndex){
            throw new IllegalArgumentException("pre is not consist with in");
        }
        // 当只有一个节点时返回
        if (inEndIndex - inStartIndex == 0){
            return new TreeNode(pre[preStartIndex]);
        }

        // 前序遍历的第一个元素即为root
        int childRootVal = pre[preStartIndex];

        // 获取root在中序遍历的位置
        int rootInMiddleIndex = 0;
        for (int i = inStartIndex; i <= inEndIndex; i++) {
            if (in[i] == childRootVal){
                rootInMiddleIndex = i;
                break;
            }
        }
        // 构建子树根节点
        TreeNode childRoot = new TreeNode(childRootVal);


        // 根据中序遍历判断是否有左右子树
        boolean hasLeft = true, hasRight = true;
        int leftInStartIndex = inStartIndex;
        if (leftInStartIndex == rootInMiddleIndex){
            hasLeft = false;
        }
        int rightInStartIndex = rootInMiddleIndex + 1;
        if (rightInStartIndex > inEndIndex){
            hasRight = false;
        }

        // 左子树递归，找下标，当有左子树时从前开始计算，能保证不溢出
        if (hasLeft){
            int leftPreStartIndex = preStartIndex + 1;
            int leftPreEndIndex = preStartIndex + rootInMiddleIndex - leftInStartIndex;
            int leftInEndIndex = rootInMiddleIndex-1;
            childRoot.left =
                    doConstruct(pre,in,leftPreStartIndex,leftPreEndIndex,leftInStartIndex,leftInEndIndex);
        }else{
            childRoot.left = null;
        }
        // 右子树递归，找下标，当有右子树时从后开始计算，能保证不溢出
        if (hasRight){
            int rightPreStartIndex = preEndIndex - (inEndIndex -rootInMiddleIndex) + 1;
            int rightPreEndIndex = preEndIndex;
            int rightInEndIndex = inEndIndex;
            childRoot.right =
                    doConstruct(pre,in,rightPreStartIndex,rightPreEndIndex,rightInStartIndex,rightInEndIndex);
        }else{
            childRoot.right = null;
        }

        return childRoot;
    }
    private static void print(int val){
        System.out.print(val);
    }

    public static void preOrderTraversal(TreeNode root){
        if (root == null){
            return;
        }
        print(root.val);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }
    public static void middleOrderTraversal(TreeNode root){
        if (root == null){
            return;
        }
        middleOrderTraversal(root.left);
        print(root.val);
        middleOrderTraversal(root.right);
    }
    public static void main(String[] args){
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] in =  {4,7,2,1,5,3,8,6};
        TreeNode root = reConstructBinaryTree(pre,in);

        preOrderTraversal(root);
        System.out.print("\n");

        middleOrderTraversal(root);
        System.out.print("\n");
    }

}
