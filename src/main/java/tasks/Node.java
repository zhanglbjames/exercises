package tasks;

/**
 * @author zhanglbjames@163.com
 * @version Created on 17-11-13.
 */
public class Node<K extends Comparable<K>, D> {
    public K key;
    public D data;
    public Node<K,D> left,right;

    public Node(K k, D d, Node<K, D> l, Node<K, D> r) {
        key = k;
        data = d;
        left = l;
        right = r;
    }

    public Node(K k, D d) {
        this(k, d, null, null);
    }
    public String toString() {
        return "(" + key + "," + data + ")";
    }
}
