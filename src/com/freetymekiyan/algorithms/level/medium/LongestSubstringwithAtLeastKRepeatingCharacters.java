package com.freetymekiyan.algorithms.level.medium;

/**
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every
 * character in T appears no less than k times.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * s = "aaabb", k = 3
 * <p>
 * Output:
 * 3
 * <p>
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * <p>
 * Example 2:
 * <p>
 * Input:
 * s = "ababbc", k = 2
 * <p>
 * Output:
 * 5
 * <p>
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 */
public class LongestSubstringwithAtLeastKRepeatingCharacters {

    /**
     * Divide and conquer.
     * For those characters in string which repeat less than k times, we try to divide the string into two parts:
     * 1) left part: from the beginning to character's left
     * 2) right part: from the character's right to the end
     * The result is the larger one of these two parts.
     */
    public int longestSubstring(String s, int k) {
        return helper(s, 0, s.length(), k);
    }

    private int helper(String s, int start, int end, int k) {
        if (end < start) {
            return 0;
        }
        if (end - start < k) { // String length less than k
            return 0;
        }
        int[] count = new int[26]; // Character count for current string
        for (int i = start; i < end; i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] == 0) {
                continue;
            }
            if (count[i] < k) {
                for (int j = start; j < end; j++) {
                    if (s.charAt(j) == i + 'a') {
                        int left = helper(s, start, j, k);
                        int right = helper(s, j + 1, end, k);
                        return Math.max(left, right);
                    }
                }
            }
        }
        return end - start;
    }
}
