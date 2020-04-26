import Chapter3.MinStack;
import Chapter3.QueueOfStacks;
import Chapter3.StackOfPlates;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class Chapter3Test {

    @Test
    void QueueOfStacks() {
        QueueOfStacks<Integer> q = new QueueOfStacks<>();
        assertThrows(NoSuchElementException.class, q::remove);
        assertThrows(NoSuchElementException.class, q::peek);
        q.add(0);
        assertEquals(0, q.remove());
        q.add(1);
        q.add(2);
        q.add(3);
        assertEquals(1, q.peek());
        assertEquals(1, q.remove());
        q.add(4);
        q.add(5);
        q.add(6);
        for (int i = 2; i <= 6; ++i) {
            assertEquals(i, q.peek());
            assertEquals(i, q.remove());
        }
        assertThrows(NoSuchElementException.class, q::remove);
        assertThrows(NoSuchElementException.class, q::peek);
    }

    @Test
    void MinStack() {
        MinStack<Integer> s = new MinStack<>();
        assertThrows(EmptyStackException.class, s::pop);
        assertThrows(EmptyStackException.class, s::min);
        s.push(5);
        assertEquals(5, s.min());
        assertEquals(5, s.pop());
        assertThrows(EmptyStackException.class, s::pop);
        assertThrows(EmptyStackException.class, s::min);
        s.push(5);
        s.push(4);
        s.push(6);
        s.push(7);
        s.push(3);
        assertEquals(3, s.min());
        assertEquals(3, s.pop());
        assertEquals(4, s.min());
        assertEquals(7, s.pop());
        assertEquals(4, s.min());
        assertEquals(6, s.pop());
        assertEquals(4, s.min());
        assertEquals(4, s.pop());
        assertEquals(5, s.min());
        assertEquals(5, s.pop());
        assertThrows(EmptyStackException.class, s::pop);
        assertThrows(EmptyStackException.class, s::min);
    }

    @Test
    void StackOfPlates() {
        StackOfPlates<Integer> s = new StackOfPlates<>(2);
        assertThrows(EmptyStackException.class, s::pop);
        assertThrows(EmptyStackException.class, s::peek);
        for (int i = 0; i < 10; ++i) {
            s.push(i);
        }
        assertEquals(9, s.pop());
        assertEquals(8, s.pop());
        assertEquals(5, s.popAt(2));
        assertEquals(7,s.pop());
        assertEquals(6,s.pop());
        assertEquals(4,s.pop());

        s.push(4);
        s.push(5);
        s.push(6);

        assertEquals(5, s.popAt(2));
        assertEquals(4, s.popAt(2));
        assertEquals(6,s.pop());
        assertEquals(3,s.pop());
    }

}
