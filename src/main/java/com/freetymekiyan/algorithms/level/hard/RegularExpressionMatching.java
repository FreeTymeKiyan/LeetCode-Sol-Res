package com.freetymekiyan.algorithms.level.hard;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
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
 *L
 */
public class RegularExpressionMatching {

    private RegularExpressionMatching r;

    /**
     * DP. O(mn) Time, O(mn) Space.
     * f[i][j]: whether s[0..i-1] matches p[0..j-1]
     * Recurrence relations:
     * if p[j - 1] != '*':
     * | f[i][j] = f[i - 1][j - 1] and (s[i - 1] == p[j - 1] or p[j - 1] == '.')
     * if p[j - 1] == '*', denote p[j - 2] with x, x can be '.'
     * | f[i][j] is true iff any of the following is true:
     * |   1) "x*" repeats 0 time and matches empty: f[i][j - 2]
     * |   2) "x*" repeats >= 1 times and matches "x*x": s[i - 1] == x && f[i - 1][j]
     * '.' matches any single character
     * Base cases:
     * When s and p are both empty, match.
     * When p is empty, but s is not, don't match.
     * When s is empty, but p is not, only matches when the last of p is '*' and previous pattern also matches.
     * That's p[j-1] == '*' && f[0][j-2]
     */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        // Base cases
        dp[0][0] = true;
//        for (int i = 1; i <= m; i++) { // Is false by default.
//            dp[i][0] = false;
//        }
        for (int j = 1; j <= n; j++) {
            // Note that dp[0][1] is false no matter what.
            // "p[j-2]*" can only match empty, so dp[0][j] depends on dp[0][j-2].
            dp[0][j] = j > 1 && dp[0][j - 2] && p.charAt(j - 1) == '*';
        }
        // Build matrix
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) != '*') { // Last characters match and previous also match.
                    dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || '.' == p.charAt(j - 1));
                } else {
                    dp[i][j] = dp[i][j - 2] // "p[j-2]*" repeats 0 times and matches empty.
                               /*
                                * "p[j-2]*" repeats >= 1 times to match s[i-1].
                                * p[j-2] must match s[i-1] and s[0..i-2] match p[0..j-1].
                                * e.g., s:"acc", p:"ac*", "ac" matches "ac*".
                                */
                               || (s.charAt(i - 1) == p.charAt(j - 2) || '.' == p.charAt(j - 2)) && dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }

    @Before
    public void setUp() {
        r = new RegularExpressionMatching();
    }

    @Test
    public void testExamples() {
        Assert.assertFalse(r.isMatch("aa", "a"));
        Assert.assertTrue(r.isMatch("aa", "aa"));
        Assert.assertFalse(r.isMatch("aaa", "aa"));
        Assert.assertTrue(r.isMatch("aa", "a*"));
        Assert.assertTrue(r.isMatch("aa", ".*"));
        Assert.assertTrue(r.isMatch("ab", ".*"));
        Assert.assertTrue(r.isMatch("aab", "c*a*b"));
    }

    @After
    public void tearDown() {
        r = null;
    }
}
