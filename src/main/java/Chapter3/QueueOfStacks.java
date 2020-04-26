package Chapter3;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class QueueOfStacks<T> {

    private MyStack<T> frontStack = new MyStack<T>();
    private MyStack<T> rearStack = new MyStack<T>();

    public void add(T item) {
        rearStack.push(item);
    }

    public T remove() {
        if (frontStack.isEmpty()) {
            moveToFrontStack();
        }
        T ret;
        try {
            ret = frontStack.pop();
        } catch (EmptyStackException e) {
            throw new NoSuchElementException();
        }
        return ret;
    }

    public T peek() throws NoSuchElementException {
        if (frontStack.isEmpty()) {
            moveToFrontStack();
        }
        T ret;
        try {
            ret = frontStack.peek();
        } catch (EmptyStackException e) {
            throw new NoSuchElementException();
        }
        return ret;
    }

    public boolean isEmpty() {
        return (frontStack.isEmpty() && rearStack.isEmpty());
    }

    private void moveToFrontStack() {
        while (!rearStack.isEmpty()) {
            var item = rearStack.pop();
            System.out.println("Moving" + item);
            frontStack.push(item);
        }
    }
}
