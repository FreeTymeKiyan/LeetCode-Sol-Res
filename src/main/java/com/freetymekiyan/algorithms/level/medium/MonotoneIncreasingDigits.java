package com.freetymekiyan.algorithms.level.medium;

/**
 * 738. Monotone Increasing Digits
 * <p>
 * Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing
 * digits.
 * <p>
 * (Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x
 * <= y.)
 * <p>
 * Example 1:
 * Input: N = 10
 * Output: 9
 * Example 2:
 * Input: N = 1234
 * Output: 1234
 * Example 3:
 * Input: N = 332
 * Output: 299
 * Note: N is an integer in the range [0, 10^9].
 * <p>
 * Related Topics: Greedy
 * Similar Questions: (M) Remove K Digits
 */
public class MonotoneIncreasingDigits {

    /**
     * Greedy.
     * Observations:
     * 1. 9 is the largest number with monotone increasing digits(MID) of single digits.
     * 2. Go from MSB to LSB, if we keep current digit d, the digits following with length l should >= dd..d (l ds).
     * | For 332, if we keep the first 3, 32 should >= 33, but not. We should reduce 3 to 2.
     * |   Replacing the rest digits 9s we got 299.
     * | If 3 is 1, say 110, if we keep the first 1, 10 should >= 11, but not. 1 should reduce to 0.
     * |   Replacing the rest digits with 9s we got 99.
     * 3. Conversely, if we reduce d to d-1, then replace all following digits to 9, can generate a good candidate.
     * | 332 -> 299 works. 1234 -> 0999, does not. Why? 1234 itself is MID already.
     * 4. We shall skip the parts that are already MID. Then reduce d to d-1, and replace all following digits to 9.
     * | 1234444423 -> 1234444399, not working, why? 4 > 3. For all the 4s before 3, they need to be smaller.
     * 5. Find the beginning of the duplicate sequence before current digit, start replacing from there.
     */
    public int monotoneIncreasingDigits(int N) {
        char[] digits = Integer.toString(N).toCharArray();
        int i = 1;
        while (i < digits.length && digits[i - 1] <= digits[i]) {
            i++;
        }
        while (0 < i && i < digits.length && digits[i - 1] > digits[i]) {
            i--;
            digits[i]--;
        }
        for (int j = i + 1; j < digits.length; j++) {
            digits[j] = '9';
        }
        return Integer.parseInt(String.valueOf(digits));
    }

}