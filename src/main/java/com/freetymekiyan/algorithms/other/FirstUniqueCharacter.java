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
     */
    public char findFirstUnique(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("Input string must exist or be non-empty.");
        }
        int[] counts = new int[128];
        char[] chs = s.toCharArray();
        for (char c : chs) {
            counts[c]++;
        }
        for (char c : chs) {
            if (counts[c] == 1) {
                return c;
            }
        }
        throw new IllegalStateException("Input string doesn't have a unique character.");
    }
}