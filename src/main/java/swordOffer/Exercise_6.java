package swordOffer;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/6/11.
 */
/**
 * <p>Description</p>
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class Exercise_6 {
    public static int minNumberInRotateArray(int[] array){
        // 为null或者empty
        if (array == null || array.length == 0){
            return 0;
        }
        // 全重复元素以及已排序检测
        int length = array.length;
        if (array[0] <= array[length-1]){ // 全重复，或者已经排序
            return array[0];
        }
        int rightMax = array[length-1];
        int leftMin = array[0];
        return doBinarySearch(array, rightMax, leftMin,0,length-1);
    }
    private static int doBinarySearch(int[] array, int rightMax, int leftMin,int startIndex, int EndIndex){

        int length = EndIndex - startIndex + 1;

        // 当只有一个或两个元素时直接返回最小的，这是递归结束条件
        // 只有一个元素
        if (length == 1){
            return array[startIndex];
        }
        // 只有两个元素
        if (length == 2){
            return array[startIndex] > array[startIndex + 1] ? array[startIndex + 1] : array[startIndex];
        }

        // 三个及以上

        // 中间位置
        int middle = startIndex + (length >>> 1); // 当长度大于等于3，能保证右移一位是有效果的

        //------------------------middle位于左最大或右最小上----------------------
        // 如果中间元素正好大于右临的元素且大于等于rightMax，判定右临的元素即为最小的元素
        if (array[middle] > array[middle + 1] && array[middle] >= rightMax){
            return array[middle + 1];
        }
        // 如果中间元素正好小于左临的元素且小于等于leftMin，判定中间元素即为最小的元素
        if (array[middle] < array[middle-1] && array[middle] <= leftMin){
            return array[middle];
        }
        //------------------------middle不位于边界上-------------------------------
        // 大于右子数组的最大值，则右半切分
        if(array[middle] > rightMax){
            return  doBinarySearch(array, rightMax, leftMin,middle+1, EndIndex);
        }
        // 小于等于则进行左半切分，（当含有重复元素时，最小值取为最靠近左边的重复值)
        else{
            return doBinarySearch(array, rightMax, leftMin, startIndex,middle-1);
        }
    }

    // java 是值传递，如果传递的是基本类型数据则是值传递,
    // 引用类型也是值传递，只是传递的是引用本身
    private static void hh(){
        int min = 0;
        gg(min);
        System.out.println(min);// 0
    }
    private static void gg(int min){
        min =1;
    }
    public static void main(String[] args){
        //hh();
        int[] rotate1 = {3,4,5,1,2};
        int[] rotate2 = {7,8,9,3,4,5,6};
        int[] rotate3 = {6501,6828,6963,7036,7422,7674,8146,8468,8704,8717,9170,9359,9719,9895,9896,9913,9962,154,293,334,492,1323,1479,1539,1727,1870,1943,2383,2392,2996,3282,3812,3903,4465,4605,4665,4772,4828,5142,5437,5448,5668,5706,5725,6300,6335};
        int[] rotate4 = {1,1,1,1,1,1,1};
        int[] rotate5 = {6873,6874,6875,6875,6875,6875,6878,6880,6881,6882,6884,6885,6885,6887,6889,6891,6892,6893,6893,6894,6895,6897,6897,6900,6900,6900,6901,6905,6905,6906,6906,6907,6908,6908,6912,6912,6914,6916,6917,6919,6920,6921,6922,6922,6926,6932,6936,6940,6941,6942,6943,6943,6944,6946,6947,6951,6951,6955,6956,6957,6957,6959,6959,6961,6964,6972,6974,6975,6978,6978,6978,6980,6980,6981,6981,6982,6983,6983,6983,6984,6984,6984,6987,6988,6988,6988,6991,6992,6994,6996,6998,6998,6998,6999,6999,7000,7006,7007,7008,7008,7009,7011,7011,7011,7014,7014,7015,7016,7016,7017,7018,7019,7023,7024,7026,7027,7027,7029,7029,7029,7031,7032,7033,7038,7038,7042,7042,7044,7046,7046,7046,7046,7048,7048,7049,7051,7051,7056,7056,7056,7057,7060,7060,7060,7061,7062,7062,7065,7065,7065,7066,7066,7067,7067,7067,7068,7069,7069,7070,7071,7075,7076,7078,7079,7079,7081,7081,7085,7089,7089,7089,7089,7090,7091,7093,7094,7095,7097,7100,7101,7101,7102,7103,7104,7106,7109,7111,7115,7123,7123,7126,7126,7130,7130,7132,7132,7139,7139,7140,7140,7141,7144,7145,7146,7146};

        int[] rotate6 = {7,8,8,8,8,9,9,10,11,3,4,5,6};
        int[] rotate7 = {3,4,5,6,7,8};
        System.out.println(minNumberInRotateArray(rotate1));
        System.out.println(minNumberInRotateArray(rotate2));
        System.out.println(minNumberInRotateArray(rotate3));
        System.out.println(minNumberInRotateArray(rotate4));
        System.out.println(minNumberInRotateArray(rotate5));
        System.out.println(minNumberInRotateArray(rotate6));
        System.out.println(minNumberInRotateArray(rotate7));
    }
}
