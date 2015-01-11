import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 * Tags: Stack, Data Structure
 */

/**
 * Standard solution, two stacks
 * a minStack to store minimums
 */
class MinStack {
    private Stack stack = new Stack<>();
    private Stack minStack = new Stack<>();

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        if (stack.pop().equals(minStack.peek())) minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

class MinStack {
    
    static class Element {
        
        final int value;
        final int min;
        
        public Element(int x, int min) {
            this.value = x;
            this.min = min;
        }
    }

    Stack<Element> s;

    /**
     * DP, one stack
     * build a static wrapper class for items in stack
     * including its value and current min
     */
    public void push(int x) {
        if (s == null) s = new Stack<Element>();
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
}

