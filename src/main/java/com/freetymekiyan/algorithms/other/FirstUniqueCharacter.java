package com.freetymekiyan.algorithms.other;

/**
 * Given a string, find the first unique character.
 * <p>
 * Company: Facebook
 */
public class FirstUniqueCharacter {

    /**
     * Hash table.
     * Save count of each character in a map.
     * Then find the first one that has count as 1.
     * If the count array stores count and index, another linear scan won't be needed.
     * Just scan the count array and find the character that has:
     * 1. count = 1.
     * 2. Minimum index.
     */
    public char findFirstUnique(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("Input string must exist or be non-empty.");
        }
        int[][] counts = new int[256][2];
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            counts[chs[i]][0]++;
            counts[chs[i]][1] = i;
        }

        int minIdx = Integer.MAX_VALUE;
        for (int[] c : counts) {
            if (c[0] == 1 && c[1] < minIdx) {
                minIdx = c[1];
            }
        }
        if (minIdx == Integer.MAX_VALUE)
            throw new IllegalStateException("Input string doesn't have a unique character.");
        return chs[minIdx];
    }
}