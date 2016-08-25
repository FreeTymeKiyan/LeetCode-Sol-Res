package com.freetymekiyan.algorithms.level.medium;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * <p>
 * For example, given the following matrix:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Return 4.
 * <p>
 * Tags: Dynamic Programming
 * Similar: Problems (H) Maximal Rectangle
 */
public class MaximalSquare {

    /**
     * DP, 2D matrix.
     * The recurrence relation here is not easy to see.
     * Use largest square's edge length formed including current grid.
     * If the current grid is an 1,the edge length of current grid is the (minimum of its top, top-left, and left) + 1.
     * Otherwise, if its 0, the edge length is still zero.
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        int r = matrix.length;
        int c = r == 0 ? 0 : matrix[0].length;
        int[][] dp = new int[r + 1][c + 1];
        int maxLen = 0;
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return maxLen * maxLen; // Return AREA here
    }

    /**
     * DP, 1D matrix.
     * Reduce space usage to 1D matrix and an integer.
     */
    public int maximalSquareB(char[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        int r = matrix.length;
        int c = r == 0 ? 0 : matrix[0].length;
        int[] dp = new int[c + 1]; // Only need one row
        int prev = 0; // Store dp[i-1][j-1]
        int maxLen = 0;
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                int temp = dp[j]; // Store last dp[j] before update
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], dp[j]), prev) + 1;
                    maxLen = Math.max(maxLen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp; // dp[j] before udpate is the dp[i-1][j-1] for the next loop
            }
        }
        return maxLen * maxLen; // Return the AREA here
    }

}
