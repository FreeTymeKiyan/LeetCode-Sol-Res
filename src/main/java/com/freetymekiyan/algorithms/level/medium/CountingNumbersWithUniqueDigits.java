package com.freetymekiyan.algorithms.level.medium;

/**
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10^n.
 * <p>
 * Example:
 * Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding
 * [11,22,33,44,55,66,77,88,99])
 * <p>
 * Hint:
 * <p>
 * A direct way is to use the backtracking approach.
 * Backtracking should contains three states which are (the current number, number of steps to get that number and a
 * bitmask which represent which number is marked as visited so far in the current number). Start with state (0,0,0) and
 * count all valid number till we reach number of steps equals to 10n.
 * This problem can also be solved using a dynamic programming approach and some knowledge of combinatorics.
 * Let f(k) = count of numbers with unique digits with length equals k.
 * f(1) = 10, ..., f(k) = 9 * 9 * 8 * ... (9 - k + 2) [The first factor is 9 because a number cannot start with 0].
 * <p>
 * Tags: Dynamic Programming, Backtracking, Math
 */
public class CountingNumbersWithUniqueDigits {

    /**
     * DP.
     * Think about how to use combination to build a number with unique digits.
     * f(1) = 10, f(2) = 9 * 9, f(3) = 9 * 9 * 8, f(4) = 9 * 9 * 8 * 7 ...
     * f(k) = 9 * 9 * 8 * ... * (9 - k + 2), 2 <= k <= 10
     * f(k) = f(10), k > 10
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int res = 10;
        int uniqueDigits = 9;
        int availableNums = 9;
        while (n > 1 && availableNums > 0) {
            uniqueDigits *= availableNums;
            res += uniqueDigits;
            availableNums--;
            n--;
        }
        return res;
    }

}
