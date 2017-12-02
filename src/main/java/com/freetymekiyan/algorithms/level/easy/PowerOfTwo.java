package com.freetymekiyan.algorithms.level.easy;

/**
 * Given an integer, write a function to determine if it is a power of two.
 * <p>
 * Tags: Math, Bit Manipulation
 * Similar Problems: (E) Number of 1 Bits, (E) Power of Three, (E) Power of Four
 */
public class PowerOfTwo {

    /**
     * 2's power only has a single 1 at the highest bit.
     * So n & (n - 1) should be 0.
     * Check also if n is positive.
     */
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     * One-liner using Integer.bitCount because only a single bit should be 1 for 2's power.
     */
    public boolean isPowerOfTwoB(int n) {
        return n > 0 && Integer.bitCount(n) == 1;
    }
}
