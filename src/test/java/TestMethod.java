import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/27.
 */
public class TestMethod {

    private int n = 2345;

//    @Before
//    public void setN(){
//        n = 2345;
//    }

    @Test
    public void  getbit10(){
        ArrayList<Integer> arrayList = new ArrayList<>();

        int base = 1;

        while(n >= base){
            arrayList.add((n % (10* base)) / base);
            base *= 10;
        }
        System.out.println(Arrays.toString(arrayList.toArray()));
    }

    @Test
    public void testFor(){

        int count = 0;
        for (int i = 0; i< 10 ; ++i){
            count ++;

            if (i == 5) {
                i = 8;
            }
        }

        Assert.assertEquals(7,count);
    }

    @Test
    public void testIntString() {
        int n = 1234567;
        Integer nInteger = new Integer(n);
        System.out.println(nInteger.toString().charAt(2));
    }
}
