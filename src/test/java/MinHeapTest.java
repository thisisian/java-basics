import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MinHeapTest {
    @Test
    void MinHeap() {
       var heap = new MinHeap<Integer>();
       int NUM_ELEMS = 100;
       var rnd = new Random();

       for (int i = 0; i < NUM_ELEMS; ++i) {
           heap.insert(rnd.nextInt(1000));
       }

       var list = new ArrayList<Integer>();
       while (!heap.isEmpty()) {
           list.add(heap.extractMinimum());
       }
       assertTrue(isSorted(list));
    }

    boolean isSorted(List<Integer> l) {
        for (int i = 0; i < l.size()-1; ++i) {
           if (l.get(i) > l.get(i+1)) {
               return false;
           }
        }
        return true;
    }
}
