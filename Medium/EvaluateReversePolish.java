import java.util.*;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, /. Each operand may be an integer or another
 * expression.
 *
 * Some examples:
 *   ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 *   ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 *
 * Tags: Stack
 */
class EvaluateReversePolish {

    public static void main(String[] args) {
        // String[] tokens = {"2", "1", "+", "3", "*"};
        // String[] tokens = {"4", "13", "5", "/", "+"};
        String[] tokens = {"3","-4","+"};
        System.out.println(evalRPN(tokens));
    }

    /**
     * assign a priority for each operators
     * use a stack to store them
     * note the numbers can be negative
     * 
     * We evaluate the expression left-to-right and push operands onto the
     * stack until we encounter an operator, which we pop the top two values
     * from the stack. We then evaluate the operator, with the values as
     * arguments and push the result back onto the stack.
     */
    public static int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;
        Stack<String> s = new Stack<String>();
        int len = tokens.length;
        for (int i = 0; i < len; i++) {
            String cur = tokens[i];
            if (isOperator(cur)) {
                int t2 = Integer.valueOf(s.pop());
                int t1 = Integer.valueOf(s.pop());
                int res = calculate(t1, t2, cur);
                s.push(res + "");
            } else s.push(cur);
        }
        return Integer.valueOf(s.peek());
    }
    
    /**
     * Helper function to check whether a token is operator or not
     */
    private boolean isOperator(String c) {
        if (c.equalsIgnoreCase("+")) return true;
        if (c.equalsIgnoreCase("-")) return true;
        if (c.equalsIgnoreCase("*")) return true;
        if (c.equalsIgnoreCase("/")) return true;
        return false;
    }
    
    /**
     * Helper function to do calculation
     */
    private int calculate(int t1, int t2, String operator) {
        int res = 0;
        if (operator.equalsIgnoreCase("+")) res = t1 + t2;
        else if (operator.equalsIgnoreCase("-")) res = t1 - t2;
        else if (operator.equalsIgnoreCase("*")) res = t1 * t2;
        else if (operator.equalsIgnoreCase("/")) res = t1 / t2;
        return res;
    } 
}
