import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class Chapter4 {

    public static boolean routeExists(Node n1, Node n2) {
        var n1q = new LinkedList<Node>();
        var n1Marked = new HashSet<Node>();
        n1q.add(n1);
        n1Marked.add(n1);

        var n2q = new LinkedList<Node>();
        var n2Marked = new HashSet<Node>();
        n2q.add(n2);
        n1Marked.add(n2);

        while(!n1q.isEmpty() && !n2q.isEmpty()) {
            var r1 = n1q.remove();
            var r2 = n2q.remove();

            for (Node n : r1.children) {
                if (!n1Marked.contains(n)) {
                    if (n2Marked.contains(n)) {
                        return true;
                    }
                    n1q.add(n);
                    n1Marked.add(n);
                }
            }
            for (Node n : r2.children) {
                if (!n2Marked.contains(n)) {
                    if (n1Marked.contains(n)) {
                        return true;
                    }
                    n2q.add(n);
                    n2Marked.add(n);
                }
            }
        }
        return false;
    }

    // Given a sorted array with unique integer elements, write an algorithm to create
    // a BST with minimal height.
    public static BinTreeNode<Integer> minimalTree(int[] xs) {
        return mkMinimalTree(xs, 0, xs.length);
    }

    private static BinTreeNode<Integer> mkMinimalTree(int[] xs, int low, int high) {
        if (low >= high) {
            return null;
        }
        var mid = (high+low)/2;
        var n = new BinTreeNode<Integer>(xs[mid]);
        var l = mkMinimalTree(xs, low, mid);
        var r = mkMinimalTree(xs, mid+1, high);
        n.setLeftNode(l);
        n.setRightNode(r);
        return n;
    }

    private static void printBinTree(BinTreeNode<Integer> root) {
        var q = new LinkedList<BinTreeNode<Integer>>();
        q.add(root);
        while (!q.isEmpty()) {
            var n = q.remove();
            System.out.print(n.elem);
            if (n.leftNode != null) {
                q.add(n.leftNode);
            }
            if (n.rightNode != null) {
                q.add(n.rightNode);
            }
        }
        System.out.println();
    }

    public static <T> ArrayList<LinkedList<T>> listOfDepths(BinTreeNode<T> root) {
        var arrayList = new ArrayList<LinkedList<T>>();
        listOfDepthHelp(arrayList, root, 0);
        return arrayList;
    }

    public static <T> void listOfDepthHelp(ArrayList<LinkedList<T>> arrayList,
                                           BinTreeNode<T> root,
                                           int currentLevel) {
        if (root == null) {
            return;
        }

        if (currentLevel >= arrayList.size()) {
            var l = new LinkedList<T>();
            l.add(root.elem);
            arrayList.add(l);
        } else {
            var l = arrayList.get(currentLevel);
            l.add(root.elem);
        }

        listOfDepthHelp(arrayList, root.leftNode, currentLevel+1);
        listOfDepthHelp(arrayList, root.rightNode, currentLevel+1);
    }

    public static <T> boolean isBalanced(BinTreeNode<T> root) {
        var minMax = getMinMaxDepth(root, 0);
        var max = minMax.getValue();
        var min = minMax.getKey();
        System.out.println(max);
        System.out.println(min);
        if (max - min <= 1) {
            return true;
        } else {
            return false;
        }
    }

    public static <T> Pair<Integer, Integer> getMinMaxDepth(BinTreeNode<T> root, int curDepth) {
        if (root.rightNode == null && root.leftNode == null) {
            return new Pair<>(curDepth, curDepth);
        } else if (root.rightNode == null) {
            return getMinMaxDepth(root.leftNode, curDepth+1);
        } else if (root.leftNode == null) {
            return getMinMaxDepth(root.rightNode, curDepth+1);
        } else {
            var minMaxLeft = getMinMaxDepth(root.leftNode, curDepth+1);
            var minMaxRight = getMinMaxDepth(root.rightNode, curDepth+1);
            var max = minMaxLeft.getValue() > minMaxRight.getValue() ? minMaxLeft.getValue()
                    : minMaxRight.getValue();
            var min = minMaxLeft.getKey() < minMaxRight.getKey() ? minMaxLeft.getKey()
                    : minMaxRight.getKey();
            return new Pair<>(min, max);
        }
    }

    public static boolean isBST(BinTreeNode<Integer> root) {
        return isBstRec(root, root.elem, root.elem);
    }

    private static boolean isBstRec(BinTreeNode<Integer> root, int max, int min) {
        if (root == null) {
            return true;
        }

        var rightIsSmaller = root.rightNode != null && root.rightNode.elem <= min;
        var leftIsLarger = root.leftNode != null && root.leftNode.elem > max;

        if (rightIsSmaller || leftIsLarger) {
            return false;
        } else {
            return (isBST(root.leftNode) && isBST(root.rightNode));
        }
    }

    public static int bstSucc(BinTreeNode<Integer> root, int max, int min) {
        if (root.rightNode != null) {
            var n = root.rightNode;
            while (n.leftNode != null) {
                n = n.leftNode;
            }
            return n.elem;
        } else {
            var n = root;
            while (root.parent != null) {
                var n2 = n.parent;
                if (n2.leftNode == n) {
                    return n2.leftNode.elem;
                }
                n = n2;
            }
            return n.elem;
        }
    }
    

}
