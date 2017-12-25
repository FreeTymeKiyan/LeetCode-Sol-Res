package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 311. Sparse Matrix Multiplication
 * <p>
 * Given two sparse matrices A and B, return the result of A * B.
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
        int rA = A.length, cA = A[0].length;
        int cB = B[0].length;
        int[][] res = new int[rA][cB];

        for (int i = 0; i < rA; i++) {
            for (int j = 0; j < cA; j++) {
                if (A[i][j] == 0) {
                    continue; // Skip zeros in A.
                }
                for (int k = 0; k < cB; k++) { // Multiply
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
    public int[][] multiply2(int[][] A, int[][] B) {
        int rA = A.length, cA = A[0].length, cB = B[0].length;
        int[][] result = new int[rA][cB];
        // Build list of rows for A.
        List<Integer>[] indexA = new List[rA];
        for (int i = 0; i < rA; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < cA; j++) {
                if (A[i][j] != 0) {
                    row.add(j); // Add column.
                    row.add(A[i][j]); // Add actual value.
                }
            }
            indexA[i] = row;
        }

        for (int i = 0; i < rA; i++) {
            List<Integer> row = indexA[i];
            for (int j = 0; j < row.size() - 1; j += 2) {
                int colA = row.get(j); // Get column.
                int valA = row.get(j + 1); // Get actual value after.
                for (int k = 0; k < cB; k++) {
                    result[i][k] += valA * B[colA][k];
                }
            }
        }

        return result;
    }
}