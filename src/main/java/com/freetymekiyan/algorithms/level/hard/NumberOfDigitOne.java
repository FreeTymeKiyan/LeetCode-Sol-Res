package com.freetymekiyan.algorithms.level.hard;

/**
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to
 * n.
 *
 * For example:
 * Given n = 13, Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 *
 * Hint:
 *
 * Beware of overflow.
 *
 * Tags: Math
 * Similar Problems: (E) Factorial Trailing Zeroes
 */
public class NumberOfDigitOne {

    /**
     * e.g n = 3141592, m = 100, a = 31415, b = 92
     * hundreds-digit of n is 1 for 3142 times, each has a 100 long streak, (a / 10 * 100) + 100
     * when m = 1000, a = 3141, b = 592
     * thousands-digit of n is 1 for 315 times, each has a 1000 long streak
     * since the digit is 1, the very last streak isn't 100 but only 592 + 1 = 593 numbers, (a / 10 * 1000) + (b + 1)
     * so the case is different when current digit is 0 or 1 or >= 2
     * (a + 8) / 10 is the number of full streaks (if current digit is 0 or 1, there won't be a full streak)
     * a % 10 == 1 tells whether to add a partial streak (add partial streak only if current digit is 1)
     */
    public int countDigitOne(int n) {
        int res = 0;
        for (long m = 1; m <= n; m *= 10)
            res += (n / m + 8) / 10 * m + (n / m % 10 == 1 ? n % m + 1 : 0);
        return res;
    }
}
