package com.freetymekiyan.algorithms.level.medium;

/**
 * 91. Decode Ways
 * <p>
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * <p>
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * <p>
 * The number of ways decoding "12" is 2.
 * <p>
 * Company Tags: Microsoft, Uber, Facebook
 * Tags: Dynamic Programming, String
 */
public class DecodeWays {

    /**
     * DP. Bottom-up. Space Optimized.
     * O(n) Time, O(1) Space.
     * Don't need an array since we only need previous two results.
     * Must keep the previous two results up-to-date during iteration.
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int pre = 1; // Previous decode ways. Initialized as 1 cuz d(2) = d(1) + d(0) if decode-able.
        int cur = s.charAt(0) == '0' ? 0 : 1; // Current decode ways. Initialized according to first character.
        for (int i = 2; i <= s.length(); i++) { // i is the length of the string.
            int oneD = Integer.valueOf(s.substring(i - 1, i)); // Last 1 digit.
            int twoD = Integer.valueOf(s.substring(i - 2, i)); // Last 2 digits.
            int temp = cur; // IMPORTANT! Store current value temporarily. Otherwise cur will be updated.
            cur = (oneD != 0 ? cur : 0) + (10 <= twoD && twoD <= 26 ? pre : 0);
            pre = temp;
        }
        return cur;
    }

    /**
     * DP. Bottom-up.
     * O(n) Time, O(n) Space.
     * Suppose the length of the String is n.
     * Recurrence relation:
     * number of decode ways at length n related to number of decode ways at previous lengths.
     * Decode the single last digit, if the digit is not 0, then ways[n] += ways[n-1].
     * Decode the last two digits, if possible, then ways[n] += ways[n-2].
     * Base cases:
     * ways[0] = 1, means only one way when string length is 0. If it is 0, ways[2] can be wrong.
     * ways[1] = 0 or 1, depending on the only digit is 0 or not.
     */
    public int numDecodings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] ways = new int[len + 1];
        ways[0] = 1; // Think about ways[2] when code2 is valid, ways[0] should be 1.
        ways[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= len; i++) {
            int oneDigit = Integer.valueOf(s.substring(i - 1, i));
            int twoDigits = Integer.valueOf(s.substring(i - 2, i));
            ways[i] = (oneDigit != 0 ? ways[i - 1] : 0) + (10 <= twoDigits && twoDigits <= 26 ? ways[i - 2] : 0);
        }
        return ways[len];
    }
}