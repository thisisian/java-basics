public class Graph {
    public Node[] nodes;
}

class Node {
    public String name;
    public Node[] children;
    public Node(String name) {
        this.name = name;
    }
    public void setChildren(Node[] children) {
        this.children = children;
    }
}
