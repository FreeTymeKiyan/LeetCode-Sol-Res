package com.freetymekiyan.algorithms.level.medium;

import java.util.Iterator;

/**
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that
 * support the peek() operation -- it essentially peek() at the element that will be returned by the next call to
 * next().
 * <p>
 * Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].
 * <p>
 * Call next() gets you 1, the first element in the list.
 * <p>
 * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 * <p>
 * You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.
 * <p>
 * Hint:
 * <p>
 * Think of "looking ahead". You want to cache the next element.
 * Is one variable sufficient? Why or why not?
 * Test your design with call order of peek() before next() vs next() before peek().
 * For a clean implementation, check out Google's guava library source code.
 * <p>
 * Follow up: How would you extend your design to be generic and work with all types, not just integer?
 * <p>
 * Company Tags: Google, Apple, Yahoo
 * Tags: Design
 * Similar Problems: (M) Binary Search Tree Iterator, (M) Flatten 2D Vector, (M) Zigzag Iterator
 */
public class PeekingIterator implements Iterator<Integer> {

    private Integer next;
    private Iterator<Integer> iter;

    /**
     * Peeking iterator is based on normal iterator.
     * Just a wrapper class.
     * Use a variable to cache the next element.
     */
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iter = iterator;
        if (iterator.hasNext()) {
            next = iterator.next();
        }
    }

    /**
     * Just return the cached element.
     */
    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    /**
     * Return next value.
     * If iterator still has more elements, update next with next.
     * If iterator don't have any more, set next to null.
     */
    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        int res = next;
        next = iter.hasNext() ? iter.next() : null;
        return res;
    }

    /**
     * Just check next.
     */
    @Override
    public boolean hasNext() {
        return next != null;
    }

}
