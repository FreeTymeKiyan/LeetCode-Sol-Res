/**
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * 
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the
 * previous row.
 * 
 * For example,
 * 
 * Consider the following matrix:
 * 
 * [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * Given target = 3, return true.
 * 
 * Tags: Array, Binary Search
 */
class Search2DMatrix {
    public static void main(String[] args) {
        Search2DMatrix s = new Search2DMatrix();
        int[][] matrix = { {1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        System.out.println(s.searchMatrix(matrix, 0));
        System.out.println(s.searchMatrix(matrix, 1));
        System.out.println(s.searchMatrix(matrix, 2));
        System.out.println(s.searchMatrix(matrix, 11));
        System.out.println(s.searchMatrix(matrix, 15));
        System.out.println(s.searchMatrix(matrix, 34));
        System.out.println(s.searchMatrix(matrix, 35));
        System.out.println(s.searchMatrix(matrix, 50));
        System.out.println(s.searchMatrix(matrix, 51));
        System.out.println(s.searchMatrix(matrix, 100));
    }
    
    /**
     * Binary search to locate row, then binary search in a row
     * O(logm + logn)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int left = 0;
        int right = matrix.length - 1;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (matrix[mid][0] == target) return true;
            else if (matrix[mid][0] > target) right = mid - 1;
            else left = mid + 1;
        }
        // row index is left - 1
        if (left - 1 < 0 || left - 1 >= matrix.length) return false;
        int row = left - 1;
        left = 0;
        right = matrix[row].length - 1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (matrix[row][mid] == target) return true;
            else if (matrix[row][mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return false;
    }
    
    /**
     * n * m matrix convert to an array => matrix[x][y] => a[x * m + y]
     * an array convert to n * m matrix => a[x] =>matrix[x / m][x % m];
     * 
     * disadvantage: 1. m * n may overflow 2. / and % are expensive
     */
    public boolean searchMatrixBest(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (matrix[mid / m][mid % m] == target) return true;
            else if (matrix[mid / m][mid % m] > target) right = mid - 1;
            else left = mid + 1;
        }
        return false;
    }
    
}
