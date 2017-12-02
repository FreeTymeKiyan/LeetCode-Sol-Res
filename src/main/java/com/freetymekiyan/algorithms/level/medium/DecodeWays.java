package com.freetymekiyan.algorithms.level.medium;

/**
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
     * DP. Bottom-up. O(n) Time, O(n) Space.
     * Suppose the length of the String is n.
     * Recurrence relation:
     * number of decode ways at length n related to number of decode ways at previous lengths.
     * Decode the single last digit, then ways[n] += ways[n-1].
     * Decode the last two digits, if possible, then ways[n] += ways[n-2].
     * Base cases:
     * ways[0] = 1, means only one way when string length is 0.
     * ways[1] = 0 or 1, depending on the only digit is 0 or non-zero.
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] ways = new int[len + 1];
        // Base cases
        ways[0] = 1; // Think about ways[2] when code2 is valid, ways[0] should be 1.
        ways[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= len; i++) {
            int code1 = Integer.valueOf(s.substring(i - 1, i));
            int code2 = Integer.valueOf(s.substring(i - 2, i));
            ways[i] = (code1 != 0 ? ways[i - 1] : 0) + (code2 <= 26 && code2 > 9 ? ways[i - 2] : 0);
        }
        return ways[len];
    }

    /**
     * DP. Bottom-up. Optimized. O(n) Time, O(1) Space.
     * Don't need an array since we only need previous two results.
     * Remember to update those results.
     */
    public int numDecodingsB(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int prev1 = 1;
        int prev2 = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= len; i++) { // i is the length of the string.
            int code1 = Integer.valueOf(s.substring(i - 1, i)); // Last 1 digit.
            int code2 = Integer.valueOf(s.substring(i - 2, i)); // Last 2 digits.
            int temp = prev2; // Store prev2 first. Otherwise it will be overwritten.
            prev2 = (code1 != 0 ? prev2 : 0) + (code2 <= 26 && code2 > 9 ? prev1 : 0);
            prev1 = temp;
        }
        return prev2;
    }
}
