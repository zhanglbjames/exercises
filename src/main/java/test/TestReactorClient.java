package test;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class TestReactorClient {
    private static SocketChannel socketChannel;
    private static Selector selector;
    private static boolean run = false;

    public static void startClient() throws Exception {
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        // 非阻塞模式，立即返回false，表示正在建立连接
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 9000));
        selector = Selector.open();
        // 注册连接操作
        SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_CONNECT);
        run = true;
        while (run) {
            // 1. 发生阻塞
            int numKey = selector.select();
            if (numKey > 0) {
                Set<SelectionKey> keySet = selector.selectedKeys();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                for (SelectionKey key : keySet) {
                    // 连接事件
                    if (key.isConnectable()) {
                        // 获取已经连接就绪的通道，并设置为非阻塞模式，并对这个通道读事件进行监听
                        // 并完成连接的建立
                        SocketChannel socketChannel1 = (SocketChannel) key.channel();
                        socketChannel1.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        socketChannel1.finishConnect();
                    }
                    // 读就绪事件
                    else if (key.isReadable()) {
                        byteBuffer.clear();
                        SocketChannel socketChannel1 = (SocketChannel) key.channel();
                        int readCount = 0;
                        try {
                            int get = 0;
                            try {
                                // 2. 阻塞
                                while ((get = socketChannel1.read(byteBuffer)) > 0) {
                                    readCount += get;
                                }
                            } finally {
                                byteBuffer.flip();
                            }
                        } finally {
                            byteBuffer.clear();
                        }
                    }
                    // 写就绪事件，则取消写注册
                    else if (key.isWritable()) {
                        key.interestOps(key.interestOps() & (~SelectionKey.OP_WRITE));
                        SocketChannel socketChannel1 = (SocketChannel) key.channel();
                        // 3. 阻塞
                        int writtenNum = socketChannel1.write(byteBuffer);
                    }
                }
                //一次性清除所有已选择key
                selector.selectedKeys().clear();
            }
        }
    }

    public static void closeClient() throws Exception {
        run = false;
        selector.close();
        socketChannel.close();
    }
}
