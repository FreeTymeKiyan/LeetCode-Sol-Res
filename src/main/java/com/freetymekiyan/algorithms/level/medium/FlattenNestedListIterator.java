package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * 341. Flatten Nested List Iterator
 * <p>
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
     * Think about the methods.
     * Normally hasNext() checks whether we have reached the end by checking against the collection size.
     * But we don't have a size here, we don't know how many nested levels are there.
     * We step forward one integer at a time.
     * So how about we put the nested integer into a dynamic collection and check whether it's empty or not?
     * Then next() only needs to check hasNext() first and return the next value.
     * Another common sense is that this kind of recursive calls are normally modelled with Stack. So let's try it.
     * <p>
     * Flatten a nested list by pushing its items onto stack in reverse order.
     * So that we can get them in the original order.
     * For hasNext(), we must account for the case when the top is a nested list with an empty nested list:
     * | Since we return one integer at a time, we should make the next integer ready on top of the stack.
     * | When the top is a list, we must flatten it until the top is an integer to make sure there are more to iterate.
     */
    public static class NestedIterator implements Iterator<Integer> {

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
            while (!stack.isEmpty() && !stack.peek().isInteger()) { // Special case "[[]]".
                flatten(stack.pop().getList());
            }
            return !stack.isEmpty();
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