package com.freetymekiyan.algorithms.level.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways
 * to group numbers and operators. The valid operators are +, - and *.
 * <p>
 * Example 1
 * Input: "2-1-1".
 * <p>
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Output: [0, 2]
 * <p>
 * Example 2
 * Input: "2*3-4*5"
 * <p>
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * Output: [-34, -14, -10, -10, 10]
 * <p>
 * Tags: Divide and Conquer
 * Similar Problems: (M) Unique Binary Search Trees II, (H) Basic Calculator, (H) Expression Add Operators
 */
public class DifferentWaysToAddParentheses {

    /**
     * Divide and Conquer.
     * Divide the input into sub-strings according to the operator: left and right.
     * Stop until there is no operator in the string, parse the integer and add it to result list.
     * Then combine left and right result lists with the operator to generate result.
     */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '*' || c == '-' || c == '+') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1, input.length()));
                for (int j = 0; j < left.size(); j++) {
                    for (int k = 0; k < right.size(); k++) {
                        res.add(calculate(c, left.get(j), right.get(k)));
                    }
                }
            }
        }
        if (res.size() == 0) {
            res.add(Integer.parseInt(input));
        }
        return res;
    }

    private int calculate(char op, int a, int b) {
        return op == '*' ? a * b : op == '+' ? a + b : a - b;
    }

}
