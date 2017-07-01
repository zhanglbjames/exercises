package test;

import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
public class TestSelect {
    private static boolean run = true;
    public static void main(String[] args) throws Exception{


        // 1. 打开SocketChannel
        SocketChannel socketChannel = SocketChannel.open();
        // 2. 尝试建立远程连接（并没有真正连接）
        socketChannel.connect(new InetSocketAddress("127.0.0.1",9001));
        // 3. 设置Channel为非阻塞状态
        socketChannel.configureBlocking(false);
        // 4. 打开一个选择器
        Selector selector = Selector.open();
        // 5. 将Channel和需要监听的IO事件注册到选择器上
        SelectionKey selectionKey = socketChannel.register(selector,SelectionKey.OP_READ);
        Object attachObj =new Object();

        selectionKey.attach(attachObj);
        Object getAttach = selectionKey.attachment();

        // 6. 循环阻塞直到有IO事件发生，并进行相应的处理
        while(run){
            // 6.1 获取准备好的通道数量
            int readyChannels = selector.select();
            if (readyChannels ==0) continue;
            // 6.2 获取已就绪的key集合
            Set selectKeys = selector.selectedKeys();
            Iterator keyItr = selectKeys.iterator();
            // 6.3 遍历就绪集合的发生的IO就绪事件
            while (keyItr.hasNext()){
                SelectionKey key = (SelectionKey) keyItr.next();

                Channel channel = key.channel();
                Selector selector1 = key.selector();

                if (key.isAcceptable()){
                    doAccept();
                }else if (key.isConnectable()){
                    doConnection();
                }else if (key.isReadable()){
                    doRead();
                }else if (key.isWritable()){
                    doWrite();
                }
                // 删除已经处理的已选择key
                keyItr.remove();
            }

        }
        // 7 关闭选择器和Channel
        selector.close(); // 先关selector
        socketChannel.close(); // 再关Channel
    }
    public static void doAccept(){
    }
    public static void doConnection(){
    }
    public static void doRead(){
    }
    public static void doWrite(){
    }
    public static void setRun(boolean flag) {
        run = flag;
    }
}
