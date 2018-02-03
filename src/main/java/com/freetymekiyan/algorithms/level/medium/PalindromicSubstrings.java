package com.freetymekiyan.algorithms.level.medium;

/**
 * 647. Palindromic Substrings
 * <p>
 * Given a string, your task is to count how many palindromic substrings in this string.
 * <p>
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist of
 * same characters.
 * <p>
 * Example 1:
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * Example 2:
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 * Note:
 * The input string length won't exceed 1000.
 * <p>
 * Related Topics: String, Dynamic Programming
 * Similar Questions: (M) Longest Palindromic Substring, (M) Longest Palindromic Subsequence, (M) Palindromic Substrings
 */
public class PalindromicSubstrings {

    /**
     * DP.
     * Regard a palindrome string as 2 parts:
     * 1. The 2 chars at both ends.
     * 2. The inner substring.
     * So a substring from i to j is a palindrome if:
     * 1. The two chars are the same, s[i] == s[j].
     * 2. The inner string is a palindrome, which means either length <= 3 or substring from i+1 j-1 is palindrome.
     * Use a 2D array dp[i][j] to remember if substring from i to j is palindrome.
     * dp[i][j] = (s[i] == s[j]) && ((j - i < 3) || dp[i + 1][j - 1])
     * Whenever there is a palindrome, the count increments by 1.
     */
    public int countSubstrings(String s) {
        if (s == null) return 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;
        for (int i = n - 1; i >= 0; i--) { // i is the starting index.
            for (int j = i; j < n; j++) { // j is the ending index. Must be with the range i has travelled.
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && ((j - i < 3) || dp[i + 1][j - 1]);
                if (dp[i][j]) count++;
            }
        }
        return count;
    }

    /**
     * Brute-force.
     * Check if each char or 2 chars can be the center of a palindrome.
     */
    public int countSubstrings2(String s) {
        if (s == null) return 0;

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count = extendPalindrome(s, i, i, count); // 1 char at the center.
            count = extendPalindrome(s, i, i + 1, count); // 2 chars at the center.
        }

        return count;
    }

    private int extendPalindrome(String s, int left, int right, int count) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }
}