package com.freetymekiyan.algorithms.level.medium;

/**
 * You are given an n x n 2D matrix representing an image.
 * <p>
 * Rotate the image by 90 degrees (clockwise).
 * <p>
 * Follow up:
 * Could you do this in-place?
 * <p>
 * Tags: Array
 */
class RotateImage {
  public static void main(String[] args) {
    int[][] matrix = {{1, 2}, {4, 3}};
    new RotateImage().rotate(matrix);
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  /**
   * Get the length of matrix
   * Do level by level, each level edge by edge
   * In-place solutions overwrites original matrix
   */
  public void rotate(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return;
    }
    int n = matrix.length;
    for (int i = 0; i < n / 2; i++) {
      for (int j = i; j < n - i - 1; j++) {
        int tmp = matrix[i][j]; // Save in a temporary variable
        matrix[i][j] = matrix[n - j - 1][i]; // First column
        matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1]; // Last row
        matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1]; // Last column
        matrix[j][n - i - 1] = tmp;
      }
    }
  }
}
