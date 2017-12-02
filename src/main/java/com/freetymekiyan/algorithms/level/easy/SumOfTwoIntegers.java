package com.freetymekiyan.algorithms.level.easy;

/**
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 * <p>
 * Example:
 * Given a = 1 and b = 2, return 3.
 * <p>
 * Tags: Bit Manipulation
 * Similar Problems: (M) Add Two Numbers
 */
public class SumOfTwoIntegers {

    /**
     * For example, a = 0001, b = 0011.
     * First, we can use "and"("&") operation between a and b to find a carry.
     * carry = a & b, then carry = 0001
     * Second, we can use "xor" ("^") operation between a and b to find the different bit, and assign it to a.
     * Then, we shift carry one position left and assign it to b, b = 0010.
     * Iterate until there is no carry (or b == 0)
     */
    public int getSum(int a, int b) {
        if (b == 0) return a;

        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }

        return a;
    }

    public int getSumRecursive(int a, int b) {
        if (b == 0) return a;
        return getSumRecursive(a ^ b, (a & b) << 1);
    }

}
