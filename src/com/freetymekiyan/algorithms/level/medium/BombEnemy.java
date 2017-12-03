package com.freetymekiyan.algorithms.level.medium;

/**
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum
 * enemies you can kill using one bomb.
 * The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the
 * wall is too strong to be destroyed.
 * Note that you can only put the bomb at an empty cell.
 * <p>
 * Example:
 * For the given grid
 * <p>
 * | 0 E 0 0
 * | E 0 W E
 * | 0 E 0 0
 * <p>
 * return 3. (Placing a bomb at (1,1) kills 3 enemies)
 * <p>
 * Company Tags: Google
 * Tags: Dynamic Programming
 */
public class BombEnemy {

    public static final char WALL = 'W';
    public static final char ENEMY = 'E';
    public static final char EMPTY = '0';

    /**
     * DP.
     * Not exactly the same idea as general DP.
     * Just avoid duplicate searching for number of enemies each row and each column.
     * For each cell from left to right at each row:
     * | Use an integer rowHits to store the # of enemies can hit at this row.
     * | Only update rowHits when first column(j==0) or there is a wall at the left cell(grid[i][j-1] == 'W').
     * | Use an integer array colHits[] to store the # of enemies can hit at this column.
     * | Only update colHits when i == 0 or grid[i - 1][j] == 'W'.
     * | If grid[i][j] is empty:
     * |   Update result. result = max(result, rowHits + colHits[j]).
     * Return max.
     */
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null) {
            return 0;
        }
        int m = grid.length;
        int n = m == 0 ? 0 : grid[0].length;
        int max = 0;
        int rowHits = 0;
        int[] colHits = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == WALL) { // Note that WALL cell can be skipped.
                    continue;
                }
                if (j == 0 || grid[i][j - 1] == WALL) {
                    rowHits = 0;
                    for (int k = j; k < n && grid[i][k] != WALL; k++) {
                        if (grid[i][k] == ENEMY) {
                            rowHits++;
                        }
                    }
                }
                if (i == 0 || grid[i - 1][j] == WALL) {
                    colHits[j] = 0;
                    for (int k = i; k < m && grid[k][j] != WALL; k++) {
                        if (grid[k][j] == ENEMY) {
                            colHits[j]++;
                        }
                    }
                }
                if (grid[i][j] == EMPTY) { // Only update when the cell is empty.
                    max = (rowHits + colHits[j]) > max ? rowHits + colHits[j] : max;
                }
            }
        }
        return max;
    }
}
