import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class Chapter4Test {

    @Test
    void listOfDepths() {
        BinTreeNode<Integer> n1 = new BinTreeNode<>(1);
        BinTreeNode<Integer> n2 = new BinTreeNode<>(2);
        BinTreeNode<Integer> n3 = new BinTreeNode<>(3);
        BinTreeNode<Integer> n4 = new BinTreeNode<>(4);
        BinTreeNode<Integer> n5 = new BinTreeNode<>(5);
        BinTreeNode<Integer> n6 = new BinTreeNode<>(6);
        BinTreeNode<Integer> n7 = new BinTreeNode<>(7);
        BinTreeNode<Integer> n8 = new BinTreeNode<>(8);
        BinTreeNode<Integer> n9 = new BinTreeNode<>(9);
        BinTreeNode<Integer> n10 = new BinTreeNode<>(10);

        n1.setChildren(n2, n3);
        n2.setChildren(n4, null);
        n3.setChildren(n5,n6);
        n4.setChildren(n7,null);
        n5.setChildren(null,n8);
        n6.setChildren(n9,n10);

        var listOfDepths = Chapter4.listOfDepths(n1);
        var actual = new ArrayList<Integer>();
        for (LinkedList<Integer> i : listOfDepths) {
            actual.addAll(i);
        }
        var actualactual = actual.toArray(new Integer[]{});
        assertArrayEquals(actualactual, new Integer[] {1,2,3,4,5,6,7,8,9,10});
    }

    @Test
    void isBalanced() {
        BinTreeNode<Integer> n1 = new BinTreeNode<>(1);
        BinTreeNode<Integer> n2 = new BinTreeNode<>(2);
        BinTreeNode<Integer> n3 = new BinTreeNode<>(3);
        BinTreeNode<Integer> n4 = new BinTreeNode<>(4);
        BinTreeNode<Integer> n5 = new BinTreeNode<>(5);
        BinTreeNode<Integer> n6 = new BinTreeNode<>(6);
        BinTreeNode<Integer> n7 = new BinTreeNode<>(7);
        BinTreeNode<Integer> n8 = new BinTreeNode<>(8);
        BinTreeNode<Integer> n9 = new BinTreeNode<>(9);
        BinTreeNode<Integer> n10 = new BinTreeNode<>(10);
        BinTreeNode<Integer> n11 = new BinTreeNode<>(11);
        BinTreeNode<Integer> n12 = new BinTreeNode<>(12);

        n1.setChildren(n2, n3);
        n2.setChildren(n4, null);
        n3.setChildren(n5,n6);
        n4.setChildren(n7,null);
        n5.setChildren(null,n8);
        n6.setChildren(n9,n10);
        assertTrue(Chapter4.isBalanced(n1));

        n10.setChildren(null,n11);
        assertTrue(Chapter4.isBalanced(n1));

        n11.setChildren(n12,null);
        assertFalse(Chapter4.isBalanced(n1));
    }

}
