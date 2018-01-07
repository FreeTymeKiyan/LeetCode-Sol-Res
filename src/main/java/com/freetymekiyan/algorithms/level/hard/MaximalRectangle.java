package com.freetymekiyan.algorithms.level.hard;

import java.util.Arrays;

/**
 * 85. Maximal Rectangle
 * <p>
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * <p>
 * For example, given the following matrix:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Return 6.
 * <p>
 * Company Tags: Facebook
 * Tags: Array, Hash Table, Stack, Dynamic Programming
 * Similar Problems: (H) Largest Rectangle in Histogram, (M) Maximal Square
 */
public class MaximalRectangle {

    /**
     * DP.
     * Three matrices: left, right, and height.
     * Height is number of continuous 1's that end at j.
     * Left is the left boundary of this height. The actual value is an index.
     * Right is the right boundary of this right. The actual value is index + 1.
     * The rectangle area of this height at row i and column j is: [right(i,j) - left(i,j)] * height(i,j).
     * <p>
     * Recurrence relations:
     * left(i,j) = max(left(i-1,j), leftBound)
     * leftBound is the leftmost 1 of this height at current row.
     * All left boundaries are initialized as 0, which is the leftmost possible.
     * <p>
     * right(i,j) = min(right(i-1,j), rightBound)
     * rightBound is the rightmost 1 of this height at current row + 1.
     * All right boundaries are initialized as n, which is the rightmost possible.
     * <p>
     * height(i,j) = height(i-1,j) + 1, if matrix[i][j]=='1'.
     * height(i,j) = 0, if matrix[i][j]=='0'.
     * <p>
     * Implementation:
     * Initialize left array and height as all zeroes, right array as the column length.
     * For each row in the matrix, update height, left, right arrays.
     * Then compute the area and record the maximum.
     * Stop when all grids are done.
     * https://discuss.leetcode.com/topic/6650/share-my-dp-solution
     * <p>
     * Note that the rectangle area we get is not the maximum at each grid, rather, it's the area of the rectangle of
     * the maximum possible height.
     * Why does that covers the maximum rectangle?
     * Because:
     * If the max rectangle has only 1 column, the bottom grid will have the max area.
     * If the max rectangle has > 1 columns, the bottom row will have the max area.
     */
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] height = new int[n];
//        Arrays.fill(left, 0);
        Arrays.fill(right, n);
//        Arrays.fill(height, 0);
        int max = 0;
        for (int i = 0; i < m; i++) {
            // Compute height (can do this from either side).
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            // Compute left boundaries (must from left to right).
            int leftBound = 0; // Index of leftmost 1 of current row.
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], leftBound);
                } else {
                    left[j] = 0;
                    leftBound = j + 1;
                }
            }
            // Compute right boundaries (must from right to left).
            int rightBound = n; // Index + 1 of rightmost 1 of current row.
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], rightBound);
                } else {
                    right[j] = n; // Like reset. Make sure right[j] >= curRight.
                    rightBound = j;
                }
            }
            // Compute the area of rectangle (can do this from either side).
            for (int j = 0; j < n; j++) {
                max = Math.max(max, (right[j] - left[j]) * height[j]);
            }
        }
        return max;
    }
}