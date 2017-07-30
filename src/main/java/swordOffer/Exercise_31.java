package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/24.
 */

/**
 * <p>Description</p>
 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
 * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,
 * 但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,
 * 可以很快的求出任意非负整数区间中1出现的次数。
 */
public class Exercise_31 {

    public int numberOf1BetweenAndN(int n){
        int max = findMax(n);
        int maxBase = max /10;

        int countOne = 0;
        for (int i = 0 ; i< maxBase; i *= 10){

        }
    }
    private int findMax(int n) {
        int base = 10;
        while (n >= base){
            base *= 10;
        }
        return base;
    }

}
