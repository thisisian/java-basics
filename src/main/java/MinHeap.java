import java.util.ArrayList;

public class MinHeap<T extends Comparable<T>> {

    ArrayList<T> heap = new ArrayList<>();

    public void insert(T item) {
        int idx = heap.size();
        heap.add(item);
        while (idx > 0) {
            var parentIdx = parentIndex(idx);
            T parent = heap.get(parentIdx);
            if (item.compareTo(parent) < 0) {
                heap.set(idx, parent);
                heap.set(parentIdx, item);
                idx = parentIdx;
            } else {
                break;
            }
        }
    }

    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    public T extractMinimum() {
        if (heap.size() == 0) {
            return null;
        }

        var minimum = heap.get(0);

        heap.set(0, heap.get(heap.size()-1));
        heap.remove(heap.size()-1);
        int idx = 0;
        while (idx < heap.size()) {
            var bubbleElem = heap.get(idx);
            var leftChildIdx = leftChildIndex(idx);
            var rightChildIdx = rightChildIndex(idx);
            if (leftChildIdx >= heap.size()) {
                break;
            }

            var leftElem = heap.get(leftChildIdx);
            if (rightChildIdx >= heap.size()) {
                if (bubbleElem.compareTo(leftElem) > 0) {
                    heap.set(leftChildIdx, bubbleElem);
                    heap.set(idx, leftElem);
                    idx = leftChildIdx;
                } else {
                    break;
                }
            } else {
                var rightElem = heap.get(rightChildIdx);
                if (bubbleElem.compareTo(leftElem) > 0 && leftElem.compareTo(rightElem) < 0) {
                    heap.set(leftChildIdx, bubbleElem);
                    heap.set(idx, leftElem);
                    idx = leftChildIdx;
                } else if (bubbleElem.compareTo(rightElem) > 0) {
                    heap.set(rightChildIdx, bubbleElem);
                    heap.set(idx, rightElem);
                    idx = rightChildIdx;
                } else {
                    break;
                }
            }
        }
        return minimum;
    }

    private static int parentIndex(int index) {
        return (index-1) / 2;
    }

    private static int leftChildIndex(int index) {
        return index*2+1;
    }

    private static int rightChildIndex(int index) {
        return index*2+2;
    }
}
