/**
 * Given a m x n grid filled with non-negative numbers, find a path from top
 * left to bottom right which minimizes the sum of all numbers along its path.
 * 
 * Note: You can only move either down or right at any point in time.
 * 
 * Tags: Array, DP
 */
class MinPathSum {
    
    public static void main(String[] args) {
        // int[][] grid = new int[][]{{0}};
        int[][] grid = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(grid));
    }
    
    /**
     * DP. bottom-up
     * row by row, use an array to store values
     */
    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[] rowSum = new int[n];
            
        rowSum[0] = grid[0][0];
        for (int col = 1; col < n; col++) rowSum[col] = rowSum[col - 1] + grid[0][col];
        for (int row = 1; row < m; row++) {
            rowSum[0] += grid[row][0];
            for (int col = 1; col < n; col++) {
                rowSum[col] = Math.min(rowSum[col - 1], rowSum[col]) + grid[row][col];
            }
        }
        return rowSum[n - 1];
    }
    
    private static void printMatrix(int[][] grid){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------");
    }
    
}