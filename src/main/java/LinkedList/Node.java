package LinkedList;

import java.util.ArrayList;

public class Node {
    public Node next = null;
    public int data;

    public Node(int d) {
        data = d;
    }

    public Node(int[] d) {
        data = d[0];
        for (int i = 1; i < d.length; ++i) {
            this.appendToTail(d[i]);
        }
    }

    public void appendToTail(int d) {
        Node end = new Node(d);
        Node n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }

    public int[] toArray() {
        var list = new ArrayList<Integer>();
        var n = this;
        while (n != null) {
            list.add(n.data);
            n = n.next;
        }
        var ret = new int[list.size()];
        for (int i = 0; i < ret.length; ++i) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    public void append(Node l) {
        var n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = l;
    }

    public static Node reverse(Node l) {
        var nh = reverseHelp(l);
        return nh.head;
    }

    private static class ReverseHelper {
        Node head;
        Node last;

        private ReverseHelper(Node head, Node last) {
            this.head = head;
            this.last = last;
        }
    }

    private static ReverseHelper reverseHelp(Node l) {
        var newNode = new Node(l.data);
        if (l.next == null) {
            return (new ReverseHelper(newNode, newNode));
        }
        var nextHelper = reverseHelp(l.next);
        nextHelper.last.next = newNode;
        return (new ReverseHelper(nextHelper.head, newNode));
    }
}
