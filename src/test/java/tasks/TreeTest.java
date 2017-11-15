package tasks;

import org.junit.Before;
import org.junit.Test;

/**
 * @author zhanglbjames@163.com
 * @version Created on 17-11-13.
 */
public class TreeTest {

    @Test
    public void testToString1() {
        System.out.println("\n");
        Tree<String,String> tree  = new Tree<String, String>();

        tree.add("B","B");
        tree.add("G","G");
        tree.add("K","k");
        tree.add("P","P");

        tree.add("R","R");
        tree.add("S","S");
        tree.add("V","V");

//        System.out.println(tree.toString());
//        System.out.println(tree.toStringReversed());
//        System.out.println(tree.ceiling("C"));
//        System.out.println(tree.ceiling("P"));
//        System.out.println(tree.ceiling("Y"));
//        System.out.println(tree.floor("C"));
//        System.out.println(tree.floor("P"));
//        System.out.println(tree.floor("A"));
        System.out.println(tree.diameter());
        System.out.println(tree.numBetween("B","V"));
        System.out.println(tree.numBetween("A","Z"));
        System.out.println(tree.numBetween("C","Q"));
        System.out.println(tree.numBetween("H","J"));

    }
    @Test
    public void testToString2() {
        System.out.println("\n");
        Tree<String,String> tree  = new Tree<String, String>();

        tree.add("P","P");
        tree.add("G","G");
        tree.add("B","B");
        tree.add("K","K");

        tree.add("S","S");
        tree.add("R","R");
        tree.add("V","V");

        System.out.println(tree.diameter());
        System.out.println(tree.numBetween("B","V"));
        System.out.println(tree.numBetween("A","Z"));
        System.out.println(tree.numBetween("C","Q"));
        System.out.println(tree.numBetween("H","J"));
    }
    @Test
    public void testToString3() {
        System.out.println("\n");
        Tree<String,String> tree  = new Tree<String, String>();

        tree.add("B","B");
        tree.add("P","P");
        tree.add("G","G");
        tree.add("K","K");

        tree.add("S","S");
        tree.add("R","R");
        tree.add("V","V");
        System.out.println(tree.diameter());
        System.out.println(tree.numBetween("B","V"));
        System.out.println(tree.numBetween("A","Z"));
        System.out.println(tree.numBetween("C","Q"));
        System.out.println(tree.numBetween("H","J"));
    }


}
