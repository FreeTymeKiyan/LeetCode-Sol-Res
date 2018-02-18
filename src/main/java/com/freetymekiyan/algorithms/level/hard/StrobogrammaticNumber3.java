package com.freetymekiyan.algorithms.level.hard;

/**
 * 248. Strobogrammatic Number III
 * <p>
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
 * <p>
 * For example,
 * Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.
 * <p>
 * Note:
 * Because the range might be a large number, the low and high numbers are represented as string.
 * <p>
 * Related Topics: Math, Recursion
 * Similar Questions: (E) Strobogrammatic Number, (M) Strobogrammatic Number II
 */
public class StrobogrammaticNumber3 {

    private static final char[][] PAIRS = {{'0', '0'}, {'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}};

    /**
     * DFS.
     * Construct stro numbers from low.length() to high.length().
     * Add stro pairs from outside.
     * When left > right, add eligible count.
     */
    public int strobogrammaticInRange(String low, String high) {
        int[] count = new int[1];
        for (int i = low.length(); i <= high.length(); i++) {
            dfs(i, 0, new char[i], Long.parseLong(low), Long.parseLong(high), count);
        }
        return count[0];
    }

    private void dfs(int i, int left, char[] number, long low, long high, int[] count) {
        if (left > i - 1 - left) {
            String s = new String(number);
            long n = Long.parseLong(s);
            if (low <= n && n <= high) {
                count[0]++;
            }
            return;
        }

        for (char[] pair : PAIRS) {
            if (pair[0] == '0' && (left == 0 && i != 1)) {
                continue;
            }
            if (left == i - 1 - left && pair[0] != pair[1]) {
                continue;
            }
            number[left] = pair[0];
            number[i - 1 - left] = pair[1];
            dfs(i, left + 1, number, low, high, count);
        }
    }
}