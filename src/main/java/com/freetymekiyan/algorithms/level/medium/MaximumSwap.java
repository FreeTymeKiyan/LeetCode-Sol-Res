package com.freetymekiyan.algorithms.level.medium;

/**
 * 670. Maximum Swap
 * <p>
 * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the
 * maximum valued number you could get.
 * <p>
 * Example 1:
 * Input: 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 * Example 2:
 * Input: 9973
 * Output: 9973
 * Explanation: No swap.
 * Note:
 * The given number is in the range [0, 10^8]
 * <p>
 * Related Topics: Array, Math
 * Similar Questions: (H) Create Maximum Number
 */
public class MaximumSwap {

    /**
     * Math.
     * Swap the digit that is: 1. larger. 2. max amongst all larger ones.
     * Build a digit to last index of the digit mapping to get the last position of a digit faster.
     */
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        int[] last = new int[10];
        for (int i = 0; i < digits.length; i++) { // Build a digit to index mapping.
            last[digits[i] - '0'] = i;
        }

        for (int i = 0; i < digits.length; i++) {
            for (int d = 9; d > digits[i] - '0'; d--) { // Check available larger digits to swap.
                if (last[d] > i) {
                    char tmp = digits[last[d]];
                    digits[last[d]] = digits[i];
                    digits[i] = tmp;
                    return Integer.parseInt(new String(digits));
                }
            }
        }
        return num;
    }
}
