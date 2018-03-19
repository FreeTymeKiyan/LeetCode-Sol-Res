package com.freetymekiyan.algorithms.other;

/**
 * Given a matrix of 1's and 0's, return the number of ones in a random rectangle.
 * <p>
 * Company: Facebook
 */
public class GetNumberOfOnes {

    private final int[][] nums;

    /**
     * Save cumulative sum.
     * The sum is the number of ones.
     * A rectangle can be represented by 4 other rectangles.
     */
    public GetNumberOfOnes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Input matrix should have at least 1 row and 1 column.");
        }
        int m = matrix.length;
        int n = matrix[0].length;
        nums = new int[m][n];
        int num = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == 1) {
                    num++;
                }
                nums[r][c] = num;
            }
        }
    }

    public int getNumberOfOnes(int[] topLeft, int[] bottomRight) {
        return nums[bottomRight[0]][bottomRight[1]] - nums[bottomRight[0]][topLeft[1]] - nums[topLeft[0]][bottomRight[1]] + nums[topLeft[0]][topLeft[1]];
    }
}