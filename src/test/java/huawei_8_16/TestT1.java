package huawei_8_16;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/17.
 */
public class TestT1 {

    /**
     * 测试Pattern.split方法
     */
    @Test
    public void testPatternSplit() {

        String str = "{0x40, 0x11, 0x00, 0x00}";
        // 分割符为：逗号, {,}, 空白符
        String regex = "[,\\{\\}\\s]";
        Pattern pattern = Pattern.compile(regex);

        /*
        * 1. split 方法用于使用正则表达式中的字符分割待匹配的字符串
        *
        * 注意：如果分割符位于原字符串的起始位置，则分割的时候，会在起始位置上分割出一个
        * " "出来
        * */
        System.out.println("----------- split test -----------");
        String[] results = pattern.split(str);
        for (int i = 0; i < results.length; i++) {
            System.out.print(results[i]);
        }

        /*
        * 2. split方法的limit参数的意思是使用正则表达式的分割字符将原字符串分为limit个组
        * **/
        System.out.println("\n----------- split limit test -----------");
        String[] resultsLimit = pattern.split(str, 2);
        for (int i = 0; i < resultsLimit.length; i++) {
            System.out.print(resultsLimit[i]);
        }
    }


    /*
    * 1. 测试Matcher的find方法：尝试在目标字符串中查找下一个匹配的字串，需在循环中迭代
    *
    * 2. groupCount ：返回当前查找所获得的匹配组的数量，不包括整个整个正则表达式的匹配
    * 比如，表达式有两个子分组，则groupCount == 2
    *
    * 3. group(i):指的是用()包含的子分组，按照定义的顺序标识下标，当正则表达式中使用 |连接分组，
    * 那么有的分组匹配的字串可能为null
    *
    * 4. start(group):返回此子分组匹配的子串在原字符串中的起始位置(包含)
    *    end(group):返回此子分组匹配的子串在原字符串中的结束位置(不包含)
    * 即子分组匹配的字符串在原字符串的位置为 [start(i),end(i)),左闭右开
    * **/
    @Test
    public void testMatcherGroupFindStartEnd() {
        String str = "{0x40, 0x31, 0x20, 0x00}";
        String regex = "([A-Za-z0-9]+)(,)";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(str);

        // 对于在整个原字符串中，找到的下一个匹配的字串
        while (matcher.find()) {
            // 输出groupCount的数量
            System.out.println("groupCount : " + matcher.groupCount());

            // 0-输出整个匹配
            System.out.println("the substring of contains all group : " + matcher.group(0));
            System.out.println("group_0 start index : " + matcher.start(0) + " end :" + matcher.end(0));

            // 依次输出子分组的匹配结果
            // 如果子分组之间是通过 | 来连接的，则子分组的匹配结果有的为null
            for (int i = 1; i <= matcher.groupCount(); i++) {
                System.out.println("group_" + i + ":" + matcher.group(i));
                System.out.println("group_" + i + " start index : " + matcher.start(i) + " end :" + matcher.end(i));
            }
        }
    }


    /**
     * 测试Matcher的匹配替换以及追加的方法
     *
     * 1. matcher.replaceAll方法 ：替换在原字符串中所有被正则表达式匹配的字串，并返回替换之后的结果
     * 2. matcher.replaceFirst方法 ：替换在原字符串中第一个被正则表达式匹配的字串，并返回替换之后的结果
     * 3. matcher.appendReplacement方法 ： 将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前
     *    到上次匹配子串之后的字符串段添加到一个StringBuffer对象里（需while(matcher.find())进行配合迭代）
     * 4. matcher.appendTail(StringBuffer sb) 方法则将最后一次匹配工作后剩余的字符串添加到一个StringBuffer对象里。
     *
     * 3和4的结合能够实现将原字符串中的某些字串替换指定字符，并返回完成替换之后的结果
     */
    @Test
    public void testMatcherReplaceAppend() {
        String str = "{0x40, 0x31, 0x20, 0x00}";
        String regex = "([0-9A-Za-z]+)";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(str);

        // replaceAll
        System.out.println("----------- replace all test ----------");
        String replacedAllStr = matcher.replaceAll("replace");
        System.out.println("replaced : " + replacedAllStr);
        //matcher.reset(str); // 重置被matcher的字符串
        matcher.reset(); // 重置matcher，以实现对原字符串重新搜索

        // replaceFirst
        System.out.println("------------ replace first test ---------");
        String replacedFirstStr = matcher.replaceFirst("replace");
        System.out.println("replaced first : " + replacedFirstStr);
        matcher.reset();

        // appendReplacement
        System.out.println("------------- appendReplacement test ------------");
        StringBuffer appendRepStr = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(appendRepStr,"0xffff");
        }
        System.out.println(appendRepStr);

        // 最后调用appendTail将匹配剩余的字符串添加都StringBuffer的末尾
        // 注意这时要实现完整的功能：将所有匹配的内容替换并添加到appendRepStr中，剩余未匹配的继续添加到
        // appendRepStr中，相当于对原字符串进行全部的替换
        // 此时要保证，在遍历所有匹配的字串后调用appendTail方法

        System.out.println("------------ appendTail test ---------------");
        matcher.appendTail(appendRepStr);

        System.out.println(appendRepStr);
    }

    @Test
    public void testSynax(){
        String str = "abc-dgr";
        String regex = "([a-c-r])";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            for (int i = 0; i < matcher.groupCount() + 1; i++) {
                System.out.println(matcher.group(i));
            }
        }
    }
}
