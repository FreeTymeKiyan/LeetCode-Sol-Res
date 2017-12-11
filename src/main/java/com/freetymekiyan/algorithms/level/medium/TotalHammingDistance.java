package com.freetymekiyan.algorithms.level.medium;

/**
 * 477. Total Hamming Distance
 * <p>
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * <p>
 * Now your job is to find the total Hamming distance between all pairs of the given numbers.
 * <p>
 * Example:
 * Input: 4, 14, 2
 * <p>
 * Output: 6
 * <p>
 * Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
 * showing the four bits relevant in this case). So the answer will be:
 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
 * Note:
 * Elements of the given array are in the range of 0 to 10^9
 * Length of the array will not exceed 10^4.
 * <p>
 * Related Topics: Bit Manipulation
 * Similar Questions: (E) Hamming Distance
 */
public class TotalHammingDistance {

    /**
     * Count each bit in the 32-bit ints how many 1s, a and 0s, b.
     * The total hamming distance of this bit is then a * b.
     * Go through all the 32-bit to get total hamming distance of each.
     * Then they add up to the total hamming distance of the numbers.
     */
    public int totalHammingDistance(int[] nums) {
        int d = 0;
        for (int i = 0; i < 32; i++) {
            int bitCount = 0;
            for (int num : nums) {
                bitCount += ((num >> i) & 1);
            }
            d += bitCount * (nums.length - bitCount);
        }
        return d;
    }
}