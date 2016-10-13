package com.freetymekiyan.algorithms.level.medium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
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

    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private WallsAndGates w;

    /**
     * BFS.
     * Search from gate to rooms.
     */
    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if (rooms[i][j] == 0) {
                    bfs(rooms, i, j);
                }
            }
        }
    }

    private void bfs(int[][] rooms, int i, int j) {
        boolean[][] visited = new boolean[rooms.length][rooms[0].length];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        visited[i][j] = true;
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] pos = queue.poll();
                if (distance < rooms[pos[0]][pos[1]]) {
                    rooms[pos[0]][pos[1]] = distance;
                }
                for (int l = 0; l < DIRS.length; l++) {
                    int nextI = pos[0] + DIRS[l][0];
                    int nextJ = pos[1] + DIRS[l][1];
                    if (0 <= nextI && nextI < rooms.length
                        && 0 <= nextJ && nextJ < rooms[0].length
                        && !visited[nextI][nextJ] && rooms[nextI][nextJ] != -1) {
                        queue.add(new int[]{nextI, nextJ});
                        visited[nextI][nextJ] = true;
                    }
                }
            }
            distance++;
        }
    }

    @Before
    public void setUp() {
        w = new WallsAndGates();
    }

    @Test
    public void testExamples() {
        int[][]
            input =
            {{2147483647, -1, 0, 2147483647}, {2147483647, 2147483647, 2147483647, -1},
             {2147483647, -1, 2147483647, -1}, {0, -1, 2147483647, 2147483647}};
        w.wallsAndGates(input);
        for (int[] row :
            input) {
            System.out.println(Arrays.toString(row));
        }
    }

    @After
    public void tearDown() {
        w = null;
    }
}
