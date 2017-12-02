package com.freetymekiyan.algorithms.level.easy;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * <p>
 * Tags: Stack, Data Structure
 */
class MinStack {

    private Stack<Integer> s = new Stack<>();
    /**
     * Standard solution, two stacks.
     * A minStack to store minimums.
     */
    private Stack<Integer> minStack = new Stack<>();

    /**
     * Push x to stack.
     * After that, check min stack.
     * Push to min stack if min stack is empty or x is smaller than the top of min stack.
     */
    public void push(int x) {
        s.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) { // even smaller
            minStack.push(x);
        }
    }

    /**
     * Pop from s.
     * Check if the value is current min.
     * If it is, pop from min stack as well.
     */
    public void pop() {
        if (s.pop().equals(minStack.peek())) minStack.pop();
    }

    public int top() {
        return s.peek();
    }

    /**
     * Peek min stack to get current minimum.
     */
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * DP, one stack
 * build a static wrapper class for items in stack
 * including its value and current min
 */
class MinStackDP {

    Stack<Element> s;

    public void push(int x) {
        if (s == null) s = new Stack<>();
        int min = s.isEmpty() ? x : Math.min(x, s.peek().min);
        s.push(new Element(x, min));
    }

    public void pop() {
        s.pop();
    }

    public int top() {
        return s.peek().value;
    }

    public int getMin() {
        return s.peek().min;
    }

    /**
     * Wrapper class for element in stack
     */
    static class Element {

        final int value;
        final int min;

        public Element(int x, int min) {
            this.value = x;
            this.min = min;
        }
    }
}
