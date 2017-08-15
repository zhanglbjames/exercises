package test;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/7/29.
 */
public class Test {
    public  int a = 0;
     public static void main(String[] args) {
         Test bClass = new B();
         System.out.println(bClass.a);
     }
}
 class B extends Test {
    public int a = 1;
 }



