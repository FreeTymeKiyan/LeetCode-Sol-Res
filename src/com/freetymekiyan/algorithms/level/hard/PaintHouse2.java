package com.freetymekiyan.algorithms.level.hard;

/**
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with
 * a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 * <p>
 * The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0]
 * is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on...
 * <p>
 * Find the minimum cost to paint all houses.
 * <p>
 * Note:
 * All costs are positive integers.
 * <p>
 * Follow up:
 * Could you solve it in O(nk) runtime?
 * <p>
 * Company Tags: Facebook
 * Tags: Dynamic Programming
 * Similar Problems: (M) Product of Array Except Self, (H) Sliding Window Maximum, (M) Paint House, (E) Paint Fence
 */
public class PaintHouse2 {

    /**
     * DP.
     * Recurrence relation:
     * min(n) = min(n-1) + min(cost[n-1][i], 0 <= i < k, i != j, where j is cost[n-2][j])
     * OR min(n) = secMin(n-1) + min(cost[n-1][i], 0 <= i < k, i == j, where j is cost[n-2][j])
     * Safely use previous minimum if colors are different.
     * If colors are the same, use second minimum of previous result.
     * So we need to keep track of minimum, second minimum and the index of last minimum.
     * <p>
     * Special case:
     * When k=1, there is only 1 color. If there is more than 1 house, invalid.
     * Loop through houses from 0 to n-1.
     * For each house, try to find minimum, second minimum, minimum index by looping through all colors.
     * For each color, get the minimum candidate value by add the cost with previous min or second min.
     * If minimum index is not set yet, update current minimum and current index.
     * If value < current min, update second min first, then update current min and index.
     * If value < current second min, update second min.
     * After min of this house is found, remember the current min, current second min and index for the next house.
     */
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int n = costs.length;
        int k = costs[0].length;
        if (k == 1) {
            return n == 1 ? costs[0][0] : -1;
        }
        int prevMin = 0;
        int prevSecMin = 0;
        int prevIndex = -1;
        for (int i = 0; i < n; i++) {
            int curMin = Integer.MAX_VALUE;
            int curSecMin = Integer.MAX_VALUE;
            int curIndex = -1;
            for (int j = 0; j < k; j++) {
                int val = costs[i][j] + (j == prevIndex ? prevSecMin : prevMin);
                if (curIndex == -1) { // Not initialized yet
                    curMin = val;
                    curIndex = j;
                } else if (val < curMin) {
                    curSecMin = curMin;
                    curMin = val;
                    curIndex = j;
                } else if (val < curSecMin) {
                    curSecMin = val;
                }
            }
            prevMin = curMin;
            prevIndex = curIndex;
            prevSecMin = curSecMin;
        }
        return prevMin;
    }

}
