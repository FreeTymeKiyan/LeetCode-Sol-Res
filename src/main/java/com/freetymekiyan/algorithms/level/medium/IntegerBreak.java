package com.freetymekiyan.algorithms.level.medium;

/**
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those
 * integers. Return the maximum product you can get.
 * <p>
 * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 * <p>
 * Note: You may assume that n is not less than 2 and not larger than 58.
 * <p>
 * Hint:
 * <p>
 * There is a simple O(n) solution to this problem.
 * You may check the breaking results of n ranging from 7 to 10 to discover the regularities.
 * <p>
 * Tags: Dynamic Programming, Math
 */
public class IntegerBreak {

    /**
     * Math.
     * If factor x >= 4, we can decompose it into 2 and (x-2), 2 * (x-2)= 2x - 4 > x.
     * So the final product will be larger, means that the product won't exceed 4.
     */
    public int integerBreak(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int product = 1;
        while (n > 4) {
            product *= 3;
            n -= 3;
        }
        return product * n;
    }
}
