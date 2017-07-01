package test;

/**
 * Created by zhanglbjames@163.com on 2017/6/29.
 */
import sun.nio.ch.DirectBuffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.LongBuffer;
import java.nio.channels.FileChannel;

public class TestBuffer {
    public static void freeTest() {
        //
        DirectBuffer buffer = (DirectBuffer)ByteBuffer.allocate(1024);


    }
    public static void main(String[] args)throws Exception{
        // 1. 获取文件输入输出流
        FileInputStream fis = new FileInputStream("D:\\test.data");
        FileOutputStream fos = new FileOutputStream("D:\\test_copy.data");

        // 2. 获取流对应的channel
        FileChannel readChannel = fis.getChannel();// 读文件通道
        FileChannel writeChannel = fos.getChannel();// 写文件通道

        // 3. 分配读数据缓冲区

        // 分配 1024 个字节的缓冲区域
        ByteBuffer buffer = ByteBuffer.allocate(1024);// 静态方法
        // 分配 2048 个long类型的缓冲区域

        // 分配Byte直接内存缓冲区
        ByteBuffer buffer1 = ByteBuffer.allocateDirect(48);
        if (!buffer1.isDirect()){
            // 如果堆外分配失败则在堆上分配
            buffer1 = ByteBuffer.allocate(48);
        }
        // 转换为Char类型缓冲区类操作访问
        CharBuffer charBuffer = buffer1.asCharBuffer();


        // 4. 将输入通道内的数据写入到读数据缓冲区，然后将缓冲区的数据写入到输出通道
        while (true) {
            // 4.1 每次写完数据都要清空缓冲区
            buffer.clear();
            // 4.2 将数据读到缓冲区当中,并获取读取到的字节数

            // 从Channel中读数据到Buffer
            int num = readChannel.read(buffer);
            // 调用Buffer.put方法，放入数据
            buffer.put((byte)111);

            // 4.3 判断是否输入通道应完成输出,
            if (num == -1){
                break;
            }
            // 4.4 缓冲区位置读写转换
            buffer.flip();
            // 4.5 写缓冲区到输出通道

            // 将Buffer数据读出到Channel
            writeChannel.write(buffer);
            // Buffer.get方法获取数据
            byte bufferByte = buffer.get();
        }
        // 5 关闭所有channel通道以及输入输出流
        readChannel.close();
        writeChannel.close();
        fis.close();
        fos.close();
    }
}
