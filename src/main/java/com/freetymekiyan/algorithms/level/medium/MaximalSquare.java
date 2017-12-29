package com.freetymekiyan.algorithms.level.medium;

/**
 * 221. Maximal Square
 * <p>
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
 * Company Tags: Apple, Airbnb, Facebook
 * Tags: Dynamic Programming
 * Similar Problems: (H) Maximal Rectangle
 */
public class MaximalSquare {

    /**
     * DP. Space optimized.
     * Only the previous row and previous column are needed.
     * So reduce space usage to an array and an integer.
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int r = matrix.length;
        int c = matrix[0].length;
        int[] row = new int[c + 1];
        int topLeft = 0;
        int maxLen = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int temp = row[j + 1]; // Store top left for the next iteration.
                if (matrix[i][j] == '1') {
                    row[j + 1] = Math.min(row[j + 1], Math.min(row[j], topLeft)) + 1;
                    if (row[j + 1] > maxLen) maxLen = row[j + 1]; // Update max side length.
                } else {
                    row[j + 1] = 0;
                }
                topLeft = temp;
            }
        }
        return maxLen * maxLen;
    }

    /**
     * DP.
     * Finding the largest square's area is the same as finding the edge length.
     * The recurrence relation here is:
     * Suppose the largest edge length formed by current grid at matrix[i-1][j-1] is dp[i][j].
     * If matrix[i - 1][j - 1] = 1, dp[i][j] = min(dp[i-1][j], dp[i][j], dp[i][j-1]) + 1.
     * If matrix[i - 1][j - 1] = 0, dp[i][j] = 0.
     * <p>
     * To understand the recurrence relation, draw a matrix.
     * Iff grid [i,j] is 1 and it's just a corners of other 1s, can the length expand.
     */
    public int maximalSquare2(char[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        int r = matrix.length;
        int c = r == 0 ? 0 : matrix[0].length;
        int[][] dp = new int[r + 1][c + 1]; // First row and column are all 0.
        int maxLen = 0;
        for (int i = 1; i <= r; i++) { // Traverse dp array. Note the equal sign.
            for (int j = 1; j <= c; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    // Update dp[i][j] and the max length.
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return maxLen * maxLen; // Return AREA here.
    }
}