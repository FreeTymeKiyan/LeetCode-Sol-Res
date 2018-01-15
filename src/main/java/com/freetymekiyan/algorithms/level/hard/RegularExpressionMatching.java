package com.freetymekiyan.algorithms.level.hard;

/**
 * 10. Regular Expression Matching
 * <p>
 * Implement regular expression matching with support for '.' and '*'.
 * <p>
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * <p>
 * The matching should cover the entire input string (not partial).
 * <p>
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * <p>
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 * <p>
 * Company Tags: Google, Uber, Airbnb, Facebook, Twitter
 * Tags: Dynamic Programming, Backtracking, String
 */
public class RegularExpressionMatching {

    /**
     * DP. O(mn) Time, O(mn) Space.
     * match[i][j]: whether s[0..i-1] matches p[0..j-1]
     * Recurrence relations:
     * if p[j - 1] != '*':
     * | p[j - 1] is a letter or a '.'.
     * | If it's a letter, it must be the same as s[i - 1].
     * | If it's a '.', it matches with any s[i - 1].
     * | Then if s[0...i-2] also matches p[0...j-2], it matches.
     * | match[i][j] = match[i - 1][j - 1] and (s[i - 1] == p[j - 1] or p[j - 1] == '.')
     * if p[j - 1] == '*', denote p[j - 2] with x, x can be '.'
     * | match[i][j] is true if:
     * |   1) "x*" matches empty sequence: match[i][j] = match[i][j - 2]
     * |   2) "x*" repeats >= 1 times and matches "x*x": s[i - 1] matches x && match[i - 1][j]
     * Base cases:
     * When s and p are both empty, match.
     * When p is empty, but s is not, don't match.
     * When s is empty, but p is not, only matches when p matches empty sequence.
     * | That means p[j-1] == '*' && match[0][j-2]
     */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] match = new boolean[m + 1][n + 1];
        match[0][0] = true; // Both s and p are empty.
//        for (int i = 1; i <= m; i++) { // P is empty but s is not. Is false by default.
//            match[i][0] = false;
//        }
        for (int j = 1; j <= n; j++) { // S is empty but p is not.
            // Note that match[0][1] is false even when it's a '*' since there is nothing preceding.
            // "p[j-2]*" can only match empty, so match[0][j] depends on match[0][j-2].
            match[0][j] = j > 1 && match[0][j - 2] && p.charAt(j - 1) == '*';
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) != '*') { // Last characters match and previous also match.
                    match[i][j] = match[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || '.' == p.charAt(j - 1));
                } else {
                    match[i][j] = match[i][j - 2] // "p[j-2]*" matches empty.
                            /*
                             * "p[j-2]*" repeats >= 1 times to match s[i-1].
                             * p[j-2] must be the same as s[i-1] and s[0..i-2] match p[0..j-1].
                             * e.g., s:"acc", p:"ac*", "ac" matches "ac*".
                             */
                            || (s.charAt(i - 1) == p.charAt(j - 2) || '.' == p.charAt(j - 2)) && match[i - 1][j];
                }
            }
        }
        return match[m][n];
    }
}