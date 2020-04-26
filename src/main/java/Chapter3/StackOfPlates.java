package Chapter3;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class StackOfPlates<T> implements MyStackInterface<T>{
    private int maxSize;
    private StackWithSize<StackWithSize<T>> stack;

    public StackOfPlates(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new StackWithSize<StackWithSize<T>>();
    }

    public class StackWithSize<T2> extends MyStack<T2> {
        private int size;

        public StackWithSize() {
            this.size = 0;
        }

        @Override
        public T2 pop() throws EmptyStackException {
            var ret = super.pop();
            --size;
            return ret;
        }

        @Override
        public void push(T2 item) {
            super.push(item);
            ++size;
        }

        public int getSize() {
            return size;
        }
    }

    @Override
    public void push(T item) {
        if (stack.isEmpty() || stack.peek().getSize() >= maxSize) {
            var newStack = new StackWithSize<T>();
            newStack.push(item);
            stack.push(newStack);
        } else {
            var curStack = stack.peek();
            curStack.push(item);
        }
    }

    @Override
    public T pop() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        var curStack = stack.peek();
        var ret = curStack.pop();
        if (curStack.isEmpty()) {
            stack.pop();
        }
        return ret;
    }

    @Override
    public T peek() throws EmptyStackException {
        return stack.peek().peek();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public T popAt(int index) {
        if (index >= stack.getSize()) {
            throw new NoSuchElementException();
        }
        var temp = new MyStack<StackWithSize<T>>();
        var curStackCount = stack.getSize();
        for (int i = 0; i < (curStackCount - index - 1); ++i) {
            var s = stack.pop();
            temp.push(s);
        }
        var curStack = stack.pop();
        var ret = curStack.pop();
        if (!curStack.isEmpty()) {
            stack.push(curStack);
        }
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
        return ret;
    }
}
