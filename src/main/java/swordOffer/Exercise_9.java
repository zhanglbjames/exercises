package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/12.
 */
public class Exercise_9 {
    public static int jumpFloorII(int target){
        return  doJumpFloorII(target);
//        return doJumpFloorIIN(target);
    }
    private static int doJumpFloorII(int surplusFloor){
        if (surplusFloor <= 2){
            return surplusFloor >= 0 ? surplusFloor : 0;
        }
        // 注意一次可以跳 n 阶，所以初始值为1，即一次跳 n 阶的一种跳法
        int result = 1;

        for (int i = 1; i < surplusFloor ; ++i) { // 从 n-1 ~ 1
            result += doJumpFloorII(surplusFloor - i);
        }
        return  result;
    }
    private static int doJumpFloorIIN(int target){
        return (int) Math.pow(2,target-1);
    }
    public static void main(String[] args){
        System.out.println(jumpFloorII(9));
    }

}
