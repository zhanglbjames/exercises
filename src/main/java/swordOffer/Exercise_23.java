package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/21.
 */
/**
 * <p>Description</p>
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */
public class Exercise_23 {
    public boolean VerifySequenceOfBST(int[] sequence){
        if (sequence == null || sequence.length == 0){
            return false;
        }
        return doTravel(sequence,0,sequence.length-1);

    }
    // 寻找root，左右子树，然后确保左子树都小于root，右子树都大于root
    private boolean doTravel(int[] sequence, int start, int end){
        // 递归结束条件：少于等于1个元素
        if (end - start < 1){
            return true;
        }
        //----------至少含有2个节点----------
        boolean hasLeft = false, hasRight = false;
        int rootValue = sequence[end];
        if (sequence[start] < rootValue)
            hasLeft = true;
        if (sequence[end-1] > rootValue){
            hasRight = true;
        }

        // ----------------确保范围内是搜索二叉树-----------
        int edge = -1;
        for (int i = start; i < end; ++i){
            if (sequence[i] > rootValue){
                edge = i;
                break;
            }
        }
        // 当 edge == -1 则表明只含有左子树 等价于hasLeft == true hasRight != true
        if (edge != -1){ //含有右子树
            for (int i = edge; i < end ; i++) {
                if ( sequence[i] < rootValue){
                    return false;
                }
            }
        }
        // ----------------递归判断--------------------
        // 有左子树
        if (hasLeft){
            // 有右子树
            // 则至少有3个元素
            if (hasRight){
                return doTravel(sequence,start,edge-1) && doTravel(sequence,edge,end);
            }
            // 没有右子树
            else return doTravel(sequence,start,end-1);
        }
        // 没有左子树则一定存在右子树
        else {
            return doTravel(sequence,start,end-1);
        }

    }
    public static void main(String[] args){
        Exercise_23 e = new Exercise_23();
        int[] a = {4,6,7,5};
        int[] b = {7,4,6,5};
        System.out.println(e.VerifySequenceOfBST(a));
        System.out.println(e.VerifySequenceOfBST(b));
    }
}
