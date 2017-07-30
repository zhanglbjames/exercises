import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import swordOffer.Exercise_34;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/30.
 */
public class Test34 {

    private String str;

    @Before
    public void setStr() {
        str = "abjahgagnakjgnagagagaaiogrgnaga abanbabnaananbabnabnazbnakbf";
    }

    @Test
    public void test34() {
        Exercise_34 test = new Exercise_34();
        Assert.assertEquals(4,test.firstNotRepeatingChar(str));
    }
}
