package tasks;

/**
 * @author zhanglbjames@163.com
 * @version Created on 17-11-13.
 */
public class Tree<K extends Comparable<K> ,D> {


    private Node<K, D> root;

    // global variable represented diameter of the Tree Object
    // -1 means invalid value of the treeDiameter
    private int treeDiameter = -1;


    /**
     * Returns a string that contains the keys in reverse order
     *
     * the worst-case running time is O(n) (BST degenerates into linked list)
     */
    public String toStringReversed() {
        return toStringReversed(root);
    }


    // also see the method: toString(Node<K,D> root)
    private String toStringReversed(Node<K,D> root) {
        if(root == null)
            return "";
        // access order: root.right -> root -> root.left
        return toStringReversed(root.right) + root+ toStringReversed(root.left);
    }


    /**
     * returns the node with the smallest key that is greater than or
     * equal to key. If key is in the tree, then the node containing
     * key is returned.If key is greater than any key in the true, null is
     * returned.
     *
     * the worst-case running time is O(n) (BST degenerates into linked list)
     */
    public Node<K,D> ceiling(K key) {
        if (key == null) {
            return null;
        }
        return ceiling(key,root);
    }

    private Node<K,D> ceiling(K key, Node<K,D> root) {
        if (root == null) {
           return null;
        }

        int c = key.compareTo(root.key);

        if (c == 0) { // if equal, return root
            return root;
        } else if (c > 0) {
            // if key is larger,towards the right to find the node
            // which is larger than the key.
            return ceiling(key,root.right);

        }else { // c < 0

            // if key is smaller, towards the left to find the node
            // which is smaller than the root but larger than the key.
            Node<K,D> tmp = ceiling(key,root.left);

            // return the found node
            if (tmp != null) {
                return tmp;
            }else // if not found, return root
                return root;
        }

    }

    /**
     * returns the node with the largest key that is less than or equal to
     * key. If key is in the tree, then the node containing key is returned.
     * If key is less than any key in the true, null is returned.
     *
     * the worst-case running time is O(n) (BST degenerates into linked list)
     */
    public Node floor(K key) {
        if (key == null) {
            return null;
        }
        return floor(key,root);
    }

    private Node<K, D> floor(K key, Node<K, D> root) {
        if (root == null) {
            return null;
        }

        int c = key.compareTo(root.key);
        if (c == 0) { // if equal, return root
            return root;
        } else if (c < 0) {
            // if key is smaller,towards the left to find the node which
            // is smaller than the key.
            return floor(key,root.left);
        }else {
            // if key is larger, towards the right to find the node
            // which is larger than the root but smaller than the key.
            Node<K,D> tmp = floor(key,root.right);

            // return the found node
            if (tmp != null) {
                return tmp;
            }else // if not found, return root
                return root;
        }
    }


    /**
     * Returns the treeDiameter of the tree.
     *
     * the worst-case running time is O(n) (BST degenerates into linked list)
     */
    public int diameter() {

        // each time diameter() is invoked,
        // treeDiameter should be initialized to an invalid value(here is -1)
        treeDiameter = -1;

        diameter(root);
        return treeDiameter;
    }

    private int diameter(Node<K, D> root) {
        if (root == null) {
            return 0;
        }

        // calculate heights of the left subtree and the right subtree
        int leftDiameter = diameter(root.left);
        int rightDiameter = diameter(root.right);

        // calculate diameter of the current node
        int currentDiameter =  leftDiameter + rightDiameter + 1;

        // update the global variable treeDiameter
        if (currentDiameter > treeDiameter)
            treeDiameter = currentDiameter;

        // return height of the current node to caller
        return Math.max(leftDiameter,rightDiameter) + 1;

    }

    /**
     * Returns the number of records in the tree whose keys are between key1
     * and key2, inclusive.
     *
     * the worst-case running time is O(n) (BST degenerates into linked list)
     */
    public int numBetween(K k1, K k2) {
        if (k1 == null || k2 == null) {
            System.err.println("Error: k1 and k2 can't be null");
            System.exit(1);
        } else if (k1.compareTo(k2) > 0) { // k1 > k2
            System.err.println("Error: k1 should be no more than k2");
            System.exit(1);
        } else if (k1.compareTo(k2) == 0) { // k1 == k2
            return find(k1) == null ? 0 : 1;
        }

        //------------- case: k1 < k2 -----------

        // Just find a middle node whose key is between k1 and k2 (inclusive).
        // And we use the middle node splits the tree into two subtrees,
        // the left nodes'keys are not more than middle node, the right nodes'keys
        // are not less than middle node.
        // Then we calculate the number of nodes whose keys are not less than k1 in
        // the left subtree(@see numBetweenKey1AndMiddle), and the number of nodes
        // whose keys are not more than k2 in the right subtree(@see numBetweenMiddleAndKey2),
        // the final result equals to numBetweenKey1AndMiddle + numBetweenMiddleAndKey2 + 1.

        Node<K,D> middleNode = findTheMiddleNode(root,k1,k2);

        Node<K,D> tmpMiddleNode;

        if (middleNode != null) {
            // return the result = numBetweenKey1AndMiddle + numBetweenMiddleAndKey2 + 1
            return numBetweenKey1AndMiddle(tmpMiddleNode=middleNode.left,k1)
                    + numBetweenMiddleAndKey2(tmpMiddleNode=middleNode.right,k2)
                    + 1;
        }else // middleNode == null, means all nodes are not between k1 and k2,so return 0
            return 0;
    }

