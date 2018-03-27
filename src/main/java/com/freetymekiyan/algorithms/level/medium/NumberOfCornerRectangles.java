package com.freetymekiyan.algorithms.level.medium;

import java.util.*;

/**
 * 750. Number Of Corner Rectangles
 * <p>
 * Given a grid where each entry is only 0 or 1, find the number of corner rectangles.
 * <p>
 * A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle. Note that only the corners need
 * to have the value 1. Also, all four 1s used must be distinct.
 * <p>
 * Example 1:
 * <p>
 * Input: grid =
 * [[1, 0, 0, 1, 0],
 * [0, 0, 1, 0, 1],
 * [0, 0, 0, 1, 0],
 * [1, 0, 1, 0, 1]]
 * Output: 1
 * Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].
 * <p>
 * Example 2:
 * <p>
 * Input: grid =
 * [[1, 1, 1],
 * [1, 1, 1],
 * [1, 1, 1]]
 * Output: 9
 * Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.
 * <p>
 * Example 3:
 * <p>
 * Input: grid =
 * [[1, 1, 1, 1]]
 * Output: 0
 * Explanation: Rectangles must have four distinct corners.
 * <p>
 * Note:
 * <p>
 * The number of rows and columns of grid will each be in the range [1, 200].
 * Each grid[i][j] will be either 0 or 1.
 * The number of 1s in the grid will be at most 6000.
 * <p>
 * Companies: Facebook
 * Related Topics: Dynamic Programming
 */
public class NumberOfCornerRectangles {

    /**
     * Hash table.
     * A pair of 1's at current row can form a rectangle with another pair of ones at previous row.
     * Save the number of pairs already found, n.
     * Current pair can form n more rectangles with them.
     */
    public int countCornerRectangles(int[][] grid) {
        Map<String, Integer> pairs = new HashMap<>(); // Coordinates combined as key. Number of pairs as value.
        int count = 0;
        for (int[] row : grid) {
            for (int c1 = 0; c1 < row.length; c1++) {
                if (row[c1] != 1) continue;
                for (int c2 = c1 + 1; c2 < row.length; c2++) {
                    if (row[c2] != 1) continue;
                    // Find an 1-1 pair in this row.
                    String key = c1 + "|" + c2;
                    int c = pairs.getOrDefault(key, 0);
                    count += c;
                    pairs.put(key, c + 1);
                }
            }
        }
        return count;
    }

    /**
     * Save the iteration of pairs.
     */
    public int countCornerRectangles2(int[][] grid) {
        List<List<Integer>> rows = new ArrayList<>();
        int N = 0;
        for (int r = 0; r < grid.length; r++) {
            rows.add(new ArrayList<>());
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == 1) {
                    rows.get(r).add(c);
                    N++;
                }
            }
        }

        int sqrtN = (int) Math.sqrt(N);
        int count = 0;
        Map<Integer, Integer> pairs = new HashMap<>();

        for (int r = 0; r < grid.length; r++) {
            if (rows.get(r).size() >= sqrtN) { // Have more than sqrt(N) ones
                Set<Integer> set = new HashSet<>(rows.get(r));
                for (int r2 = 0; r2 < grid.length; r2++) {
                    if (r2 <= r && rows.get(r2).size() >= sqrtN) {
                        continue;
                    }
                    int found = 0;
                    for (int c2 : rows.get(r2)) {
                        if (set.contains(c2)) {
                            found++;
                        }
                    }
                    count += found * (found - 1) / 2;
                }
            } else {
                for (int i1 = 0; i1 < rows.get(r).size(); i1++) {
                    int c1 = rows.get(r).get(i1);
                    for (int i2 = i1 + 1; i2 < rows.get(r).size(); i2++) {
                        int c2 = rows.get(r).get(i2);
                        int ct = pairs.getOrDefault(c1 * 200 + c2, 0);
                        count += ct;
                        pairs.put(200 * c1 + c2, ct + 1);
                    }
                }
            }
        }
        return count;
    }
}