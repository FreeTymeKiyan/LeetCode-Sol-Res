package com.freetymekiyan.algorithms.level.medium;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 554. Brick Wall
 * <p>
 * There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the
 * same height but different width. You want to draw a vertical line from the top to the bottom and cross the least
 * bricks.
 * <p>
 * The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick
 * in this row from left to right.
 * <p>
 * If your line go through the edge of a brick, then the brick is not considered as crossed. You need to find out how to
 * draw the line to cross the least bricks and return the number of crossed bricks.
 * <p>
 * You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously
 * cross no bricks.
 * <p>
 * Example:
 * Input:
 * | [
 * |   [1,2,2,1],
 * |   [3,1,2],
 * |   [1,3,2],
 * |   [2,4],
 * |   [3,1,2],
 * |   [1,3,1,1]
 * | ]
 * Output: 2
 * Explanation:
 * https://leetcode.com/static/images/problemset/brick_wall.png
 * <p>
 * Note:
 * The width sum of bricks in different rows are the same and won't exceed INT_MAX.
 * The number of bricks in each row is in range [1,10,000]. The height of wall is in range [1,10,000]. Total number of
 * bricks of the wall won't exceed 20,000.
 * <p>
 * Related Topics: Hash Table
 */
public class BrickWall {

    /**
     * Hash Table.
     * The # of bricks crossed, B = # of rows, R - # of edges, E.
     * min(B) = R - max(E)
     * Crossing least bricks actually means crossing most edges.
     * Hence we may use a map to record edge index and number of occurrences.
     */
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> edgeToCounts = new HashMap<>();
        int count = 0;
        for (List<Integer> row : wall) {
            for (int i = 0, length = 0; i < row.size() - 1; i++) {
                length += row.get(i); // length is from current edge to leftmost edge.
                edgeToCounts.put(length, edgeToCounts.getOrDefault(length, 0) + 1);
                count = Math.max(count, edgeToCounts.get(length));
            }
        }
        return wall.size() - count;
    }

    /**
     * Same idea. More efficient.
     * Use integer array instead of map.
     * The size of the array can derive from provided note.
     * Then use ternary operator instead of Math.max.
     * And lastly check if count reaches number of rows already.
     * If it does, we can exit early since the least bricks is 0.
     */
    public int leastBricks2(List<List<Integer>> wall) {
        int[] edgeToCounts = new int[Integer.MAX_VALUE / 10000];
        int count = 0;
        for (List<Integer> row : wall) {
            for (int i = 0, length = 0; i < row.size() - 1; i++) {
                length += row.get(i);
                edgeToCounts[length]++;
                count = edgeToCounts[length] > count ? edgeToCounts[length] : count;
                if (count == wall.size()) return 0;
            }
        }
        return wall.size() - count;
    }
}
