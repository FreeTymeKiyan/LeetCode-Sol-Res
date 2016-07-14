/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in
 * the diagram below).
 * 
 * The robot can only move either down or right at any point in time. The robot
 * is trying to reach the bottom-right corner of the grid (marked 'Finish' in
 * the diagram below).
 * 
 * How many possible unique paths are there?
 * 
 * Note: m and n will be at most 100.
 * 
 * Tags: Array, DP
 */
class UniquePaths {
    
    public static void main(String[] args) {
        System.out.println(uniquePathsMath(10, 20));
        System.out.println(uniquePathsDP(10, 20));
    }
    
    /**
     * Math, Combination
     * Equivalent to choose n-1 to go down from m - 1 + n - 1
     * other steps will go right
     */
    public static int uniquePathsMath(int m, int n) {
        int k = m > n ? n : m;
        int N = m + n - 2;
        double res = 1; // note that res can overflow
        for (int i = 1; i < k; i++) {
            res *= N--;
            res /= i;
        }
        return (int)res; // convert to int
    }
    
    /**
     * DP, top-down approach
     * use a matrix to store # of paths
     * base cases are, when m <= 0 or n <= 0, no way
     * when m == 1 or n == 1, only 1 way (straight down or straight right)
     */
    static int[][] paths = new int[101][101];
    public static int uniquePathsDP(int m, int n) {
        if (m <= 0 || n <= 0) return 0;
        if (m == 1 || n == 1) return 1;
        if (paths[m][n] == 0) 
            paths[m][n] = uniquePathsDP(m - 1, n) + uniquePathsDP(m, n - 1);
        return paths[m][n];
    }
    
    /**
     * DP, bottom-up approach
     */
    public static int uniquePaths(int m, int n) {
        int[][] paths = new int[m + 1][n + 1];
        paths[m - 1][n] = 1;
        for (int r = m - 1; r >= 0; r--) 
            for (int c = n - 1; c >= 0; c--) 
                paths[r][c] = paths[r + 1][c] + paths[r][c + 1];
        return paths[0][0];
    }
}