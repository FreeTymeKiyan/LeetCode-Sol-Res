package com.freetymekiyan.algorithms.level.medium;

/**
 * 361. Bomb Enemy
 * <p>
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

    private static final char WALL = 'W';
    private static final char ENEMY = 'E';
    private static final char EMPTY = '0';

    /**
     * DP.
     * Avoid duplicate searching for number of enemies on the cell's left and top.
     * Since they will be already traversed.
     * So maintain an integer of number of enemy hits of current row, rowHits.
     * Maintain an array of integers of numbers of enemy hits of each column, colHits of size grid[0].length.
     * Recurrence Relation:
     * rowHits = from this cell to the end of row or WALL. Re-calculate when j = 0 or left cell is a 'W'.
     * colHits = from this cell to the bottom of column or WALL. Re-calculate when i = 0 or top cell is a 'W'.
     * When the cell is 'E' (empty), we can place a bomb.
     * Update the max with rowHits + colHits[j] if bigger.
     */
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null) {
            return 0;
        }
        int m = grid.length;
        int n = m == 0 ? 0 : grid[0].length;
        int maxEnemies = 0;
        int rowHits = 0;
        int[] colHits = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == WALL) { // Note that WALL can be skipped.
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
                    maxEnemies = Integer.max(rowHits + colHits[j], maxEnemies);
                }
            }
        }
        return maxEnemies;
    }
}