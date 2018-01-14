package com.freetymekiyan.algorithms.level.easy;

/**
 * 28. Implement strStr()
 * <p>
 * Implement strStr().
 * <p>
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * <p>
 * Company Tags: Pocket Gems, Microsoft, Apple, Facebook
 * Tags: Two Pointers, String
 * Similar Problems: (H) Shortest Palindrome
 */
public class ImplementStrStr {

    /**
     * Two Pointers.
     * For i from 0 to m-n:
     * | For j from 0 to n-1:
     * |   If characters are not the same, break
     * |   If j reaches the end of needle, return i.
     * Return -1.
     * Special case:
     * If needle is empty, no need to check , just return 0.
     */
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        int m = haystack.length();
        int n = needle.length();
        for (int i = 0; i <= m - n; i++) { // Why m-n? From m-n+1 to m-1 the characters are not enough for needle.
            for (int j = 0; j < n; j++) { // Compare with needle from the beginning.
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
                if (j == n - 1) {
                    return i;
                }
            }
        }
        return -1;
    }
}