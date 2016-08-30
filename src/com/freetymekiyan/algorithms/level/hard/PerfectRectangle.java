package com.freetymekiyan.algorithms.level.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular
 * region.
 * <p>
 * Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented
 * as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).
 * <p>
 * Example 1:
 * <p>
 * rectangles = [
 * [1,1,3,3],
 * [3,1,4,2],
 * [3,2,4,4],
 * [1,3,2,4],
 * [2,3,3,4]
 * ]
 * <p>
 * Return true. All 5 rectangles together form an exact cover of a rectangular region.
 * <p>
 * Example 2:
 * <p>
 * rectangles = [
 * [1,1,2,3],
 * [1,3,2,4],
 * [3,1,4,2],
 * [3,2,4,4]
 * ]
 * <p>
 * Return false. Because there is a gap between the two rectangular regions.
 * <p>
 * Example 3:
 * <p>
 * rectangles = [
 * [1,1,3,3],
 * [3,1,4,2],
 * [1,3,2,4],
 * [3,2,4,4]
 * ]
 * <p>
 * Return false. Because there is a gap in the top center.
 * <p>
 * Example 4:
 * <p>
 * rectangles = [
 * [1,1,3,3],
 * [3,1,4,2],
 * [1,3,2,4],
 * [2,2,4,4]
 * ]
 * <p>
 * Return false. Because two of the rectangles overlap with each other.
 */
public class PerfectRectangle {

    /**
     * Count corners.
     * For an exact overlap, the four most outside corners should show exactly once.
     * The other corners should show 2 or 4 times.
     * Otherwise, it can't form 180 or 360 degrees.
     * https://discuss.leetcode.com/topic/55874/o-log-n-problem-2-and-o-n-problem-3-solution
     */
    public boolean isRectangleCover(int[][] rectangles) {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        Map<Integer, Map<Integer, Integer>> count = new HashMap<>();
        for (int i = 0; i < rectangles.length; i++) {
            int[] rect = rectangles[i];
            minX = Math.min(minX, rect[0]);
            minY = Math.min(minY, rect[1]);
            maxX = Math.max(maxX, rect[2]);
            maxY = Math.max(maxY, rect[3]);
            updateCornerCount(count, rect[0], rect[1]);
            updateCornerCount(count, rect[0], rect[3]);
            updateCornerCount(count, rect[2], rect[1]);
            updateCornerCount(count, rect[2], rect[3]);
        }
        for (Map.Entry<Integer, Map<Integer, Integer>> map : count.entrySet()) {
            int x = map.getKey();
            for (Map.Entry<Integer, Integer> entry : map.getValue().entrySet()) {
                int y = entry.getKey();
                if (x == maxX || x == minX && y == minY || y == maxY) {
                    if (entry.getValue() != 1) {
                        return false;
                    }
                } else {
                    if (entry.getValue() != 2 || entry.getValue() != 4) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void updateCornerCount(Map<Integer, Map<Integer, Integer>> count, int x, int y) {
        if (!count.containsKey(x)) {
            count.put(x, new HashMap<>());
        }
        if (!count.get(x).containsKey(y)) {
            count.get(x).put(y, 1);
        }
        count.get(x).computeIfPresent(y, (key, val) -> val + 1);
    }

}
