package task_1130;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zhanglbjames@163.com
 * @version Created on 17-12-1.
 */
public class TwoKey<K1 extends Comparable, K2 extends Comparable, D> {

    /**
     * 2-Key Database Abstract Data Type TwoKey implementation Node:
     *
     * 1. Set up a two-way mapping to support efficient mapping
     * from K1 to K2 {@code k1Tok2Map} and from K2 to K1 {@code k2Tok1Map}.
     * So the beneficial operations are {@link TwoKey#change1(Comparable, Comparable)},
     * {@link TwoKey#change2(Comparable, Comparable)}, {@link TwoKey#modify1(Comparable, Object)},
     * {@link TwoKey#modify2(Comparable, Object)}, {@link TwoKey#list1()}
     * and {@link TwoKey#list2()}.
     *
     * 2. {@code treeMap1} mapping from key1 to data supports operations {@link TwoKey#find1(Comparable)};
     * {@code treeMap2} mapping from key2 to data supports operations {@link TwoKey#find2(Comparable)};
     *
     *
     * Analysis:
     *
     * using auxiliary data storage to reduce the time complexity of the algorithm.
     * all the time complexity of the algorithm is O(log(N)), N is the number of records.
     *
     */


    // mapping from key1 to data
    private TreeMap<K1, D> treeMap1 = new TreeMap<>();
    // mapping from key2 to data
    private TreeMap<K2, D> treeMap2 = new TreeMap<>();

    // mapping from key1 to key2.
    // in order to efficiently retrieve key2 based on key1
    private TreeMap<K1, K2> k1Tok2Map = new TreeMap<>();
    // mapping from key2 to key1.
    // in order to efficiently retrieve key2 based on key1
    private TreeMap<K2, K1> k2Tok1Map = new TreeMap<>();


    /**
     * add a record to the database
     *
     * establish association:
     * k1 -> k2, k2 -> k1, k1 -> data, k2 -> data
     */
    public void add(K1 k1, K2 k2, D data) {


        // put data in dataArray to treeMap
        treeMap1.put(k1, data);
        treeMap2.put(k2, data);

        // put k1 -> k2 and k2 -> k1 entry mapping
        k1Tok2Map.put(k1,k2);
        k2Tok1Map.put(k2,k1);
    }

    /**
     * find and return the data associated with key1
     */
    public D find1(K1 key1) {
        return treeMap1.get(key1);
    }

    /**
     * find and return the data associated with key2
     */
    public D find2(K2 key2) {
        return treeMap2.get(key2);
    }

    /**
     * modify the record associated with key1 to contain the new data
     */
    public void modify1(K1 key1, D data) {
        // put data associated with key1
        treeMap1.put(key1,data);

        // get key2 associated with key1
        K2 key2 = k1Tok2Map.get(key1);

        // put data associated with key2
        treeMap2.put(key2,data);
    }

    /**
     * modify the record associated with key2 to contain the new data
     */
    public void modify2(K2 key2, D data) {
        // put data associated with key2
        treeMap2.put(key2,data);

        // get key1 associated with key2
        K1 key1 = k2Tok1Map.get(key2);

        // put data associated with key1
        treeMap1.put(key1,data);
    }

    /**
     * change the second key of the record associated with
     * key1 to be key2, and keep the previous data

     */
    public void change1(K1 key1, K2 key2) {
        // get old key2 based on k1 -> k2 mapping
        K2 oldKey2 = k1Tok2Map.get(key1);

        // change the k1 -> k2 map
        k1Tok2Map.put(key1,key2);
        // oldKey2 is invalid, so remove the oldKey2 -> key1 map
        k2Tok1Map.remove(oldKey2);
        // put a new map: key2 -> key1
        k2Tok1Map.put(key2,key1);


        // remove oldKey2 and get the data
        D dataTmp = treeMap2.remove(oldKey2);
        // put key2
        treeMap2.put(key2,dataTmp);
    }

    /**
     * change the first key of record associated with key2 to be key1
     * and keep the previous data
     */
    public void change2(K2 key2, K1 key1) {
        // get old key1 based on k2 -> k1 map
        K1 oldKey1 = k2Tok1Map.get(key2);

        // change the k2 -> k1 map
        k2Tok1Map.put(key2,key1);
        // oldKey1 is invalid, so remove the oldKey1 -> key2 map
        k1Tok2Map.remove(oldKey1);
        // put a new map: key1 -> key2
        k1Tok2Map.put(key1,key2);


        // remove oldKey1 and get the data
        D dataTmp = treeMap1.remove(oldKey1);
        // put key1
        treeMap1.put(key1,dataTmp);
    }

    /**
     *  return the list (key, data) in order by key1 as a String
     */
    public String list1() {
        StringBuilder stringBuilder = new StringBuilder();

        Iterator<Map.Entry<K1,D>> iterator = treeMap1.entrySet().iterator();

        // Iterating in order
        while (iterator.hasNext()) {
            Map.Entry<K1,D> entry = iterator.next();
            stringBuilder.append("(").append(entry.getKey()).append(",")
                    .append(entry.getValue()).append(")");
        }
        return stringBuilder.toString();
    }

    /**
     * return the list (key, data) in order by key2 as a String
     */
    public String list2() {
        StringBuilder stringBuilder = new StringBuilder();

        Iterator<Map.Entry<K2,D>> iterator = treeMap2.entrySet().iterator();
        // Iterating in order
        while (iterator.hasNext()) {
            Map.Entry<K2,D> entry = iterator.next();
            stringBuilder.append("(").append(entry.getKey()).append(",")
                    .append(entry.getValue()).append(")");

        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        TwoKey<String,Integer,Integer> db = new TwoKey<>();
        db.add("Sam", 1, 2);
        db.add("Joe", 2, 3);
        db.add("Wilma", 3, 5);
        db.add("Bob", 4, 7);
        System.out.println(db.find1("Wilma"));
        System.out.println(db.find2(4));
        System.out.println(db.list1());
        System.out.println(db.list2());
        db.modify1("Sam", 11);
        db.modify1("Joe", 13);
        db.modify2(1, 17);
        db.change1("Bob", 5);
        db.change2(3, "Pebbles");
        System.out.println(db.list1());
        System.out.println(db.list2());
    }


}
