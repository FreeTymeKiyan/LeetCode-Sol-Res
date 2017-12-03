package com.freetymekiyan.algorithms.level.hard;

import java.util.Arrays;

/**
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
     * Maximal rectangle area at row i and column j is: [right(i,j) - left(i,j)] * height(i,j).
     * left is the left boundary of current row and previous row.
     * left(i,j) = max(left(i-1,j), curLeft)
     * curLeft is the left boundary candidate of current row.
     * <p>
     * right is the right boundary of current row. Actually is index + 1 for easy implementation.
     * right(i,j) = min(right(i-1,j), curRight)
     * curRight is the right boundary candidate of current row.
     * <p>
     * height is how many 1s, including current 1, from top to current position.
     * height(i,j) = height(i-1,j) + 1, if matrix[i][j]=='1'.
     * height(i,j) = 0, if matrix[i][j]=='0'.
     * <p>
     * Implementation:
     * Initialize left array and height as all zeroes, right array as the column length.
     * For each row in the matrix, update height, left, right arrays.
     * Then compute the max area.
     * Stop when all rows are looped through.
     * https://discuss.leetcode.com/topic/6650/share-my-dp-solution
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
        int maxA = 0;
        for (int i = 0; i < m; i++) {
            // Compute height (can do this from either side).
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            // Compute left (must from left to right).
            int curLeft = 0; // Index of leftmost 1 of current row.
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], curLeft);
                } else {
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }
            // Compute right (must from right to left).
            int curRight = n; // Index+1 of rightmost 1 of current row.
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], curRight);
                } else {
                    right[j] = n; // Like reset. Make sure right[j] >= curRight.
                    curRight = j;
                }
            }
            // Compute the area of rectangle (can do this from either side).
            for (int j = 0; j < n; j++) {
                maxA = Math.max(maxA, (right[j] - left[j]) * height[j]);
            }
        }
        return maxA;
    }
}
