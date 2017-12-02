package com.freetymekiyan.algorithms.level.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * <p>
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * <p>
 * Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * Company Tags: LinkedIn
 * Tags: Stack
 * Similar Problems: (H) Basic Calculator, (H) Expression Add Operators
 */
public class EvaluateReversePolish {

    /**
     * Stack.
     * Suppose all reverse polish are valid.
     * For each token t in tokens:
     * | If t is an operator:
     * |   Pop two numbers and do the calculation.
     * |   Push result onto the stack.
     * | Else t should be a number:
     * |   Just push t onto the stack.
     * Return the top value of stack as the result.
     */
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Deque<Integer> s = new ArrayDeque<>();
        for (int i = 0; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "+":
                    s.push(s.pop() + s.pop());
                    break;
                case "-":
                    s.push(-s.pop() + s.pop());
                    break;
                case "*":
                    s.push(s.pop() * s.pop());
                    break;
                case "/":
                    int num1 = s.pop();
                    int num2 = s.pop();
                    s.push(num2 / num1);
                    break;
                default:
                    s.push(Integer.valueOf(tokens[i]));
                    break;
            }
        }
        return s.isEmpty() ? 0 : s.peek();
    }

    @Test
    public void testExamples() {
        String[] tokens = {"3", "-4", "+"};
        Assert.assertEquals(-1, evalRPN(tokens));
        tokens =
            new String[]{"-78", "-33", "196", "+", "-19", "-", "115", "+", "-", "-99", "/", "-18", "8", "*", "-86", "-",
                         "-", "16", "/", "26", "-14", "-", "-", "47", "-", "101", "-", "163", "*", "143", "-", "0", "-",
                         "171", "+", "120", "*", "-60", "+", "156", "/", "173", "/", "-24", "11", "+", "21", "/", "*",
                         "44", "*", "180", "70", "-40", "-", "*", "86", "132", "-84", "+", "*", "-", "38", "/", "/",
                         "21", "28", "/", "+", "83", "/", "-31", "156", "-", "+", "28", "/", "95", "-", "120", "+", "8",
                         "*", "90", "-", "-94", "*", "-73", "/", "-62", "/", "93", "*", "196", "-", "-59", "+", "187",
                         "-", "143", "/", "-79", "-89", "+", "-"};
        Assert.assertEquals(165, evalRPN(tokens));
        tokens = new String[]{"2", "1", "+", "3", "*"};
        Assert.assertEquals(9, evalRPN(tokens));
        tokens = new String[]{"4", "13", "5", "/", "+"};
        Assert.assertEquals(6, evalRPN(tokens));
    }
}
