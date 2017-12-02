package com.freetymekiyan.algorithms.level.hard;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers
 * and empty spaces.
 *
 * You may assume that the given expression is always valid.
 *
 * Some examples:
 *
 * "1 + 1" = 2
 *
 * "2 - 1 + 2" = 3
 *
 * "(1+(4+5+2)-3)+(6+8)" = 23
 *
 * Note: Do not use the eval built-in library function.
 *
 * Tags: Stack, Math
 *
 * Similar Problems: (M) Evaluate Reverse Polish Notation, (M) Basic Calculator II, (M) Different Ways to addRecursive
 * Parentheses, (H) Expression addRecursive Operators
 */
public class BasicCalculator {

    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int result = 0;
        int sign = 1; // sign of the current context
        int num = 0;

        Stack<Integer> stack = new Stack<>();
        stack.push(sign); // start with +1
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') { // current number
                num = num * 10 + (c - '0');
            } else if (c == '+' || c == '-') { // number finishes
                result += sign * num;
                sign = stack.peek() * (c == '+' ? 1 : -1);
                num = 0;
            } else if (c == '(') { // sign outside of parantheses
                stack.push(sign);
            } else if (c == ')') {
                stack.pop();
            }
        }
        result += sign * num; // last number
        return result;
    }
}
