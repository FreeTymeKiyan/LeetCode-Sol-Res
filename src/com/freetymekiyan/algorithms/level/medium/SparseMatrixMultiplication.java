package com.freetymekiyan.algorithms.level.medium;

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
     * Loop through A row by row.
     * In each row, multiply the value in column with row in B.
     * Add it to the position of result.
     * Since the matrix is sparse, we can skip zeros.
     */
    public int[][] multiply(int[][] A, int[][] B) {
        int mA = A.length;
        int nA = A[0].length;
        int nB = B[0].length;
        int[][] res = new int[mA][nB];
        for (int i = 0; i < mA; i++) {
            for (int j = 0; j < nA; j++) {
                if (A[i][j] != 0) { // Skip zero in A
                    for (int k = 0; k < nB; k++) {
                        if (B[j][k] != 0) { // Skip zero in B
                            res[i][k] += A[i][j] * B[j][k];
                        }
                    }
                }
            }
        }
        return res;
    }
}
