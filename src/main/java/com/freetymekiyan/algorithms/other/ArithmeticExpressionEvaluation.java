package com.freetymekiyan.algorithms.other;

import java.util.Stack;

/**
 * Dijkstra Two-Stack Algorithm for Expression Evaluation
 * Assumes that the expression is fully parenthesized, with numbers and characters separated by whitespace.
 */
public class ArithmeticExpressionEvaluation {
    public static void main(String[] args) {
        ArithmeticExpressionEvaluation aee = new ArithmeticExpressionEvaluation();
        int evaluate = aee.evaluate("(1+((2+3)*(4*5)))");
        System.out.println(evaluate);
    }

    public int evaluate(String expr) {
        if (expr == null) return 0;
        Stack<Character> ops = new Stack<>();
        Stack<Integer> vals = new Stack<>();
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '(') ;
            else if (c == '+') ops.push(c);
            else if (c == '-') ops.push(c);
            else if (c == '*') ops.push(c);
            else if (c == '/') ops.push(c);
            else if (c == ')') {
                int v = vals.pop();
                char opr = ops.pop();
                if (opr == '+') v = vals.pop() + v;
                else if (opr == '-') v = vals.pop() - v;
                else if (opr == '*') v = vals.pop() * v;
                else if (opr == '/') v = vals.pop() / v;
                vals.push(v);
            } else {
                vals.push(c - '0');
            }
        }
        return vals.size() == 1 ? vals.pop() : -1;
    }
}
