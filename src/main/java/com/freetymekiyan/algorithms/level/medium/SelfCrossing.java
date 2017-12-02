package com.freetymekiyan.algorithms.level.medium;

/**
 * You are given an array x of n positive numbers. You start at point (0,0) and moves x[0] metres to the north, then
 * x[1] metres to the west, x[2] metres to the south, x[3] metres to the east and so on. In other words, after each
 * move your direction changes counter-clockwise.
 * <p>
 * Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or not.
 * <p>
 * Example 1:
 * Given x = [2, 1, 1, 2],
 * ┌───┐
 * │   │
 * └───┼──>
 * │
 * <p>
 * Return true (self crossing)
 * <p>
 * Example 2:
 * Given x = [1, 2, 3, 4],
 * ┌──────┐
 * │      │
 * │
 * │
 * └────────────>
 * <p>
 * Return false (not self crossing)
 * <p>
 * Example 3:
 * Given x = [1, 1, 1, 1],
 * ┌───┐
 * │   │
 * └───┼>
 * <p>
 * Return true (self crossing)
 * <p>
 * Tags: Math
 */
public class SelfCrossing {
    public boolean isSelfCrossing(int[] x) {
        for (int i = 3; i < x.length; i++) {
            if (x[i] >= x[i - 2] && x[i - 1] <= x[i - 3]) {
                // checks if current line crosses the line 3 steps ahead of it
                return true;
            }
            if (i >= 4) {
                // checks if current line crosses the line 4 steps ahead of it
                if (x[i - 1] == x[i - 3] && x[i] + x[i - 4] >= x[i - 2]) {
                    return true;
                }
            }
            if (i >= 5) {
                // checks if current line crosses the line 5 steps ahead of it
                if (x[i - 2] - x[i - 4] >= 0 && x[i] >= x[i - 2] - x[i - 4] && x[i - 1] >= x[i - 3] - x[i - 5]
                        && x[i - 1] <= x[i - 3]) {
                    return true;
                }
            }
        }
        return false;
    }
}
