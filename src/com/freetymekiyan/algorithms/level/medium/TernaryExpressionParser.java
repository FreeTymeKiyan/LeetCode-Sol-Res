package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression. You can
 * always assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F (T and F represent
 * True and False respectively).
 * <p>
 * Note:
 * <p>
 * The length of the given string is ≤ 10000.
 * Each number will contain only one digit.
 * The conditional expressions group right-to-left (as usual in most languages).
 * The condition will always be either T or F. That is, the condition will never be a digit.
 * The result of the expression will always evaluate to either a digit 0-9, T or F.
 * Example 1:
 * <p>
 * Input: "T?2:3"
 * <p>
 * Output: "2"
 * <p>
 * Explanation: If true, then result is 2; otherwise result is 3.
 * Example 2:
 * <p>
 * Input: "F?1:T?4:5"
 * <p>
 * Output: "4"
 * <p>
 * Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
 * <p>
 * "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
 * -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
 * -> "4"                                    -> "4"
 * Example 3:
 * <p>
 * Input: "T?T?F:5:3"
 * <p>
 * Output: "F"
 * <p>
 * Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
 * <p>
 * "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
 * -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
 * -> "F"                                    -> "F"
 * <p>
 * Company Tags: Snapchat
 * Tags: Depth-first Search, Stack
 * Similar Problems: (M) Mini Parser
 */
public class TernaryExpressionParser {

    /**
     * Stack.
     * Use a stack to help with evaluation.
     * Push possible values into stack.
     * Whenever there is a '?', pop two values from stack.
     * Then get the char before '?', push evaluate result onto stack.
     * <p>
     * Create an array deque as stack.
     * For i from the end of the expression to the start:
     * | Get current character as c.
     * | If c is digit or T/F, push onto stack.
     * | If c is '?':
     * |   Pop two values from stack, left and right.
     * |   Get the char before '?':
     * |     If its 'T', return first value. Else return right.
     * | If c is ':', just skip.
     * Return the top of stack.
     */
    public String parseTernary(String expression) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                stack.push(c);
            } else if (c == 'T' || c == 'F') {
                stack.push(c);
            } else if (c == '?') {
                char left = stack.pop();
                char right = stack.pop();
                stack.push(expression.charAt(--i) == 'T' ? left : right);
            }
        }
        return String.valueOf(stack.peek());
    }

    /**
     * Recursive.
     * The key is to find the correct colon to split the expression.
     * Note that one '?' match with one ':' for a non-nested expression.
     * So just need to find the ':' without any matching '?'.
     * Use a counter. When '?', counter++; when '.', counter--.
     * There is one '?' for current expression.
     * So if count is 0, remember the colon's index.
     */
    public String parseTernaryB(String expression) {
        return String.valueOf(dfs(expression.toCharArray(), 0, expression.length() - 1));
    }

    /**
     * DFS.
     * Recurrent relation:
     * The value of the input expression depends on the values of sub-expressions.
     * If T, return the value of left sub-expression.
     * If F, return right sub-expression.
     * Base case:
     * If the expression has only 1 value, return the value.
     * Complete problem:
     * If there is no sub-problem, deal with base case.
     * If there is sub-problem, find the colon to split.
     * Return by the first character. If 'T', return left value. Otherwise right value.
     */
    private char dfs(char[] chs, int start, int end) {
        if (start == end) {
            return chs[start];
        }
        int count = 0, i = start;
        for (; i <= end; i++) {
            if (chs[i] == '?') {
                count++;
            } else if (chs[i] == ':') {
                count--;
                if (count == 0) {
                    break;
                }
            }
        }
        return chs[start] == 'T' ? dfs(chs, start + 2, i - 1) : dfs(chs, i + 1, end);
    }
}
