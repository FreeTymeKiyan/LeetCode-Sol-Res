package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * Given a nested list of integers, implement an iterator to flatten it.
 * <p>
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * <p>
 * Example 1:
 * Given the list [[1,1],2,[1,1]],
 * <p>
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be:
 * [1,1,2,1,1].
 * <p>
 * Example 2:
 * Given the list [1,[4,[6]]],
 * <p>
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 * <p>
 * Company Tags: Google, Facebook, Twitter
 * Tags: Stack, Design
 * Similar Problems: (M) Flatten 2D Vector, (M) Zigzag Iterator, (M) Mini Parser
 */
public class FlattenNestedListIterator {

    /**
     * This is the interface that allows for creating nested lists.
     * You should not implement it, or speculate about its implementation
     */
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    /**
     * Stack.
     * Flatten by pushing a list of nested lists onto stack in reverse order.
     * So that we can get them in original order.
     * For hasNext(), first check if stack is empty.
     * If it is empty, return false.
     * If it is not, check whether the top is an integer.
     * If it is an integer, return true.
     * If it is a list, pop the list and add all elements to stack from back to front.
     * <p>
     * If the nested integer wraps an empty list, hasNext() should return false.
     * So we must unwrap nested integer in hasNext() to make sure.
     */
    public class NestedIterator implements Iterator<Integer> {

        private Deque<NestedInteger> stack;

        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new ArrayDeque<>();
            flatten(nestedList);
        }

        @Override
        public Integer next() {
            return hasNext() ? stack.pop().getInteger() : null;
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) { // Must put in hasNext(), otherwise cannot pass "[[]]".
                if (stack.peek().isInteger()) {
                    return true;
                }
                flatten(stack.pop().getList());
            }
            return false;
        }

        /**
         * Push list of nested integers to stack in REVERSE order.
         * So that when popping out of stack, it's the correct order.
         */
        private void flatten(List<NestedInteger> list) {
            for (int i = list.size() - 1; i >= 0; i--) {
                stack.push(list.get(i));
            }
        }
    }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
}
