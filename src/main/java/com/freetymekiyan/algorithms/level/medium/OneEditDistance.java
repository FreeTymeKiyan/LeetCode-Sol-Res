package com.freetymekiyan.algorithms.level.medium;

/**
 * 163. One Edit Distance
 * <p>
 * Given two strings S and T, determine if they are both one edit distance apart.
 * <p>
 * Company Tags: Snapchat, Uber, Facebook, Twitter
 * Tags: String
 * Similar Problems: (H) Edit Distance
 */
public class OneEditDistance {

    /**
     * Edit distance: add, remove, or replace.
     * Three possible situations:
     * 1) s and t are of same length, replace
     * 2) delete 1 char from s
     * 3) delete 1 char from t
     * <p>
     * Implementation:
     * Get the lengths of two strings, m and n.
     * Compare m and n. Always put the shorter string as the first parameter.
     * If the difference of their lengths are larger than 1, return false.
     * Loop through the shorter string to find a different char.
     * If found and they are of same length, the rest of them should be the same.
     * If found and they are of different length, the rest of shorter string, including that char,
     * should be the same as the rest of longer string, excluding that char.
     * If all chars are the same, it can only be the last character in longer string.
     * Return true iff longer string is one character longer.
     */
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m > n) {
            return isOneEditDistance(t, s); // Short length first.
        }
        if (n - m > 1) return false; // Pruning. Can't be over 1 letter different.
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (m == n) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                } else if (m < n) {
                    return s.substring(i).equals(t.substring(i + 1));
                }
            }
        }
        return m != n; // If all characters are the same, will pass previous checks.
    }
}