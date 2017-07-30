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
    public int numberOfBetween1AndN(int n) {

        if (n < 1) return 0;
        int len = getLenOfNum(n);
        if (len == 1) return 1;
        int tmp = (int) Math.pow(10, len - 1);
        int first = n / tmp;
        int firstOneNum = first == 1 ? n % tmp + 1 : tmp;
        int otherOneNUm = first * (len - 1) * (tmp / 10);
        return firstOneNum + otherOneNUm + numberOfBetween1AndN(n % tmp);

    }

    private int getLenOfNum(int n) {
        int len = 0;
        while (n != 0) {
            len++;
            n /= 10;
        }
        return len;
    }
}
