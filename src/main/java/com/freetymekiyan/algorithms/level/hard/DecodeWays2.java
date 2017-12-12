package com.freetymekiyan.algorithms.level.hard;

/**
 * 639. Decode Ways II
 * <p>
 * A message containing letters from A-Z is being encoded to numbers using the following mapping way:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers
 * from 1 to 9.
 * <p>
 * Given the encoded message containing digits and the character '*', return the total number of ways to decode it.
 * <p>
 * Also, since the answer may be very large, you should return the output mod 10^9 + 7.
 * <p>
 * Example 1:
 * Input: "*"
 * Output: 9
 * Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
 * Example 2:
 * Input: "1*"
 * Output: 9 + 9 = 18
 * Note:
 * The length of the input string will fit in range [1, 10^5].
 * The input string will only contain the character '*' and digits '0' - '9'.
 * <p>
 * Related Topics: Dynamic Programming
 * Similar Questions: (M) Decode Ways
 */
public class DecodeWays2 {

    private static final int M = (int) (Math.pow(10, 9) + 7);

    /**
     * DP, Bottom Up.
     * State:
     * # of ways to decode string's substring from [0, i], ways[i].
     * Base case:
     * ways[0] = 1. Only 1 way to decode empty string.
     * ways[1] = single character possible decodings.
     * Later also won't mess up when calculate ways[2].
     * Since if ways[0] = 0, ways[2] would be 0, which is not true.
     * Recurrence Relation:
     * ways[i] can divide in 2 ways:
     * | 1. ways[i-1] and the last character.
     * | 2. ways[i-2] and the last two characters.
     * Note that the values can easily overflow due to large numbers product.
     * Modular 10^9 + 7 at each step, during calculation and right before returning.
     */
    public int numDecodings(String s) {
        long[] ways = new long[s.length() + 1];
        ways[0] = 1;
        ways[1] = getNumDecodings(s.charAt(0));
        for (int i = 2; i < ways.length; i++) {
            ways[i] += (ways[i - 1] * getNumDecodings(s.charAt(i - 1))) % M;
            ways[i] += (ways[i - 2] * getNumDecodings(s.charAt(i - 2), s.charAt(i - 1))) % M;
        }
        return (int) (ways[s.length()] % M);
    }

    /**
     * DP, Optimized Space.
     * Reduce space usage from O(n) array to O(1) variables
     * since we are only using the previous two values to calculate.
     */
    public int numDecodings2(String s) {
        long first = 1;
        long second = getNumDecodings(s.charAt(0));
        for (int i = 2; i < s.length() + 1; i++) {
            long current = (second * getNumDecodings(s.charAt(i - 1))) % M;
            current = (current + (first * getNumDecodings(s.charAt(i - 2), s.charAt(i - 1)) % M)) % M;
            first = second;
            second = current;
        }
        return (int) second;
    }

    /**
     * Number of decodings of one single character.
     * If '*', it can be any one between 1-9, 9 ways.
     * If '0', it cannot be decoded, 0.
     * Else just 1.
     */
    private int getNumDecodings(char single) {
        if (single == '*') return 9;
        if (single == '0') return 0;
        return 1;
    }

    /**
     * Number of decodings of two characters.
     * If both are '*', the first '*' can be either 1 or 2, 9 + 6 = 15.
     * If first is '*', the other is digit:
     * | If the digit is <= 6, * can be 1 or 2, return 2.
     * | If the digit is > 6, * can only be 1, return 1.
     * If first is '1', and second is '*', '*' can be 1-9, return 9.
     * | If second is not '*', return 1.
     * If first is '2', and second is '*', '*' can be 1-6, return 6.
     * | If second is not '*', second can only be 1-6.
     * | If second is within the range, return 1.
     */
    private int getNumDecodings(char first, char second) {
        int ways = 0;
        if (first == '*') {
            if (second == '*') {
                ways = 15;
            } else {
                ways = (second - '0' <= 6 ? 2 : 1);
            }
        } else if (first == '1') {
            if (second == '*') ways = 9;
            else ways = 1;
        } else if (first == '2') {
            if (second == '*') ways = 6;
            else ways = (second - '0' <= 6 ? 1 : 0);
        }
        return ways;
    }
}
