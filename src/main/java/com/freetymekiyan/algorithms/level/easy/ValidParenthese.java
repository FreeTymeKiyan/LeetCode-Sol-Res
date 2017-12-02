package com.freetymekiyan.algorithms.level.easy;

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string is valid.
 *
 * The brackets must close in the correct order, "()" and "()[]{}" are all
 * valid but "(]" and "([)]" are not.
 *
 * Tags: Stack, String
 */
class ValidParenthese {

    public static void main(String[] args) {
        ValidParenthese v = new ValidParenthese();
        System.out.println(v.isValid("()"));
        System.out.println(v.isValid("()[]{}"));
        System.out.println(v.isValid("([)]"));
        System.out.println(v.isValid("[({(())}[()])]"));
        System.out.println(v.isValid("a[a(a{a(a(.)a)a}x[a(a)v]w)q]z"));
    }

    /**
     * Use a stack to store the parens
     * If   left paren, push to stack
     * Elif stk is empty, return false
     * Elif matches, pop and go on
     * Else don't match, return false
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return false;
        Stack<Character> stk = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (!isParenthese(c)) continue;
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
}
