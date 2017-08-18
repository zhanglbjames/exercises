package test;

import java.util.*;

/**
 * @author zhanglbjames@163.com
 * @version Created on 2017/8/18.
 */

/**
 * TreeMap总结：
 * 1. TreeMap默认是根据Key来比较来排序的
 *
 * 2. TreeMap的构造方法允许使用指定的比较器来比较
 *
 * 3. 可以实现通过比较Value来排序，通过指定比较器来实现，
 *    注意比较器的compare方法接收的两个参数都是Key，必须通过Key来获取对应的Value来比较
 *    才能实现按照Value来排序，否则还是按照key来排序的。
 *
 * 4. TreeMap底层是红黑树来实现的，红黑树不允许重复的比较关键字，
 *    所以如果比较器（如果没有指定比较器，则默认使用Key的自然顺序或者Key实现的比较接口方法来比较）
 *    的比较结果为0，即比较关键字相等，则将会发生覆盖的情况，具体如下：
 *      1. 如果按照Key来排序，则相同的Key的Value值将会发生覆盖，即value值等于最近put方法的指定的value值；
 *      2. 如果按照Value来排序，则相同的Value的key将会发生覆盖，即key值等于最近put方法的指定的key值；
 *
 * */
public class TestTreeMap {
    public static void main(String[] args){
        // 创建hashmap
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        hashMap.put(1,5);
        hashMap.put(2,4);
        hashMap.put(3,3);
        hashMap.put(4,2);
        hashMap.put(5,1);
        hashMap.put(7,1);

        // 创建Value比较器
        ValueComparator valueComparator = new ValueComparator(hashMap);
        // 创建TreeMap
        TreeMap<Integer,Integer> treeMap = new TreeMap<>(valueComparator);
        // 将HashMap中所有数据放入 TreeMap中

        System.out.println(treeMap.put(1,5));
        System.out.println(treeMap.put(2,4));
        System.out.println(treeMap.put(3,3));
        System.out.println(treeMap.put(4,2));
        System.out.println(treeMap.put(5,1));
        System.out.println(treeMap.put(7,1));
        //System.out.println(treeMap.put(1,5));

//        treeMap.put(2,4);
//        treeMap.put(3,3);
//        treeMap.put(4,2);
//        treeMap.put(5,1);
//        treeMap.put(7,1);

        //treeMap.putAll(hashMap);

        // 迭代TreeMap的结果
        Iterator<Map.Entry<Integer,Integer>> iterator = treeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer,Integer> entry = iterator.next();
            System.out.println("key : "+entry.getKey()+" value : " + entry.getValue());
        }
        System.out.println(treeMap.size());
    }
}

class ValueComparator implements Comparator<Integer> {

    // 一定要持有能根据Key的值找到对应Value值的Map，只有这样才能在compare方法中比较key对应的value
    // 默认传入的比较器都是针对key的比较器，如果想修改为Value的比较器需要通过持有k-v的Map
    private Map<Integer,Integer> map;

    public ValueComparator(Map map) {
        this.map = map;
    }
    @Override
    public int compare(Integer o1, Integer o2) {

        if (map.get(o1) > map.get(o2)){
            return 1;
        }else if(map.get(o1) < map.get(o2)){
            return -1;
        }
        // 如果比较value，相同的value的Key将会发生合并，即Value的值是不允许重复的
        // 必须返回 0，否则get方法将返回null
        else return 0;
    }
}


