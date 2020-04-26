import LinkedList.Node;
import javafx.util.Pair;

import java.util.*;

public class Chapter2 {


    public static Node removeNode(Node head, int d) {
        if (head == null) return null;
        Node n = head;

        if (n.data == d) {
            return head.next;
        }

        while (n.next != null) {
            if (n.next.data == d) {
                n.next = n.next.next;
                return head;
            }
            n = n.next;
        }
        return head;
    }

    public static Node removeDuplicates(Node head, Set<Integer> seen) {
        if (head == null) return null;
        if (seen.contains(head.data)) {
            return removeDuplicates(head.next, seen);
        }
        seen.add(head.data);
        head.next = removeDuplicates(head.next, seen);
        return head;
    }

    public static Node kthToLast(Node n, int k) {
        var forward = n;
        for (int i = 0; i < k; ++i) {
            if (forward.next == null) return null;
            forward = forward.next;
        }
        var rear = n;
        while (forward.next != null) {
            forward = forward.next;
            rear = rear.next;
        }
        return rear;
    }

    public static void deleteMiddle(Node n) {
        n.data = n.next.data;
        n.next = n.next.next;
    }

    public static Node parition(Node n, int x) {
        Node left = null;
        Node right = null;
        var cur = n;
        while (cur != null) {
            if (cur.data < x) {
                if (left == null) {
                    left = new Node(cur.data);
                } else {
                    left.appendToTail(cur.data);
                }
            } else {
                if (right == null) {
                    right = new Node(cur.data);
                } else {
                    right.appendToTail(cur.data);
                }
            }
            cur = cur.next;
        }
        left.append(right);
        return left;
    }

    public static Node sumLists(Node n1, Node n2) {
        return sumLists(n1, n2, 0);
    }

    public static Node sumLists(Node n1, Node n2, int carryVal) {
        if (n1 == null && n2 == null) {
            return null;
        }

        int v1;
        int v2;
        if (n1 == null) {
            v1 = 0;
        } else {
            v1 = n1.data;
        }
        if (n2 == null) {
            v2 = 0;
        } else {
            v2 = n2.data;
        }
        int val = v1 + v2 + carryVal;

        var newNode = new Node(val % 10);
        Node nextn1 = null;
        Node nextn2 = null;
        if (n1 != null) nextn1 = n1.next;
        if (n2 != null) nextn2 = n2.next;
        newNode.next = sumLists(nextn1, nextn2, val / 10);
        if (n1.next == null && n2.next == null && val > 10) {
            newNode.next = new Node(1);
        }
        return newNode;
    }

    public static Node sumListReversed(Node n1, Node n2) {
        var nodeCarryPair = sumListReversedHelper(n1, n2, true);
        return (nodeCarryPair.getKey());
    }

    private static Pair<Node, Integer> sumListReversedHelper(Node n1, Node n2, boolean isFirstNode) {
        if (n1 == null || n2 == null) {
            return new Pair<>(null, 0);
        }

        var nextNodeCarryPair = sumListReversedHelper(n1.next, n2.next, false);
        var nextNode = nextNodeCarryPair.getKey();
        var carry = nextNodeCarryPair.getValue();

        var val = n1.data + n2.data + carry;
        var ret = new Node(val % 10);
        ret.next = nextNode;
        if (isFirstNode && val >= 10) {
            var firstNode = new Node(1);
            firstNode.next = ret;
            return (new Pair<>(firstNode, 0));
        }
        return new Pair<>(ret, val / 10);
    }

    public static boolean isPalindrome(Node l1) {
        var n1 = l1;
        var n2 = Node.reverse(l1);
        while (n1 != null || n2 != null) {
            if (n1.data != n2.data) {
                return false;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        return true;
    }

    public static Node intersection(Node l1, Node l2) {
        var n1 = l1;
        var length_l1 = 1;
        while (n1.next != null) {
            n1 = n1.next;
            ++length_l1;
        }

        var n2 = l2;
        var length_l2 = 1;
        while (n2.next != null) {
            n2 = n2.next;
            ++length_l2;
        }

        if (n1 != n2) {
            return null;
        }

        var longList = length_l1 > length_l2 ? l1 : l2;
        var shortList = length_l1 > length_l2 ? l2 : l1;
        var diff = Math.abs(length_l1-length_l2);

        for (int i = 0; i < diff; ++i) {
            longList = longList.next;
        }
        while(longList != shortList) {
            longList = longList.next;
            shortList = shortList.next;
        }
        return longList;
    }

    public static Node kthElement(Node l1, int k) {
        var n = l1;
        for (int i = 0; i < k; ++i) {
            if (n.next == null) {
                return null;
            }
            n = n.next;
        }
        return n;
    }

    public static Node detectLoop(Node l) {
        var x = l;
        var y = l;

        var firstCollisionResult = detectCollision(x, y);
        if (firstCollisionResult == null) return null;
        //System.out.println("First: " + firstCollisionResult.numSteps);

        x = firstCollisionResult.node;
        y = firstCollisionResult.node;
        var secondCollisionResult = detectCollision(x, y);
        if (secondCollisionResult == null) return null;
        //System.out.println("Second: " + secondCollisionResult.numSteps);

        var diff = firstCollisionResult.numSteps - secondCollisionResult.numSteps;
        var start_node = kthElement(l, diff);

        for (int i = 0; i <= secondCollisionResult.numSteps; ++i) {
            x = kthElement(start_node, i);
            y = secondCollisionResult.node;
            for (int j = 0; j <= secondCollisionResult.numSteps; ++j) {
                //System.out.println("Trying x = " + x.data + ", y = " + y.data);
                if (x == y) return x;
                y = y.next;
            }
        }
        assert(false);
        return null;
    }

    private static class CollisionResult {
        Node node;
        int numSteps;

        private CollisionResult(Node node, int numSteps) {
            this.node = node;
            this.numSteps = numSteps;
        }
    }

    private static CollisionResult detectCollision(Node x, Node y) {
        var steps = 0;
        do {
            if (x.next == null || y.next == null || y.next.next == null) return null;
            x = x.next;
            y = y.next.next;
            ++steps;
            //System.out.println("Slow: " + x.data + ", Fast: " + y.data + ", Steps: " + steps );
        } while (x != y);
        return (new CollisionResult(x, steps));
    }


}
