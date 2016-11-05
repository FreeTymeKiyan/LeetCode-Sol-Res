package com.freetymekiyan.algorithms.level.medium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water
 * and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are
 * all surrounded by water.
 * <p>
 * Example 1:
 * <p>
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 * <p>
 * Example 2:
 * <p>
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 * <p>
 * Company Tags: Amazon, Microsoft, Google, Facebook, Zenefits
 * Tags: Depth-first Search, Breadth-first Search, Union Find
 * Similar Problems: (M) Surrounded Regions, (M) Walls and Gates, (H) Number of Islands II, (M) Number of Connected
 * Components in an Undirected Graph
 */
public class NumberOfIslands {

    private static final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private NumberOfIslands n;

    /**
     * BFS.
     * Start from the top-left corner of the grid.
     * Go through each position row by row and check if it is island.
     * If it is not, skip.
     * If it is, BFS to find the whole region.
     * Mark the region as "0" when finished.
     * Number of islands increment by 1.
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '0') { // Not an island
                    continue;
                }
                // bfsRecursive(grid, i, j);
                bfs(grid, i, j);
                count++;
            }
        }
        return count;
    }

    /**
     * Iterative.
     * Set the starting grid to '0' to mark it as visited.
     * Add it to queue to start BFS.
     * Find the region and mark all grids as '0'.
     */
    private void bfs(char[][] grid, int i, int j) {
        Queue<int[]> queue = new ArrayDeque<>();
        grid[i][j] = '0';
        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int[] dir : dirs) {
                int row = p[0] + dir[0];
                int col = p[1] + dir[1];
                // Within range and not visited.
                if (row >= 0 && row < grid.length && col >= 0 && col < grid[row].length
                    && grid[row][col] == '1') {
                    grid[row][col] = '0';
                    queue.add(new int[]{row, col});
                }
            }
        }
    }

    /**
     * Recursive.
     * Base case:
     * If out of the grid or is not an island, return.
     * If it is an island, set it to '0' as visited.
     * Then recursively search the 4 adjacent neighbors.
     */
    private void bfsRecursive(char[][] grid, int i, int j) {
        // Out of range or not going to visit.
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0'; // Set to 0 can both remove this island and set to visited.
        bfsRecursive(grid, i + 1, j);
        bfsRecursive(grid, i - 1, j);
        bfsRecursive(grid, i, j + 1);
        bfsRecursive(grid, i, j - 1);
    }

    /**
     * Union find.
     * Build an Union Find data structure first.
     * Then iterate all grids row by row.
     */
    public int numIslandsUnionFind(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind(m, n, grid); // Build union find.

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                int p = i * n + j; // Id of current island. i * column + j.
                int q; // Id of adjacent island.
                if (i > 0 && grid[i - 1][j] == '1') {
                    q = p - n;
                    uf.union(p, q);
                }
                if (i < m - 1 && grid[i + 1][j] == '1') { // i is not last row.
                    q = p + n;
                    uf.union(p, q);
                }
                if (j > 0 && grid[i][j - 1] == '1') {
                    q = p - 1;
                    uf.union(p, q);
                }
                if (j < n - 1 && grid[i][j + 1] == '1') { // j is not last column.
                    q = p + 1;
                    uf.union(p, q);
                }
            }
        }
        return uf.count;
    }

    private char[][] buildGrid(String[] val) {
        char[][] grid = new char[val.length][val[0].length()];
        for (int i = 0; i < val.length; i++) {
            for (int j = 0; j < val[i].length(); j++) {
                grid[i][j] = val[i].charAt(j);
            }
        }
        return grid;
    }

    @Before
    public void setUp() {
        n = new NumberOfIslands();
    }

    @Test
    public void testExamples() {
        char[][] grid = buildGrid(new String[]{"11110", "11010", "11000", "00000"});
        Assert.assertEquals(1, n.numIslands(grid));
        grid = buildGrid(new String[]{"11000", "11000", "00100", "00011"});
        Assert.assertEquals(3, n.numIslands(grid));
    }

    @After
    public void tearDown() {
        n = null;
    }

    /**
     * Data structure to keep track of number of connected components in the grid.
     * The count is initialized as the number of island in the grid.
     * Every time two islands are unified, the count will decrease by 1.
     */
    private class UnionFind {

        /**
         * Count of connected components.
         * Initialized as number of 1's.
         * Whenever there is an union, decrement count by 1.
         */
        public int count;
        /**
         * Connected component id array.
         * The index is mapped from 2d array.
         */
        public int[] id = null;

        public UnionFind(int m, int n, char[][] grid) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        count++;
                    }
                }
            }
            // Initialize id array by mapping each position in grid.
            id = new int[m * n];
            for (int i = 0; i < m * n; i++) {
                id[i] = i;
            }
        }

        /**
         * O(n), find the root id.
         * If p equals id[p], p is root.
         */
        public int find(int p) {
            while (p != id[p]) {
                id[p] = id[id[p]]; // Cut unnecessary branches.
                p = id[p]; // Move on to the next one
            }
            return p;
        }

        /**
         * Check whether two points are in the same edges component.
         * If connected, they will have the same id.
         */
        public boolean isConnected(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            return pRoot == qRoot;
        }

        /**
         * O(n), connect two points to the same root.
         * Every time we union two points, the count will decrease by 1.
         */
        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) {
                return;
            }
            id[pRoot] = qRoot; // Set p's root to q's
            count--; // IMPORTANT!! Decrement count by 1 after union.
        }
    }

}
