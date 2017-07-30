import org.junit.Ignore;
import org.junit.Test;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/27.
 */
public class TestJunit {
    @Ignore
    @Test
    public void testJunit() {
        System.out.println("zhang shuai shuai");
    }

    @Ignore
    @Test(expected = IllegalStateException.class)
    public void throwExceptionTest() {
        throw new IllegalStateException("Test Exception throwable");
    }

}