    /**
     * returns the number of records in the subtree
     * whose keys are between key1 and middleNode.left.key
     * note: search logic just likes the method ceiling(K key)
     */
    private int numBetweenKey1AndMiddle(Node<K, D> middleNode, K k1) {
        if (middleNode == null) {
            return 0;
        }

        int c = k1.compareTo(middleNode.key);

        if (c == 0) {
            // if equal, return numOfSubTree(middleNode.right) + 1
            return numOfSubTree(middleNode.right) + 1;
        } else if (c > 0) {
            // if k1 smaller than middleNode.key
            // recursive call numBetweenKey1AndMiddle(middleNode.right,k1)
            return numBetweenKey1AndMiddle(middleNode.right,k1);

        }else { // c < 0
            int num = numBetweenKey1AndMiddle(middleNode.left,k1);
            if (num != 0) {
                return num + numOfSubTree(middleNode.right) + 1;
            }else
                return 1 + numOfSubTree(middleNode.right);
        }
    }

    /**
     * returns the number of records in the subtree
     * whose keys are between middleNode.right.key and k2
     * note: search logic just likes the method floor(K key)
     */
    private int numBetweenMiddleAndKey2(Node<K, D> middleNode, K k2) {
        if (middleNode == null) {
            return 0;
        }

        int c = k2.compareTo(middleNode.key);
        if (c == 0) {
            return numOfSubTree(middleNode.left) + 1;
        } else if (c < 0) {
            return numBetweenMiddleAndKey2(middleNode.left,k2);
        }else {
            int num = numBetweenMiddleAndKey2(middleNode.right,k2);
            if (num != 0) {
                return num + numOfSubTree(middleNode.left) + 1;
            }else
                return 1 + numOfSubTree(middleNode.left);
        }
    }

    /**
     * from root node to find the first middle node
     * whose key is between k1 and k2
     */
    private Node<K, D> findTheMiddleNode(Node<K, D> root, K k1, K k2) {
        if (root == null) {
            return null;
        }
        int c1 = k1.compareTo(root.key);
        int c2 = k2.compareTo(root.key);

        if (c1 <= 0 && c2 >= 0) {
            return root;
        } else if (c1 > 0) {
            return findTheMiddleNode(root.right,k1,k2);
        }else
            return findTheMiddleNode(root.left,k1,k2);
    }

    /**
     * calculate the number of subtree'nodes
     */
    private int numOfSubTree(Node<K,D> root) {
        if (root == null) {
            return 0;
        }
        return numOfSubTree(root.left) + numOfSubTree(root.right) + 1;
    }

    public D find(K key) {
        return find(key, root);
    }

    private D find(K key, Node<K, D> x) {
        if (x == null) {
            return null;
        }
        int c = key.compareTo(x.key);
        if (c == 0) {
            return x.data;
        } else if (c < 0) {
            return find(key, x.left);
        } else // c > 0
            return find(key, x.right);
    }

    public void add(K key, D data) {
        if (root == null) {
            root = new Node<K, D>(key,data);
            return;
        }
        add(key, data, root);
    }

    // returns the tree with the added record.
    private Node<K, D> add(K key, D data, Node<K, D> root) {
        if (root == null) {
            return new Node<K, D>(key, data);
        }
        int c = key.compareTo(root.key);
        if (c == 0) {
            System.err.println("Error: duplicate key: " + key);
            System.exit(1);
            return null;
        } else if (c < 0) {
            root.left = add(key, data, root.left);
            return root;
        } else {
            root.right = add(key, data, root.right);
            return root;
        }
    }

    public void modify(K key, D data) {
        modify(key, data, root);
    }

    private void modify(K key, D data, Node<K, D> root) {
        if (root == null) {
            System.err.println("Error: key not found: " + key);
            System.exit(1);
        }
        int c = key.compareTo(root.key);
        if (c == 0)
            root.data = data;
        else if (c < 0)
            modify(key, data, root.left);
        else // c > 0
            modify(key, data, root.right);
    }


    public String toString() {
        return toString(root);
    }

    private String toString(Node<K,D> root) {
        if(root == null)
            return "";
        return toString(root.left) + root+ toString(root.right);
    }


    public void delete(K key) {
        root = delete(key, root);
    }

    private Node<K,D> findLeftmost(Node<K,D> root) {
        // Assumes root != null.
        return root.left == null
                ? root
                : findLeftmost(root.left);
    }
    private Node<K,D> removeLeftmost(Node<K,D> root) {
        if(root.left == null)
            return root.right;
        else {
            root.left = removeLeftmost(root.left);
            return root;
        }
    }

