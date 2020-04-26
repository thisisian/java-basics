import LinkedList.Node;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class Chapter2Test {

    private static Node zeroToFourList() {
        var l = new LinkedList.Node(0);
        l.appendToTail(1);
        l.appendToTail(2);
        l.appendToTail(3);
        l.appendToTail(4);
        return l;
    }

    @Test
    void nodeToList() {
        var l1 = new LinkedList.Node(0);
        var a1 = l1.toArray();
        assertArrayEquals(a1, new int[] {0});

        var l2 = new LinkedList.Node(0);
        l2.appendToTail(1);
        l2.appendToTail(2);
        l2.appendToTail(3);
        l2.appendToTail(4);
        var a2 = l2.toArray();
        assertArrayEquals(a2, new int[] {0,1,2,3,4});
    }

    @Test
    void removeNode() {
        var l1 = new Node(new int[] {0,1,2,3,4});
        var l1_p = Chapter2.removeNode(l1, 0);
        assertArrayEquals(l1_p.toArray(), new int[] {1,2,3,4});

        var l2 = new Node(new int[] {0,1,2,3,4});
        var l2_p = Chapter2.removeNode(l2, 1);
        assertArrayEquals(l2_p.toArray(), new int[] {0,2,3,4});

        var l3 = new Node(new int[] {0,1,2,3,4});
        var l3_p = Chapter2.removeNode(l3, 4);
        assertArrayEquals(l3_p.toArray(), new int[] {0,1,2,3});
    }

    @Test
    void removeDuplicates() {
        var l1 = new Node(new int[] {0,0,1,2,1,3,4,3});
        var l1_p = Chapter2.removeDuplicates(l1, new HashSet<Integer>());
        assertArrayEquals(l1_p.toArray(), new int[] {0,1,2,3,4});
    }

    @Test
    void kthToLast() {
        var l1 = new Node(new int[] {0,1,2,3,4,5,6,7,8,9});
        var act1 = Chapter2.kthToLast(l1, 0);
        var act2 = Chapter2.kthToLast(l1, 9);
        var act3 = Chapter2.kthToLast(l1, 3);
        assertArrayEquals(act1.toArray(), new int[] {9});
        assertArrayEquals(act2.toArray(), new int[] {0,1,2,3,4,5,6,7,8,9});
        assertArrayEquals(act3.toArray(), new int[] {6,7,8,9});
    }

    @Test
    void deleteMiddle() {
        var l1 = new Node(new int[] {0,1,2,3,4,5});
        Chapter2.deleteMiddle(l1.next);
        assertArrayEquals(new int[] {0,2,3,4,5}, l1.toArray());
    }

    @Test
    void partition() {
        var l1 = new Node(new int[] {3,5,8,5,10,2,1});
        var act = Chapter2.parition(l1, 5);
        System.out.println(Arrays.toString(act.toArray()));
        assertArrayEquals(new int[] {3,2,1,5,8,5,10}, act.toArray());
    }

    @Test
    void sumLists() {
        var l1 = new Node(new int[] {7,1,6});
        var l2 = new Node(new int[] {5,9,2});
        var actual = Chapter2.sumLists(l1, l2);
        assertArrayEquals(new int[] {2,1,9}, actual.toArray());

        l1 = new Node(new int[] {1,6,8});
        l2 = new Node(new int[] {5,9,7});
        actual = Chapter2.sumLists(l1, l2);
        System.out.println(Arrays.toString(actual.toArray()));
        assertArrayEquals(new int[] {6,5,6,1}, actual.toArray());
    }

    @Test
    void sumListsReversed() {
        var l1 = new Node(new int[] {6,1,7});
        var l2 = new Node(new int[] {2,9,5});
        var actual = Chapter2.sumListReversed(l1, l2);
        assertArrayEquals(new int[] {9,1,2}, actual.toArray());

        l1 = new Node(new int[] {8,6,1});
        l2 = new Node(new int[] {2,9,5});
        actual = Chapter2.sumListReversed(l1, l2);
        assertArrayEquals(new int[] {1,1,5,6}, actual.toArray());
    }

    @Test
    void reverse() {
        var l = new Node(new int[] {0,1,2,3,4});
        var l2 = Node.reverse(l);
        assertArrayEquals(new int[] {4,3,2,1,0}, l2.toArray());
    }

    @Test
    void isPalindrome() {
        var l = new Node(new int[] {0,1,2,3,4});
        assertFalse(Chapter2.isPalindrome(l));

        l = new Node(new int[] {0,1,2,1,0});
        assertTrue(Chapter2.isPalindrome(l));

        l = new Node(new int[] {0,1,1,0});
        assertTrue(Chapter2.isPalindrome(l));
    }

    @Test
    void intersection() {
        var a = new Node(0);
        var b = new Node(1);
        var c = new Node(2);
        var d = new Node(3);
        var e = new Node(4);
        var f = new Node(5);
        var g = new Node(6);

        a.next = b;
        b.next = d;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;

        var res = Chapter2.intersection(a, c);
        System.out.println(Arrays.toString(res.toArray()));
        assertArrayEquals(new int[] {3,4,5,6}, res.toArray());

    }

    private static Node mkLoop(int length) {
        if (length == 0) return null;
        var head = new Node(0);
        var x = head;
        for (int i = 1; i < length; ++i) {
            var n = new Node(i);
            x.next = n;
            x = n;
        }
        x.next = head;
        return head;
    }

    private static Node mkTail(int length) {
        if (length == 0) return null;
        Node head = new Node(-length);
        Node x = head;
        for (int i = 1 - length; i < 0; ++i) {
            var n = new Node(i);
            x.next = n;
            x = n;
        }
        return head;
    }


    @Test
    void loopDetection(){
        for (int i = 1; i <= 10; ++i) {
            for (int j = 0; j <= 10; ++j) {
                var head = mkTail(i);
                var loop = mkLoop(j);
                head.append(loop);
                var res = Chapter2.detectLoop(head);
                if (j == 0) {
                    assertTrue(res == null);
                } else {
                    assertEquals(0, res.data);
                }
            }
        }
    }
}
