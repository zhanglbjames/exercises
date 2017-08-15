package course.lesson2;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/1.
 */
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            while(scanner.hasNext()){
                int n = scanner.nextInt();
                int[] h = new int[n];
                for(int i = 0;i < n;i++){
                    h[i]=scanner.nextInt();
                }
                int m=scanner.nextInt();
                int[] w =new int[m];
                for(int i=0;i<m;i++){
                    w[i]=scanner.nextInt();
                }

                Main m1 = new Main();
                int result = m1.findMatch(n, h, m, w);
                System.out.println(result);
            }
        }



    }

    public int findMatch(int n,int[] h,int m,int[] w){
        Arrays.sort(h);
        Arrays.sort(w);

        int len1 = h.length;
        int len2 = w.length;
        int count = 0;

        for(int i=0,j=0; i<len1 && j<len2;){
            if(h[i] <= w[j]){
                count ++;
                i++;
                j++;
            }else {
                j++;
            }
        }
        return count;
    }
}
