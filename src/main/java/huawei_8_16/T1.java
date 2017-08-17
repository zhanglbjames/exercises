package huawei_8_16;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/16.
 */
public class T1 {
    public static void main(String[] args){
        String str2 = "{0x00,0x01},";
        String str1 = "{0x40,0x11,0x00}";

        String patternStr = "([A-Za-z0-9]+)";
        Pattern pattern = Pattern.compile(patternStr);

        Matcher matcher = pattern.matcher(str1);

        /**
         * find方法是一个迭代过程，所以使用while来迭代
         *
         * 对于每一个迭代过程，通过在while中调用find方法，
         * 每一个迭代相当于整个匹配模式在整个输入中的匹配，会有多个所以通过while迭代
         *
         * 整个匹配模式包含多个分组 比如 "([a-z])zhangp0([0-9])"
         * matcher.group(i)
         * i == 0 表示的是满足整个匹配模式的全部内容
         * i == 1 表示的是满足整个匹配模式的第一个分组（即第一个括号里面的内容）
         * i == n 等同
         * */
        while (matcher.find()){
            System.out.println(matcher.group(0));
        }


    }
}
