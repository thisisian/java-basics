package Chapter3;

import java.util.EmptyStackException;

public interface MyStackInterface<T> {
    void push(T item);
    T pop() throws EmptyStackException;
    T peek() throws EmptyStackException;
    boolean isEmpty();
}
