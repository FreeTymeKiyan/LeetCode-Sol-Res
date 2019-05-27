package com.freetymekiyan.algorithms.level.hard;

import java.util.TreeSet;

/**
 * 363. Max Sum of Rectangle No Larger Than K
 * <p>
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum
 * is no larger than k.
 * <p>
 * Example:
 * <p>
 * Input: matrix = [[1,0,1],[0,-2,3]], k = 2
 * Output: 2
 * Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
 * and 2 is the max number no larger than k (k = 2).
 * Note:
 * <p>
 * The rectangle inside the matrix must have an area > 0.
 * What if the number of rows is much larger than the number of columns?
 * <p>
 * Companies: Google, Facebook
 * <p>
 * Related Topics: Binary Search, Dynamic Programming, Queue
 */
public class MaxSumOfRectangleNoLargerThanK {

  public int maxSumSubmatrix(int[][] matrix, int target) {
    int row = matrix.length;
    if (row == 0) return 0;
    int col = matrix[0].length;
    int m = Math.min(row, col);
    int n = Math.max(row, col);
    //indicating sum up in every row or every column
    boolean colIsBig = col > row;
    int res = Integer.MIN_VALUE;
    for (int i = 0; i < m; i++) {
      int[] array = new int[n];
      // sum from row j to row i
      for (int j = i; j >= 0; j--) {
        int val = 0;
        TreeSet<Integer> set = new TreeSet<Integer>();
        set.add(0);
        //traverse every column/row and sum up
        for (int k = 0; k < n; k++) {
          array[k] = array[k] + (colIsBig ? matrix[j][k] : matrix[k][j]);
          val = val + array[k];
          //use  TreeMap to binary search previous sum to get possible result
          Integer subres = set.ceiling(val - target);
          if (null != subres) {
            res = Math.max(res, val - subres);
          }
          set.add(val);
        }
      }
    }
    return res;
  }
}