package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/12.
 */
/**
 * <p>Description</p>
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 */
public class Exercise_10 {
    public static int rectCover(int target){
        return doRectRecover(target);
    }
    private static int doRectRecover(int surplusN){
        if (surplusN <= 2){
            return surplusN >=0 ? surplusN : 0;
        }
        return doRectRecover(surplusN -1) + doRectRecover(surplusN -2);
    }
    public static void main(String[] args){
        System.out.println(rectCover(1));
    }
}
