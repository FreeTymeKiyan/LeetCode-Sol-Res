package com.freetymekiyan.algorithms.level.easy;

/**
 * 461. Hamming Distance
 * <p>
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * <p>
 * Given two integers x and y, calculate the Hamming distance.
 * <p>
 * Note:
 * 0 ≤ x, y < 231.
 * <p>
 * Example:
 * <p>
 * Input: x = 1, y = 4
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * | 1   (0 0 0 1)
 * | 4   (0 1 0 0)
 * |        ↑   ↑
 * <p>
 * The above arrows point to positions where the corresponding bits are different.
 * <p>
 * Related Topics: Bit Manipulation
 * Similar Questions: (E) Number of 1 Bits, (M) Total Hamming Distance
 */
public class HammingDistance {

    /**
     * Bit Manipulation.
     * By def., hamming distance is the number of positions where
     * the corresponding bits are different.
     * XOR can generate a result where different bits produce 1, same bits produce 0.
     * This can distinguish different bits from same bits.
     * Then we just need to count the number of 1s after XOR.
     * One-liner: Integer.bitCount(x ^ y);
     */
    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int d = 0;
        while (xor != 0) {
            if ((xor & 1) == 1) d++;
            xor >>= 1;
        }
        return d;
    }
}