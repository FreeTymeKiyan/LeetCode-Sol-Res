/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 *
 * Integers in each column are sorted in ascending from top to bottom.
 *
 * For example,
 *
 * Consider the following matrix:
 *
 * [ [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30] ]
 *
 * Given target = 5, return true.
 *
 * Given target = 20, return false.
 *
 * Tags: Divide and Conquer, Binary Search
 *
 * Similar Problems: (M) Search a 2D Matrix
 */
public class SearchA2DMatrix2 {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }

        int row = 0;
        int col = matrix[row].length - 1;

        while (col >= 0 && row <= matrix.length - 1) {
            if (target == matrix[row][col]) {
                return true;
            } else if (target < matrix[row][col]) {
                col--;
            } else if (target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }
}
