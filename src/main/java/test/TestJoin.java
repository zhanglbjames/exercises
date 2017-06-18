package test;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/11.
 */
public class TestJoin {

    public static void main(String[] args){

        Runnable task = new Runnable() {
            public void run() {
                try {
                    System.out.println("A is going to sleep 10s");
                    Thread.currentThread().sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println("A is interrupted");
                } finally{
                    System.out.println("A is running");
                }

            }
        };
        Thread A = new Thread(task,"Thread A");
        A.start();
        try{
            A.join();
        }catch(InterruptedException e){
            System.out.println("B is interrupted");
        }
        System.out.println("B is keep running from A join");

    }
}
