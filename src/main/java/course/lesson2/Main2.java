package course.lesson2;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/1.
 */
import java.util.Arrays;
import java.util.Scanner;
import java.math.BigInteger;
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        if(len<3){
            System.out.println(0);
            return;
        }
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE, min3 = Integer.MAX_VALUE;
        int[] nums = new int[4];
        for(int i=0; i<len; i++){
            int num = scanner.nextInt();

            nums[0]=max1;nums[1]=max2;nums[2]=max3;nums[3]=num;
            Arrays.sort(nums);
            max1=nums[3];max2=nums[2];max3=nums[1];

            nums[0]=min1;nums[1]=min2;nums[2]=min3;nums[3]=num;
            Arrays.sort(nums);
            min1=nums[0];min2=nums[1];min3=nums[2];
        }
        BigInteger max11 = new BigInteger(""+max1), max22 = new BigInteger(""+max2), max33 = new BigInteger(""+max3);
        BigInteger min11 = new BigInteger(""+min1), min22 = new BigInteger(""+min2), min33 = new BigInteger(""+min3);
        BigInteger answer =(max11.multiply(max22).multiply(max33).compareTo(min11.multiply(min22).multiply(max11)))>=0 ? max11.multiply(max22).multiply(max33) : min11.multiply(min22).multiply(max11);
        System.out.println(answer);
    }
}

