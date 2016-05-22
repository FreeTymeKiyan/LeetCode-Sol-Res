import java.util.Iterator;

/**
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that
 * support the peek() operation -- it essentially peek() at the element that will be returned by the next call to
 * next().
 *
 * Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].
 *
 * Call next() gets you 1, the first element in the list.
 *
 * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 *
 * You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.
 *
 * Hint:
 *
 * Think of "looking ahead". You want to cache the next element. Is one variable sufficient? Why or why not?
 *
 * Test your design with call order of peek() before next() vs next() before peek().
 *
 * For a clean implementation, check out Google's guava library source code.
 *
 * Follow up: How would you extend your design to be generic and work with all types, not just integer?
 *
 * Tags: Design
 *
 * Similar Problems: (M) Binary Search Tree Iterator, (M) Flatten 2D Vector, (M) Zigzag Iterator
 */
public class PeekingIterator implements Iterator<Integer> {

    private Integer next;
    private Iterator<Integer> iter;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iter = iterator;
        if (iterator.hasNext()) {
            next = iterator.next();
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        int res = next;
        next = iter.hasNext() ? iter.next() : null;
        return res;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

}
