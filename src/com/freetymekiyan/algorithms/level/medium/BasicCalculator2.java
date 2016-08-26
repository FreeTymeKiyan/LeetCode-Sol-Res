package com.freetymekiyan.algorithms.level.medium;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * <p>
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer
 * division should truncate toward zero.
 * <p>
 * You may assume that the given expression is always valid.
 * <p>
 * Some examples:
 * "3+2*2" = 7
 * " 3/2 " = 1
 * " 3+5 / 2 " = 5
 * Note: Do not use the eval built-in library function.
 * <p>
 * Tags: String
 * Similar Problems: (H) Basic Calculator, (H) Expression Add Operators
 */
public class BasicCalculator2 {

    /**
     * The easy way would be using a stack to store high priority results and integers.
     * Then go through the stack to get final result.
     * How to remove the stack? How to do it in only one-pass?
     * We have only two priority levels, '*' '/' and '+' '-'.
     * They can separated into two results, high level value and low level value.
     * https://discuss.leetcode.com/topic/42684/explanation-for-java-o-n-time-o-1-space-solution
     */
    public int calculate(String s) {
        int lowVal = 0; // Result for low level operators like '+' and '-'
        int highVal = 0; // Result for high level operators like '*' and '/'
        int sign = 1; // 1 means '+', -1 means '-'
        int priority = 0; // 0 means '+' and '-', 1 means '*', -1 means '/'
        int num = 0; // Current number

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
                if (i == s.length() - 1 || !Character.isDigit(s.charAt(i + 1))) {
                    highVal = (priority == 0 ? num : (priority == 1 ? highVal * num : highVal / num));
                }
            } else if (c == '*' || c == '/') { // Enter high level
                priority = (c == '*' ? 1 : -1); // Update priority
                num = 0; // Reset current number
            } else if (c == '+' || c == '-') { // Enter low level
                lowVal += sign * highVal; // Accumulate high level result to low level
                sign = (c == '+' ? 1 : -1); // Update sign
                priority = 0; // Update priority
                num = 0; // Reset current number
            }
        }

        return lowVal + sign * highVal;
    }

}
