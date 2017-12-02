package com.freetymekiyan.algorithms.level.easy;

/**
 * Given an integer, write a function to determine if it is a power of three.
 * <p>
 * Follow up:
 * Could you do it without using any loop / recursion?
 * <p>
 * Tags: Math
 * Similar Problems: (E) Power of Two, (E) Power of Four
 */
public class PowerOfThree {

    int maxPow3 = (int) Math.pow(3, (int) (Math.log(Integer.MAX_VALUE) / Math.log(3)));

    /**
     * Check if n's modular of 3 is zero.
     * If yes, divide n by 3 and check again.
     * Stop till n is 1.
     */
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        while (n > 1 && n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    /**
     * Recursive version.
     */
    public boolean isPowerOfThreeRecursive(int n) {
        return n > 0 && n == 1 && n % 3 == 0 && isPowerOfThreeRecursive(n / 3);
    }

    /**
     * Find the max power of 3 within int range.
     * It should be divisible by all power of 3s.
     */
    public boolean isPowerOfThreeB(int n) {
        return n > 0 && maxPow3 % n == 0;
    }
}
