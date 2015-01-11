/**
 * Follow up for "Unique Paths":
 * 
 * Now consider if some obstacles are added to the grids. How many unique paths
 * would there be?
 * 
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * 
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * 
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * The total number of unique paths is 2.
 * 
 * Note: m and n will be at most 100.
 * 
 * Tags: Array, DP
 */
class UniquePaths2 {
    public static void main(String[] args) {
        int[][] obstacleGrid = new int[3][3];
        obstacleGrid[1][1] = 1;
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }
    
    /**
     * DP, bottom-up approach
     * build from end point to start point
     * for the grid paths at the rth row and cth column
     * paths[r][c] = obstacleGrid[r][c] == 1 ? 0 
     * : paths[r + 1][c] + paths[r][c + 1];
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null) return 0;
        int m = obstacleGrid.length;
        if (m == 0) return 0;
        int n = obstacleGrid[0].length;
        int[][] paths = new int[m + 1][n + 1];
        
        paths[m - 1][n] = 1;
        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                paths[r][c] = obstacleGrid[r][c] == 1 ? 0 : paths[r + 1][c] + paths[r][c + 1];
            }
        }
        return paths[0][0];
    }
}