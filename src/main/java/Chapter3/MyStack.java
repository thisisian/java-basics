package Chapter3;

import java.util.EmptyStackException;

public class MyStack<T> implements MyStackInterface<T> {
    private static class StackNode<T> {
        private final T data;
        private StackNode<T> next;

        public StackNode(T data){
            this.data = data;
        }
    }

    private StackNode<T> top;

    @Override
    public T pop() throws EmptyStackException {
        if (top == null) throw new EmptyStackException();
        T item = top.data;
        top = top.next;
        return item;
    }

    @Override
    public void push(T item) {
        StackNode<T> t = new StackNode<>(item);
        t.next = top;
        top = t;
    }

    @Override
    public T peek() throws EmptyStackException {
        if (top == null) throw new EmptyStackException();
        return top.data;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }
}
