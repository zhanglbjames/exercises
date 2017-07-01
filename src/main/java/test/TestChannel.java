package test;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class TestChannel {
    public static void main(String[] args) throws Exception{
        // 新建随机访问文件流，以读写权限打开
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\test.data","rw");

        // 获取Channel
        FileChannel channel = randomAccessFile.getChannel();
        // 将文件映射到内存缓冲区内
        MappedByteBuffer mapBuffer =
                // 设置映射模式为可读写，设置映射的文件范围为从文件开始到文件结束
                channel.map(FileChannel.MapMode.READ_WRITE,0,randomAccessFile.length());
        // 打印文件中的内容
        while (mapBuffer.hasRemaining()){
            System.out.println((char)mapBuffer.get());
        }
        // 在文件开头添加内容
        mapBuffer.put(0,(byte)48);
        // 关闭文件流
        randomAccessFile.close();
    }
}
