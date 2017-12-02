package com.freetymekiyan.algorithms.level.medium;

/**
 * Given a 2D matrix <i>matrix</>, find the sum of the elements inside the rectangle defined by its upper left corner
 * (row1,
 * col1) and lower right corner (row2, col2).
 * <p>
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which
 * contains sum = 8.
 * <p>
 * Example:
 * Given matrix = [
 * [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]
 * ]
 * <p>
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * <p>
 * Note:
 * You may assume that the matrix does not change.
 * There are many calls to sumRegion function.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 * <p>
 * Tags: Dynamic Programming
 * Similar Problems: (E) Range Sum Query - Immutable, (H) Range Sum Query 2D - Mutable
 */
public class RangeSumQuery2DImmutable {
    public class NumMatrix {

        /**
         * Pre-compute sum from (0, 0) to current point in another matrix
         * Sum from (0, 0) to (row, col) is A + B - C + D
         * 1. A: sum from (0, 0) to (row - 1, col)
         * 2. B: sum from (0, 0) to (row, col - 1)
         * 3. C: sum from (0, 0) to (row - 1, col - 1)
         * 4. D: sum from (row ,col) to (row, col), which is matrix[row][col] itself
         * C is added twice in A and B
         */
        private int[][] dp;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
            // Make dp one row and one column bigger than matrix to avoid trivial range validation
            dp = new int[matrix.length + 1][matrix[0].length + 1];
            for (int r = 0; r < matrix.length; r++) {
                for (int c = 0; c < matrix[0].length; c++) {
                    dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1] + matrix[r][c] - dp[r][c];
                }
            }
        }

        /**
         * A: Sum of (0, 0) to (row2, col2) is dp[row2 + 1][col2 + 1]
         * B: Sum of (0, 0) to (row1 - 1, col2) is dp[row1][col2 + 1]
         * C: Sum of (0, 0) to (row2, col1 - 1) is dp[row2 + 1][col1]
         * D: Sum of (0, 0) to (row1 - 1, col1 - 1) is dp[row1][col1]
         * Range sum is A - B - C + D (D is subtracted twice in B and C)
         */
        public int sumRegion(int row1, int col1, int row2, int col2) {
            return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
        }
    }

// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);
}
