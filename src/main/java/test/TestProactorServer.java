package test;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestProactorServer {

    private static AsynchronousServerSocketChannel assc;
    private static ExecutorService executorService;
    private static AsynchronousChannelGroup channelGroup;

    private static void log(String info) {
        System.out.println("Info : " + info);
    }

    public static void initServer() throws Exception {
        log("initialing Server ...");
        // 初始化线程池
        executorService = Executors.newCachedThreadPool();
        // 将线程池绑定到通道组上
        channelGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
        // 以指定的通道组开启异步ServerSocket通道
        assc = AsynchronousServerSocketChannel.open(channelGroup);
        // 监听端口
        assc.bind(new InetSocketAddress(9000));
        log("init completed");
    }

    public static void startServer() throws Exception {
        log("start server ...");

        Message receiveMessage = new Message();
        // 监听接收远程Socket数据完成事件--则获取接收到的数据然后发送响应数据
        assc.accept(receiveMessage, new CompletionHandler<AsynchronousSocketChannel, Message>() {
            final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            public void completed(AsynchronousSocketChannel result, Message attachment) {
                log("waiting ...");
                int readNum = 0;
                try {
                    byteBuffer.clear();
                    // 1. 接收数据
                    // read返回future对象，这里直接调用Future.get()方法阻塞，直到读完成
                    // 也可以做其他的事情，做完之后再get等待完成
                    readNum = result.read(byteBuffer).get();
                    if (readNum <= 0)
                        log("read 0 byte stream or stream is end");
                    else {
                        // 以String格式添加到receiveMessage
                        attachment.append(new String(byteBuffer.array()));
                        log("read some byte to receiveMessage");
                    }

                    // 2. 发送响应数据
                    result.write(ByteBuffer.wrap("server receive client message".getBytes()));
                } catch (InterruptedException | ExecutionException e) {
                    log("read exception! cause of" + e.getMessage());
                } finally {
                    try {
                        // 关闭本次连接通道
                        result.close();
                        // 递归监听下一次accept事件完成
                        //assc.accept(attachment, this);
                    } catch (Exception e) {
                        log("close connect failed! cause of " + e.getMessage());
                    }
                }


                log("completed ...");

            }

            public void failed(Throwable exc, Message attachment) {
                log("failed read! cause of " + exc.getMessage());
            }
        });
        // 当没有消息时做其他的事
        while (receiveMessage.getMessage() == null) {
            // do anything you want to do
            log("server is free");
            // 防止过度打印log，休眠0.5秒
            Thread.sleep(500);
        }
        // 有消息则获取消息
        log("get message:" + receiveMessage.getMessage());
    }

    // 平滑关闭
    public static void close() throws Exception {
        log("close server");
        // 先关闭socket异步通道
        assc.close();
        // 在关闭绑定在socket上的通道组
        channelGroup.awaitTermination(10, TimeUnit.SECONDS);
        if (!channelGroup.isTerminated()) {
            channelGroup.shutdownNow();
        }
        // 最后关闭线程池
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        if (!executorService.isTerminated()) {
            executorService.shutdownNow();
        }
        // 如果还有其他资源则之后关闭
        log("bye!");
    }

    public static void main(String[] args) throws Exception {
        initServer();
        startServer();
        close();
    }
}

class Message {
    private StringBuilder message;

    public void setMessage(String message) {
        this.message = new StringBuilder(message);
    }

    public String getMessage() {
        return message == null ? null : message.toString();
    }

    public void append(String appendStr) {
        if (message == null)
            message = new StringBuilder(appendStr);
        else
            message.append(appendStr);
    }
}
