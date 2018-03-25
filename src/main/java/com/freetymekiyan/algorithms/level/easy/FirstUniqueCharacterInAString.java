package com.freetymekiyan.algorithms.level.easy;

/**
 * 387. First Unique Character in a String
 * <p>
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 * <p>
 * Examples:
 * <p>
 * s = "leetcode"
 * return 0.
 * <p>
 * s = "loveleetcode",
 * return 2.
 * Note: You may assume the string contain only lowercase letters.
 * <p>
 * Related Topics: Hash Table, String
 * Similar Questions: (M) Sort Characters By Frequency
 * Company: Facebook
 */
public class FirstUniqueCharacterInAString {

    /**
     * Hash table.
     * Save count of each character in a map.
     * Then find the first one that has count as 1.
     * If the count array stores count and index, another linear scan won't be needed.
     * Just scan the count array and find the character that has:
     * 1. count = 1.
     * 2. Minimum index.
     */
    public int firstUniqChar(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("Input string must exist or be non-empty.");
        }
        int[][] counts = new int[26][2];
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            int idx = chs[i] - 'a';
            if (counts[idx][0] == 0) {
                counts[idx][1] = i;
            }
            counts[idx][0]++;
        }

        int minIdx = Integer.MAX_VALUE;
        for (int[] c : counts) {
            if (c[0] == 1 && c[1] < minIdx) {
                minIdx = c[1];
            }
        }
        return minIdx == Integer.MAX_VALUE ? -1 : minIdx;
    }
}