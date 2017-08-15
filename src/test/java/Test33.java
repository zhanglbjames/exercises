import junit.framework.Assert;
import org.junit.Test;
import swordOffer.Exercise_33;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/30.
 */
public class Test33 {

    @Test
    public void test33() {
        Exercise_33 test = new Exercise_33();
        Assert.assertEquals(12,test.getUglyNumber(10));
    }
}
