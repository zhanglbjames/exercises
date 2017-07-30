import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import swordOffer.Exercise_32;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/30.
 */
public class Test32 {
    private Exercise_32 test;
    private int[] numbers;
    private String result;

    @Before
    public void setTest() {
        test = new Exercise_32();
        numbers = new int[]{3,32,321,40,31,29};
        result = "293132132340";
    }

    @Test
    public void test32() {
        String result = test.printMinNumber(numbers);
        Assert.assertEquals(this.result,result);
    }

    @Test
    public void testCompare() {

        Assert.assertEquals(false,test.comparaTwo(31,321));
    }
}
