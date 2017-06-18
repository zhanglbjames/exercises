package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/12.
 */
/**
 * <p>Description</p>
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
public class Exercise_8 {
    public static int jumpFloor(int target) {
        return doJumpFloor(target);
    }
    private static int doJumpFloor(int surplusFloor){
        // n=2:2种 ，n=1:1种 ， n=0:0种， n < 0:0种
        if (surplusFloor <=2 ){
            return surplusFloor >= 0 ? surplusFloor : 0;
        }
        return doJumpFloor(surplusFloor -1) + doJumpFloor(surplusFloor -2);
    }
    public static void main(String[] args){
        System.out.println(jumpFloor(3));
    }
}
