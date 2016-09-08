package com.freetymekiyan.algorithms.level.medium;

/**
 * Given a string s and a string t, check if s is subsequence of t.
 * <p>
 * You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length
 * ~=
 * 500,000) string, and s is a short string (<=100).
 * <p>
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of
 * the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of
 * "abcde" while "aec" is not).
 * <p>
 * Example 1:
 * s = "abc", t = "ahbgdc"
 * <p>
 * Return true.
 * <p>
 * Example 2:
 * s = "axc", t = "ahbgdc"
 * <p>
 * Return false.
 * <p>
 * Follow up:
 * If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T
 * has
 * its subsequence. In this scenario, how would you change your code?
 * <p>
 * Answer:
 * In this case, we can iterate through T once and build a list for each letter in T with its indices in increasing
 * trend. Then for each S, we check its characters one by one in the list, find the minimum index possible(Greedy,
 * Binary Search). If there is a possible index sequence for S, return true. If not, return false.
 * <p>
 * Tags: Binary Search, Dynamic Programming, Greedy
 */
public class IsSubsequence {

    /**
     * Two pointers.
     * For each character in T, check whether it's the same as current character in S.
     * If it's the same, move index in S by 1.
     * If it reaches the end of S, return true.
     * If it's not the same, continue.
     * Stop when T is fully traversed.
     */
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        int indexS = 0;
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == s.charAt(indexS)) {
                indexS++;
                if (indexS == s.length()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Recursion.
     * Recurrence relation: whether s is the subsequence of t can be divided into 2 parts:
     * 1) the first character of s is found in t
     * 2) the rest of s is the subsequence of the rest of t
     */
    public boolean isSubsequenceB(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(0) == t.charAt(i)) {
                return isSubsequenceB(s.substring(1), t.substring(i + 1));
            }
        }
        return false;
    }
}
