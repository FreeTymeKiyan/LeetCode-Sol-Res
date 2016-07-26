package com.freetymekiyan.algorithms.level.hard;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * <p>
 * For example,
 * Given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 * <p>
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 * <p>
 * Tags: Dynamic Programming, String
 */
public class InterleavingString {

    private InterleavingString i;

    /**
     * DP, bottom-up, Time: O(nm), and Space: O(nm)
     * Quick check, length of s3 should be the sum of s1 and s2
     * <p>
     * If s1 provides first i characters, s2 provides first j characters, to interleave s3 at length (i + j).
     * Think about the recurrence relation, if with (i - 1) characters from s1 and j characters from s2, we can
     * interleave s3 at length (i + j - 1), the only thing we need to interleave s3 at length (i + j) is making sure
     * that ith character of s1 is the same as (i + j)th character of s3.
     * <p>
     * 1. i == 0 && j == 0, dp[i][j] is true initially
     * 2. first row, i == 0, dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1)
     * == s3.charAt(j - 1)
     * 3. first col, j == 0, dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1)
     * == s3.charAt(i - 1)
     * 4. dp[i][j] = (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j
     * - 1))|| (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
     * <p>
     * final result should dp[a][b]
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        int a = s1.length();
        int b = s2.length();
        if (s3.length() != a + b) {
            return false;
        }
        boolean[][] dp = new boolean[a + 1][b + 1];
        for (int i = 0; i <= a; i++) {
            for (int j = 0; j <= b; j++) {
                if (i == 0 && j == 0) {
                    dp[0][0] = true;
                } else if (i == 0) {
                    dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
                } else if (j == 0) {
                    dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                               || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[a][b];
    }

    /**
     * DP, space optimized, O(m)
     * space could optimize since optimal[i+1][] only depends on optimal[i][],
     */
    public boolean isInterleaveOptimal(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        int a = s1.length();
        int b = s2.length();
        if (s3.length() != a + b) {
            return false;
        }
        boolean[] dp = new boolean[b + 1];

        /*i == 0 && j == 0*/
        dp[0] = true;
        // s1 empty, s2 matches s3
        for (int j = 0; j < b; j++) {
            if (dp[j] && s2.charAt(j) == s3.charAt(j)) {
                dp[j + 1] = true;
            }
        }
        for (int i = 0; i < a; i++) { // from
            /*nothing from s2*/
            if (dp[0] && s1.charAt(i) == s3.charAt(i)) {
                dp[0] = true;
            } else {
                dp[0] = false;
            }
            for (int j = 0; j < b; j++) { // select jth char
                /*from s1 or from s2*/
                if (dp[j + 1] && (s1.charAt(i) == s3.charAt(i + j + 1)) ||
                    (dp[j] && s2.charAt(j) == s3.charAt(i + j
                                                        + 1))) { // dp[j+1] means dp[i][j+1], result of last row, dp[j] means dp[i+1][j], result of this row last col
                    dp[j + 1] = true;
                } else {
                    dp[j + 1] = false;
                }
            }
        }
        return dp[b];
    }

    @Before
    public void setUp() {
        i = new InterleavingString();
    }

    @Test
    public void testExamples() {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        Assert.assertTrue(isInterleave(s1, s2, s3));
        s3 = "aadbbbaccc";
        Assert.assertFalse(isInterleave(s1, s2, s3));
        s1 = "ab";
        s2 = "bc";
        s3 = "babc";
        Assert.assertTrue(isInterleave(s1, s2, s3));
    }

    @After
    public void tearDown() {
        i = null;
    }

}
