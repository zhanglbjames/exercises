package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/21.
 */

import java.util.ArrayList;
import java.util.Iterator;

/**
 * <p>Description</p>
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 */
public class Exercise_22 {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root){
        ArrayList<Integer> result = new ArrayList<Integer>();
        ArrayList<TreeNode> tmp = new ArrayList<TreeNode>();
        if (root == null){
            return result;
        }
        else{
            tmp.add(root);
            doTravel(result,tmp);
        }
        return result;
    }
    private void doTravel(ArrayList<Integer> result, ArrayList<TreeNode> tmp){
        if (tmp.size() == 0){
            return;
        }
        ArrayList<TreeNode> newTmp = new ArrayList<TreeNode>();
        Iterator<TreeNode> iterator = tmp.iterator();
        while(iterator.hasNext()){
            TreeNode node = iterator.next();
            // 添加元素都输出队列
            result.add(node.val);

            if (node.left != null){
                newTmp.add(node.left);
            }
            if (node.right != null){
                newTmp.add(node.right);
            }
        }
        doTravel(result,tmp = newTmp);

    }
    public static void main(String[] args){
        Exercise_22 e = new Exercise_22();

    }
}
