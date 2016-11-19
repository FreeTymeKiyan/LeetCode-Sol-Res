package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two sparse matrices A and B, return the result of AB.
 * <p>
 * You may assume that A's column number is equal to B's row number.
 * <p>
 * Example:
 * <p>
 * | A = [
 * |   [ 1, 0, 0],
 * |   [-1, 0, 3]
 * | ]
 * <p>
 * | B = [
 * |   [ 7, 0, 0 ],
 * |   [ 0, 0, 0 ],
 * |   [ 0, 0, 1 ]
 * | ]
 * <p>
 * |      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 * | AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 * |                   | 0 0 1 |
 * Company Tags: LinkedIn, Facebook
 * Tags: Hash Table
 */
public class SparseMatrixMultiplication {

    /**
     * Matrix multiplication is row in A multiply column in B.
     * When implement, for A[i][j], multiply a row B[j][k], 0 <= k < nB.
     * Then add result to res[i][k].
     * Skip zeros since the matrix is sparse.
     * <p>
     * Loop through A from left to right, row by row.
     * In each row, multiply the column value A[i][j] with each value in row j in B, B[j][k].
     * Add it to res[i][k].
     */
    public int[][] multiply(int[][] A, int[][] B) {
        int mA = A.length, nA = A[0].length;
        int nB = B[0].length;
        int[][] res = new int[mA][nB];

        for (int i = 0; i < mA; i++) {
            for (int j = 0; j < nA; j++) {
                if (A[i][j] == 0) {
                    continue; // Skip zeros in A.
                }
                for (int k = 0; k < nB; k++) {
                    if (B[j][k] == 0) {
                        continue; // Skip zeros in B.
                    }
                    res[i][k] += A[i][j] * B[j][k];
                }
            }
        }
        return res;
    }

    /**
     * A sparse matrix can be represented as a sequence of rows.
     * Each row is a sequence of (column-number, value) pairs of the nonzero values in the row.
     * Create result matrix.
     * Create a List array for the rows of matrix A.
     * | For each value in A:
     * |   Create a list.
     * |   If A[i][j] != 0:
     * |     Add j and A[i][j] to the list.
     * |   Set this list in the array.
     * For each list in the array:
     * | Get the list first.
     * | For each pair, get column and value.
     * |   Get value in B.
     * |   Multiply and update result.
     * Return result.
     */
    public int[][] multiplyB(int[][] A, int[][] B) {
        int m = A.length, n = A[0].length, nB = B[0].length;
        int[][] result = new int[m][nB];
        // Build list of rows for A.
        List[] indexA = new List[m];
        for (int i = 0; i < m; i++) {
            List<Integer> numsA = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (A[i][j] != 0) {
                    numsA.add(j); // Add column.
                    numsA.add(A[i][j]); // Add actual value.
                }
            }
            indexA[i] = numsA;
        }

        for (int i = 0; i < m; i++) {
            List<Integer> numsA = indexA[i];
            for (int p = 0; p < numsA.size() - 1; p += 2) {
                int colA = numsA.get(p); // Get column.
                int valA = numsA.get(p + 1); // Get actual value after.
                for (int j = 0; j < nB; j++) {
                    int valB = B[colA][j];
                    result[i][j] += valA * valB;
                }
            }
        }

        return result;
    }
}
