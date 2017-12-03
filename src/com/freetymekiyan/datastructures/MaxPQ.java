/**
 * Priority Queue API
 * Generic items are comparable
 * <p>
 * MaxPQ(): create an empty priority queue
 * MaxPQ(Key[] a): create a priority queue with given keys
 * void insert(Key v): insert a key into the priority queue
 * Key delMax(): return and remove the largest key
 * boolean isEmpty(): is the priority queue empty?
 * Key max(): return the largest key
 * int size(): number of entries in the priority queue
 * <p>
 * binary heap-ordered array: start from 1, if children is k, parent is k / 2
 * if parent is k, children are 2k and 2k + 1
 *
 * @author kiyan
 * @since 5/25/16.
 */
public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int N;

    /**
     * add no-arg constructor and use resizing array
     */
    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    public MaxPQ(Key[] a) {
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    /**
     * should throw exception when empty
     */
    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        sink(1);
        pq[N + 1] = null; // prevent loitering
        return max;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Key max() {
        return pq[1];
    }

    public int size() {
        return N;
    }

    /**
     * heap promotion: when child's key becomes larger than it's parent
     * solution: exchange key in child with key in parent, repeat util heap order restored
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void exch(int i, int k) {
        Key temp = pq[i];
        pq[i] = pq[k];
        pq[k] = temp;
    }

    private boolean less(int i, int k) {
        return pq[i].compareTo(pq[k]) < 0;
    }

    private boolean greater(int i, int k) {
        return pq[i].compareTo(pq[k]) > 0;
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }
}
