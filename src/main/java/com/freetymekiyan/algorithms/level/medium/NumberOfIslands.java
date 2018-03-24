package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * 200. Number of Islands
 * <p>
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

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * BFS. DFS.
     * Start from the top-left corner of the grid.
     * Go through each grid row by row and check if it is a land.
     * If it is not, skip.
     * If it is, BFS / DFS to find the whole island.
     * Mark the island as '0' when finished.
     * Number of islands then increments by 1.
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '0') { // Not an island
                    continue;
                }
                bfs(grid, i, j);
                // dfs(grid, i, j);
                // dfsRecursive(grid, i, j);
                count++;
            }
        }
        return count;
    }

    /**
     * BFS.
     * Set the starting grid to '0' to mark it as visited.
     * Enqueue to start BFS.
     * While queue is not empty:
     * Get a grid from the queue.
     * Add it's 4-adjacent grids to queue if they are '1'.
     * Mark grid as '0' when enqueuing.
     * Thus an island will be all marked as '0' when done.
     */
    private void bfs(char[][] grid, int i, int j) {
        Queue<int[]> queue = new ArrayDeque<>();
        grid[i][j] = '0';
        queue.offer(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int[] dir : DIRS) {
                int row = p[0] + dir[0];
                int col = p[1] + dir[1];
                // Within range and not visited.
                if (row >= 0 && row < grid.length && col >= 0 && col < grid[row].length
                        && grid[row][col] == '1') {
                    grid[row][col] = '0';
                    queue.offer(new int[]{row, col});
                }
            }
        }
    }

    /**
     * DFS. Iterative.
     */
    private void dfs(char[][] grid, int i, int j) {
        Deque<int[]> stack = new ArrayDeque<>();
        grid[i][j] = '0';
        stack.push(new int[]{i, j});
        while (!stack.isEmpty()) {
            int[] pos = stack.pop();
            for (int[] dir : DIRS) {
                int row = pos[0] + dir[0];
                int col = pos[1] + dir[1];
                if (row >= 0 && row < grid.length && col >= 0 && col < grid[row].length && grid[row][col] == '1') {
                    grid[row][col] = '0';
                    stack.push(new int[]{row, col});
                }
            }
        }
    }

    /**
     * DFS. Recursive.
     * Base case:
     * If out of the grid or is not land, return.
     * Recurrence:
     * If it is an island, set it to '0' as visited.
     * Then recursively search the 4 adjacent neighbors.
     */
    private void dfsRecursive(char[][] grid, int i, int j) {
        // Out of range or not land or visited.
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length
                || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0'; // Set to 0 can both remove this land and set to visited.
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    /**
     * Union find.
     * Find the # of connected components.
     * Union find can mark the grids.
     * But to find out # of connected components, we must understand that:
     * 1. The maximum possible # of connected components is # of 1's.
     * 2. Whenever there is a valid union, the count of 1 decreases.
     * So we can initialize a count as the # of ones, and decrement it after union.
     * In the end we return the count.
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
        public int[] id;

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

    /**
     * Union Find without a separate class.
     * Number of islands = Number of one's - Number of unions.
     */
    public int numIslandsUnionFind2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int numOfOnes = 0;
        int[] numOfUnions = new int[1];
        int[] ids = new int[m * n];
        for (int i = 0; i < ids.length; i++) ids[i] = i;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == '0') continue;
                numOfOnes++;
                int p = r * n + c;
                if (r > 0 && grid[r - 1][c] == '1') {
                    int q = p - n; // Previous row, minus number of chars a row, which is n.
                    union(ids, p, q, numOfUnions);
                }
                if (r + 1 < m && grid[r + 1][c] == '1') {
                    int q = p + n; // Next row, minus number of chars a row, which is n.
                    union(ids, p, q, numOfUnions);
                }
                if (c > 0 && grid[r][c - 1] == '1') {
                    int q = p - 1; // Previous column, just minus 1.
                    union(ids, p, q, numOfUnions);
                }
                if (c + 1 < n && grid[r][c + 1] == '1') {
                    int q = p + 1; // Next column, just plus 1.
                    union(ids, p, q, numOfUnions);
                }
            }
        }
        return numOfOnes - numOfUnions[0];
    }

    private void union(int[] ids, int p, int q, int[] numOfUnions) {
        int pRoot = find(ids, p);
        int qRoot = find(ids, q);
        if (pRoot == qRoot) return;
        ids[pRoot] = qRoot;
        numOfUnions[0]++; // Increment number of unions only when a union really happened.
    }

    private int find(int[] ids, int id) {
        while (ids[id] != id) {
            ids[id] = ids[ids[id]];
            id = ids[id];
        }
        return id;
    }
}