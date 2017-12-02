package com.freetymekiyan.algorithms.level.easy;

/**
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 * <p>
 * Example:
 * Given num = 16, return true. Given num = 5, return false.
 * <p>
 * Follow up: Could you solve it without loops/recursion?
 * <p>
 * Tags: Bit Manipulation
 * Similar Problems: (E) Power of Two, (E) Power of Three
 */
public class PowerOfFour {

    /**
     * Power of 4 has only a single bit 1 on the highest bit.
     * Plus, this single bit should be on odd digit.
     * So create a number that has 1 on each and every digit, which is 0x55555555.
     * num & 0x55555555 should not be 0.
     */
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num & 0x55555555) != 0;
    }

    /**
     * Math
     * 4^n % 3 = 1
     * Prove by induction.
     * Suppose 4^n - 1 can be divided by 3.
     * 4^(n + 1) - 1 = 4 * 4^n - 1 = 3 * 4^n + (4^n - 1)
     * So (4^n - 1) % 3 = 0
     */
    public boolean isPowerOfFourB(int num) {
        return (num & (num - 1)) == 0 && num % 3 == 1;
    }
}
