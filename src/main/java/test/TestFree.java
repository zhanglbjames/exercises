package test;

import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;
import java.nio.ByteBuffer;

public class TestFree {
    public static void main(String[] args) {
        /*
         * DirectByteBuffer实现了DirectBuffer接口
         * DirectByteBuffer是default访问权限，但是DirectBuffer是public
         */

        // 获取DirectBuffer对象
        DirectBuffer directBuffer = (DirectBuffer) ByteBuffer.allocateDirect(1024);
        // 获取Cleaner对象
        Cleaner cleaner = directBuffer.cleaner();
        // 调用clean方法释放直接内存
        directBuffer.cleaner().clean();
    }
}