    private Node<K,D> delete(K key, Node<K,D> root) {
        if (root == null) {
            System.err.println("Error: key not found");
            System.exit(1);
            return null;
        }
        int c = key.compareTo(root.key);
        if (c == 0) {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            else {
                Node<K, D> t = root;
                root = findLeftmost(root.right);
                root.right = removeLeftmost(t.right);
                root.left = t.left;
                return root;
            }
        }
        else if(c < 0) {
            root.left = delete(key, root.left);
            return root;
        }else { // c > 0
            root.right = delete(key, root.right);
            return root;
        }
    }

    /**
     * main method to test Tree class
     */
    public static void main(String[] args) {
        // test 1
        // tree1 is formed by this order: B, G, K, P, R, S, V
        System.out.println("---------------------- test1 -------------------------");
        System.out.println("tree1 is formed by this order: B, G, K, P, R, S, V");
        Tree<String,String> tree1  = new Tree<String, String>();

        tree1.add("B","B");
        tree1.add("G","G");
        tree1.add("K","k");
        tree1.add("P","P");

        tree1.add("R","R");
        tree1.add("S","S");
        tree1.add("V","V");

        // test all methods in tree1
        System.out.println("toString: " + tree1.toString());
        System.out.println("toStringReversed" + tree1.toStringReversed());
        System.out.println("ceiling(C): " + tree1.ceiling("C"));
        System.out.println("ceiling(P): "+tree1.ceiling("P"));
        System.out.println("ceiling(Y): "+tree1.ceiling("Y"));
        System.out.println("floor(C): "+tree1.floor("C"));
        System.out.println("floor(P): "+tree1.floor("P"));System.out.println("floor(A): "+tree1.floor("A"));

        System.out.println("diameter: "+tree1.diameter());
        System.out.println("numBetween(B,V): "+tree1.numBetween("B","V"));
        System.out.println("numBetween(A,Z): "+tree1.numBetween("A","Z"));
        System.out.println("numBetween(C,Q): "+tree1.numBetween("C","Q"));
        System.out.println("numBetween(H,J): "+tree1.numBetween("H","J"));

        // test 2
        // tree2 is formed by this order: P, G, B, K, S, R, V
        System.out.println("\n---------------------- test2 -------------------------");
        System.out.println("tree2 is formed by this order: P, G, B, K, S, R, V");
        Tree<String,String> tree2  = new Tree<String, String>();

        tree2.add("P","P");
        tree2.add("G","G");
        tree2.add("B","B");
        tree2.add("K","K");

        tree2.add("S","S");
        tree2.add("R","R");
        tree2.add("V","V");

        // test all methods in tree2
        System.out.println("toString: " + tree2.toString());
        System.out.println("toStringReversed" + tree2.toStringReversed());
        System.out.println("ceiling(C): " + tree2.ceiling("C"));
        System.out.println("ceiling(P): "+tree2.ceiling("P"));
        System.out.println("ceiling(Y): "+tree2.ceiling("Y"));
        System.out.println("floor(C): "+tree2.floor("C"));
        System.out.println("floor(P): "+tree2.floor("P"));
        System.out.println("floor(A): "+tree2.floor("A"));
        System.out.println("diameter: "+tree2.diameter());
        System.out.println("numBetween(B,V): "+tree2.numBetween("B","V"));
        System.out.println("numBetween(A,Z): "+tree2.numBetween("A","Z"));
        System.out.println("numBetween(C,Q): "+tree2.numBetween("C","Q"));
        System.out.println("numBetween(H,J): "+tree2.numBetween("H","J"));

        // test 3
        // tree3 is formed by this order: B, P, G, K, S, R, V
        System.out.println("\n---------------------- test3 -------------------------");
        System.out.println("tree3 is formed by this order: B, P, G, K, S, R, V");
        Tree<String,String> tree3  = new Tree<String, String>();

        tree3.add("B","B");
        tree3.add("P","P");
        tree3.add("G","G");
        tree3.add("K","K");

        tree3.add("S","S");
        tree3.add("R","R");
        tree3.add("V","V");

        // test all methods in tree3
        System.out.println("toString: " + tree3.toString());
        System.out.println("toStringReversed" + tree3.toStringReversed());
        System.out.println("ceiling(C): " + tree3.ceiling("C"));
        System.out.println("ceiling(P): "+tree3.ceiling("P"));
        System.out.println("ceiling(Y): "+tree3.ceiling("Y"));
        System.out.println("floor(C): "+tree3.floor("C"));
        System.out.println("floor(P): "+tree3.floor("P"));
        System.out.println("floor(A): "+tree3.floor("A"));
        System.out.println("diameter: "+tree3.diameter());
        System.out.println("numBetween(B,V): "+tree3.numBetween("B","V"));
        System.out.println("numBetween(A,Z): "+tree3.numBetween("A","Z"));
        System.out.println("numBetween(C,Q): "+tree3.numBetween("C","Q"));
        System.out.println("numBetween(H,J): "+tree3.numBetween("H","J"));
    }

}