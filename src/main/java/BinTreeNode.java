public class BinTreeNode<T> {
    T elem;
    public BinTreeNode<T> leftNode;
    public BinTreeNode<T> rightNode;
    public BinTreeNode<T> parent;

    public BinTreeNode(T elem) {
        this.elem = elem;
    }

    public void setRightNode(BinTreeNode<T> rightNode) {
        this.rightNode = rightNode;
        rightNode.parent = this;
    }

    public void setLeftNode(BinTreeNode<T> leftNode) {
        this.leftNode = leftNode;
        leftNode.parent = this;
    }

    public void setChildren(BinTreeNode<T> leftNode, BinTreeNode<T> rightNode) {
        setLeftNode(leftNode);
        setRightNode(rightNode);
    }
}
