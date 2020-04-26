import java.util.HashMap;
import java.util.HashSet;

public class EdgeList<T> {
    private HashSet<T> nodes;
    private HashMap<T, HashSet<T>> edges;

    public EdgeList() {
        this.nodes = new HashSet<>();
        this.edges = new HashMap<>();
    }

    public void addEdge(T from, T to) {
        addNode(from);
        addNode(to);
        insertIntoMap(this.edges, from, to);
    }

    public HashSet<T> getNodes() {
        return nodes;
    }

    public HashSet<T> getNeighbors(T node) {
        return edges.get(node);
    }

    public void addNode(T node) {
        nodes.add(node);
        if (!edges.containsKey(node)) {
            var noChildren = new HashSet<T>();
            edges.put(node, noChildren);
        }
    }

    private void insertIntoMap(HashMap<T, HashSet<T>> m, T to, T from) {
        var children = m.get(from);
        if (children == null) {
            throw new RuntimeException("Children map does not exist for node!");
        }
        children.add(to);
    }

    public EdgeList<T> reverse() {
        var ret = new EdgeList<T>();
        for (var n : edges.entrySet()) {
            var from = n.getKey();
            for (var to : n.getValue()) {
                ret.addEdge(to, from);
            }
        }
        return ret;
    }

    public HashSet<T> findRoots() {
        var roots = new HashSet<T>();

        var reversed = this.reverse();
        for (var n : reversed.getNodes()) {
            var neighbors = reversed.getNeighbors(n);
            if (neighbors.isEmpty()) {
                roots.add(n);
            }
        }
        //var q = new LinkedList<>(reversed.getNodes());
        //var visited = new HashSet<T>();
        //while (!q.isEmpty()) {
        //    T n = q.remove();
        //    if (visited.contains(n)) {
        //        continue;
        //    }
        //    visited.add(n);
        //    var neighbors = reversed.getNeighbors(n);
        //    if (neighbors.isEmpty()) {
        //        roots.add(n);
        //    } else {
        //        q.addAll(neighbors);
        //    }
        //}
        return roots;
    }

    public static void main(String[] s) {
        var g = new EdgeList<String>();
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("D", "F");
        g.addEdge("E", "F");
        g.addEdge("G", "H");
        g.addEdge("H", "G");
        var ret = g.findRoots();
        System.out.println(ret.toString());
    }
}
