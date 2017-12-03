package com.freetymekiyan.algorithms.level.easy;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is
 * valid.
 * <p>
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * <p>
 * Company Tags: Google, Airbnb, Facebook, Twitter, Zenefits, Amazon, Microsoft, Bloomberg
 * Tags: Stack, String
 * Similar Problems: (M) Generate Parentheses, (H) Longest Valid Parentheses, (H) Remove Invalid Parentheses
 */
public class ValidParentheses {

    /**
     * Stack.
     * Use stack to check pair.
     * Whenever there is a valid pair, pop from stack since it is already valid.
     * Quick check: If string length not even, return false.
     * Create a stack.
     * For each character c in the string given:
     * | If c is a left paren, push to stack.
     * | Else if stack is not empty, and c matches with the peek:
     * |   Pop the left paren from stack.
     * | Else return false.
     * After the check, the stack should be empty because all pairs are popped.
     * Otherwise return false.
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        if (s.length() % 2 != 0) { // String length must be even.
            return false;
        }
        Deque<Character> stk = new ArrayDeque<>();
        for (Character c : s.toCharArray()) {
            if ("({[".indexOf(c) != -1) { // Push left parens.
                stk.push(c);
            } else if (!stk.isEmpty() && isMatch(stk.peek(), c)) {
                stk.pop();
            } else {
                return false;
            }
        }
        return stk.isEmpty();
    }

    private boolean isMatch(char c1, char c2) {
        return (c1 == '(' && c2 == ')') || (c1 == '{' && c2 == '}') || (c1 == '[' && c2 == ']');
    }

    /**
     * Stack.
     * Push right parens onto stack instead of left ones.
     * This way match function can be saved.
     * When it is a right paren:
     * | If the stack is empty, return false since there is no matching left.
     * | If paren popped from stack is different from c, doesn't match, return false.
     * Finally, return true if the stack is empty. Otherwise false.
     */
    public boolean isValidB(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        String left = "({[";
        String right = ")}]";
        for (char c : s.toCharArray()) {
            int index = left.indexOf(c);
            if (index != -1) { // Is left paren.
                stack.push(right.charAt(index)); // Push right.
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void testExamples() {
        Assert.assertTrue(isValid("()"));
        Assert.assertTrue(isValid("()[]{}"));
        Assert.assertFalse(isValid("([)]"));
        Assert.assertTrue(isValid("[({(())}[()])]"));
        Assert.assertFalse(isValid("a[a(a{a(a(.)a)a}x[a(a)v]w)q]z"));
    }
}
