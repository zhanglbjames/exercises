package test;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class TestProactorClient {
    private static AsynchronousSocketChannel asc;

    private static void log(String message) {
        System.out.println("Info : " + message);
    }

    public static void initClient() throws Exception {
        log("init client...");
        asc = AsynchronousSocketChannel.open();
    }

    public static void startClient() throws Exception {
        log("start client...");

        // 监听到完成连接建立事件--则发送数据
        asc.connect(new InetSocketAddress("127.0.0.1", 9000), null,
                new CompletionHandler<Void, Object>() {
                    @Override
                    public void completed(Void result, Object attachment) {
                        try {
                            asc.write(ByteBuffer.wrap("this is a test".getBytes())).get();
                            log("send data to server");

                        } catch (Exception e) {
                            log("send exception! cause of " + e.getMessage());
                        }
                    }

                    @Override
                    public void failed(Throwable e, Object attachment) {
                        log("send failed! cause of " + e.getMessage());

                    }
                });

        // 监听到从远程socket读数据完成事件--则将读到的数据放到attachment
        Message receiveMessage = new Message();
        final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        asc.read(byteBuffer, receiveMessage,
                new CompletionHandler<Integer, Message>() {
                    public void completed(Integer result, Message attachment) {
                        if (result > 0) {
                            attachment.append("read " + result + " bytes :");
                            attachment.append(new String(byteBuffer.array()));
                        }
                    }

                    public void failed(Throwable e, Message attachment) {
                        log(e.getMessage());
                    }
                });

        // 当没有获取到信息则干其他的事情
        while (receiveMessage.getMessage() == null) {
            log("client is free");
            // 防止过度打印log，休眠0.5秒
            Thread.sleep(500);
        }
        log("get response :" + receiveMessage.getMessage());
    }

    public static void close() throws Exception {
        log("close client");
        asc.close();
    }

    public static void main(String[] args) throws Exception {
        initClient();
        startClient();
        close();
    }
}
