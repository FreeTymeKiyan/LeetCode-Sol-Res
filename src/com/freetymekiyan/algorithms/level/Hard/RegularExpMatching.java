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
 * Similar Problems:  (H) Wildcard Matching
 */
public class RegularExpMatching {

    private RegularExpMatching r;

    /**
     * DP. O(mn) Time, O(mn) Space.
     * Recurrence relation:
     * f[i][j]: if s[0..i-1] matches p[0..j-1]
     * if p[j - 1] != '*'
     * -   f[i][j] = f[i - 1][j - 1] && s[i - 1] == p[j - 1]
     * if p[j - 1] == '*', denote p[j - 2] with x
     * -   f[i][j] is true iff any of the following is true
     * -   1) "x*" repeats 0 time and matches empty: f[i][j - 2]
     * -   2) "x*" repeats >= 1 times and matches "x*x": s[i - 1] == x && f[i - 1][j]
     * '.' matches any single character
     * Base case:
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
//        for (int i = 1; i <= m; i++) { // Is false by default
//            dp[i][0] = false;
//        }
        // p[0.., j - 3, j - 2, j - 1] matches empty iff p[j - 1] is '*' and p[0..j - 3] matches empty
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j > 1 && '*' == p.charAt(j - 1) && dp[0][j - 2];
        }
        // Build matrix
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) != '*') {
                    dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || '.' == p.charAt(j - 1));
                } else {
                    // p[0] cannot be '*' so no need to check "j > 1" here
                    dp[i][j] = dp[i][j - 2] // Matches empty
                               /*
                                * If s[i-1] == a, p[j-2] == a, or p[j-2] == '.', and p[j-1] == '*'
                                * p can match s if s[0...i-2] matches p[0...j-1]
                                * Because s[i-2] already matches "p[j-2]*",
                                * "s[i-2]s[i-1]" can match "p[j-2]*" by repeating p[j-2]
                                */
                               || (s.charAt(i - 1) == p.charAt(j - 2) || '.' == p.charAt(j - 2)) && dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }

    @Before
    public void setUp() {
        r = new RegularExpMatching();
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
