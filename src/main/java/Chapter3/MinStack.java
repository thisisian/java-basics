package Chapter3;

import java.util.EmptyStackException;

public class MinStack<T extends Comparable<T>> {
    private MyStack<MinStackNode> stack = new MyStack<>() ;

    private class MinStackNode {
        private final T item;
        private final T curMin;
        public MinStackNode(T item, T curMin){
            this.item = item;
            this.curMin = curMin;
        }

        public T getCurMin() {
            return curMin;
        }

        public T getItem() {
            return item;
        }
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void push(T item) {
        T curMin;
        if (this.isEmpty()) {
            curMin = item;
        } else {
            var p = stack.peek();
            curMin = p.getCurMin();
            curMin = item.compareTo(curMin) < 0 ? item : curMin;
        }
        stack.push(new MinStackNode(item, curMin));
    }

    public T pop() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        var node = stack.pop();
        return node.getItem();
    }

    public T min() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        var node = stack.peek();
        return node.getCurMin();
    }
}
