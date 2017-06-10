package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/10.
 */

/**
 * <p>Description</p>
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的
 * 字符串为We%20Are%20Happy。
 */
public class Exercise_2 {
    public static String replaceSpace(StringBuffer str){
        if(str == null){
            return null;
        }
        // 局部变量必需要初始化（对象域可以不用显示初始化）
        int spaceCount = 0;
        int length = str.length();

        // 注意：char类型数据不用判断null，而且用单引号
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == ' '){
                ++ spaceCount;
            }
        }
        int newLength = length + 2*spaceCount;
        if (newLength <= length){
            return str.toString();
        }

        str.setLength(newLength);
        for (int i = newLength -1,j = length-1; j >= 0; --j) {
            if (str.charAt(j) == ' '){
                str.setCharAt(i--,'0');
                str.setCharAt(i--,'2');
                str.setCharAt(i--,'%');
            }else{
                str.setCharAt(i--,str.charAt(j));
            }
        }
        return str.toString();
    }
    public static void main(String[] args){

        StringBuffer stringBuffer = new StringBuffer(" zhang shuai  shuai ");
        System.out.println(replaceSpace(stringBuffer));
    }
}
