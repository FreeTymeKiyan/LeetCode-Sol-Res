package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 286. Walls and Gates
 * <p>
 * You are given a m x n 2D grid initialized with these three possible values.
 * <p>
 * 1. -1 - A wall or an obstacle.
 * 2. 0 - A gate.
 * 3. INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that
 * the distance to a gate is less than 2147483647.
 * <p>
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled
 * with INF.
 * <p>
 * For example, given the 2D grid:
 * | INF  -1  0  INF
 * | INF INF INF  -1
 * | INF  -1 INF  -1
 * |   0  -1 INF INF
 * <p>
 * After running your function, the 2D grid should be:
 * | 3  -1   0   1
 * | 2   2   1  -1
 * | 1  -1   2  -1
 * | 0  -1   3   4
 * Company Tags: Google, Facebook
 * Tags: Breadth-first Search
 * Similar Problems: (M) Surrounded Regions, (M) Number of Islands, (H) Shortest Distance from All Buildings
 */
public class WallsAndGates {

    private static final int EMPTY = Integer.MAX_VALUE;
    private static final int GATE = 0;
    private static final int WALL = -1;
    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * BFS.
     * Search from gate to rooms.
     * As its breadth first search, it makes sure that:
     * The rooms are visited level by level.
     * So that rooms at distance 1 won't be visited until all gates are visited.
     * Also use EMPTY room to indicate whether a room has been visited.
     * <p>
     * Implementations:
     * Add all gates to the queue first (first level).
     * Update its 4 adjacent empty rooms with new distance.
     * Then add these rooms to the queue.
     * Stop when the queue is empty.
     */
    public void wallsAndGates(int[][] rooms) {
        Queue<int[]> queue = new ArrayDeque<>();
        // Add all zeros(gates) to queue.
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if (rooms[i][j] == GATE) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int r = pos[0];
            int c = pos[1];
            // Visit 4 adjacent nodes that are empty.
            if (r > 0 && rooms[r - 1][c] == EMPTY) {
                rooms[r - 1][c] = rooms[r][c] + 1; // Update distance before enqueue. Set as visited.
                queue.offer(new int[]{r - 1, c});
            }
            if (r < rooms.length - 1 && rooms[r + 1][c] == EMPTY) {
                rooms[r + 1][c] = rooms[r][c] + 1;
                queue.offer(new int[]{r + 1, c});
            }
            if (c > 0 && rooms[r][c - 1] == EMPTY) {
                rooms[r][c - 1] = rooms[r][c] + 1;
                queue.offer(new int[]{r, c - 1});
            }
            if (c < rooms[0].length - 1 && rooms[r][c + 1] == EMPTY) {
                rooms[r][c + 1] = rooms[r][c] + 1;
                queue.offer(new int[]{r, c + 1});
            }
        }
    }

    /**
     * BFS. Level-order Traversal.
     * Use queue size to pull each level.
     * Unnecessary since we can always update a grid's adjacent grids with it's value.
     */
    public void wallsAndGates2(int[][] rooms) {
        Queue<int[]> queue = new LinkedList<>();
        // Add all zeros to queue.
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if (rooms[i][j] == GATE) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) { // Level-order.
                int[] pos = queue.poll();
                for (int j = 0; j < DIRS.length; j++) {
                    int nextI = pos[0] + DIRS[j][0];
                    int nextJ = pos[1] + DIRS[j][1];
                    if (0 <= nextI && nextI < rooms.length
                            && 0 <= nextJ && nextJ < rooms[0].length
                            && rooms[nextI][nextJ] == Integer.MAX_VALUE) {
                        rooms[nextI][nextJ] = rooms[pos[0]][pos[1]] + 1;
                        queue.offer(new int[]{nextI, nextJ});
                    }
                }
            }
        }
    }
}