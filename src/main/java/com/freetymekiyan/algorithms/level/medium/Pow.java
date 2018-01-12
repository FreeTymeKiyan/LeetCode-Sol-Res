package com.freetymekiyan.algorithms.level.medium;

/**
 * 50. Pow(x, n)
 * <p>
 * Implement pow(x, n).
 * <p>
 * Company Tags: LinkedIn, Google, Bloomberg, Facebook
 * Tags: Binary Search, Math
 * Similar Problems: (M) Sqrt(x), (M) Super Pow
 */
public class Pow {

    /**
     * Binary Search. Math.
     * Decompose the exponent into powers of 2, so that you can keep dividing the problem in half.
     * N = 9 = 2^3 + 2^0 = 1001 in binary. Then: x^9 = x^(2^3) * x^(2^0).
     * Every time we encounter a 1 in the binary representation of N, multiply the answer with x^(2^i).
     * To handle N = Integer.MIN_VALUE, use a long (64-bit) variable.
     */
    public double myPow(double x, int n) {
        double ans = 1; // Might be a fraction.
        long absN = Math.abs((long) n); // Must convert n to long first to avoid overflow.
        while (absN > 0) {
            if ((absN & 1) == 1) { // Check lowest bit.
                ans *= x;
            }
            absN >>= 1; // Right shift a bit.
            x *= x; // x^(2^N) -> x^(2^(N+1)).
        }
        return n < 0 ? 1 / ans : ans; // Deal with n < 0.
    }
}