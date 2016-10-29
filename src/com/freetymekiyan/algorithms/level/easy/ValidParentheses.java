package com.freetymekiyan.algorithms.level.easy;

import org.junit.Test;

import java.util.Stack;

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
public class ValidParenthese {

    /**
     * Stack.
     * Store left parentheses. 
     * If   left paren, push to stack
     * Elif stk is empty, return false
     * Elif matches, pop and go on
     * Else don't match, return false
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        Stack<Character> stk = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (!isParenthese(c)) {
                continue;
            }
            if ("({[".indexOf(c) != -1) { // push left paren
                stk.push(c);
            } else {
                if (!stk.isEmpty() && isMatch(stk.peek(), c)) {
                    stk.pop();
                } else {
                    return false;
                }
            }
        }
        return stk.isEmpty();
    }

    private boolean isParenthese(char c) {
        String parens = "(){}[]";
        return parens.indexOf(c) != -1;
    }

    private boolean isMatch(char c1, char c2) {
        return (c1 == '(' && c2 == ')') || (c1 == '{' && c2 == '}') || (c1 == '[' && c2 == ']');
    }

    @Test
    public void testExamples() {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("([)]"));
        System.out.println(isValid("[({(())}[()])]"));
        System.out.println(isValid("a[a(a{a(a(.)a)a}x[a(a)v]w)q]z"));
    }
}
