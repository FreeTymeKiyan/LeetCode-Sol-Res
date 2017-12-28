package com.freetymekiyan.algorithms.level.hard;

/**
 * 265. Paint House 2
 * <p>
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
     * OR
     * min(n) = secMin(n-1) + min(cost[n-1][i], 0 <= i < k, i == j, where j is cost[n-2][j])
     * Safely use previous minimum if the colors are different.
     * If colors are the same, use second minimum of previous result.
     * So track minimum, second minimum and the index of last minimum.
     * <p>
     * Corner case:
     * When k=1, there is only 1 color. If there is more than 1 house, invalid.
     * <p>
     * Implementation:
     * Check the costs matrix. If it's null or empty, return 0.
     * Get n and k.
     * If k is 1, n can only be 1, otherwise cannot paint. So return n == 1 ? cost[0][0] : -1.
     * Initialize 3 integers min, second min and previous picked color's index.
     * For each house from 0 to n-1:
     * | Find the current minimum, second minimum, and color.
     * | For each of the k colors from 0 to k-1:
     * |   The cost value is costs[i][j] + (j == prevColor ? secMin : min);
     * |   If current color is not picked yet:
     * |      Store value in current min. Update the color index.
     * |   Else if value < current min:
     * |      Update second min first. Then current min. Then the color.
     * |   Else if value < current second min:
     * |      Update second min.
     * |   Store the minimum, second minimum, and color.
     * Return the minimum.
     */
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int n = costs.length;
        int k = costs[0].length;
        if (k == 1) { // Only one color available.
            return n == 1 ? costs[0][0] : -1;
        }
        int min = 0;
        int secMin = 0;
        int prevColor = -1;
        for (int i = 0; i < n; i++) {
            int curMin = Integer.MAX_VALUE;
            int curSecMin = Integer.MAX_VALUE;
            int curColor = -1;
            for (int j = 0; j < k; j++) {
                int val = costs[i][j] + (j == prevColor ? secMin : min); // Note the parentheses.
                if (curColor == -1) { // Not initialized yet
                    curMin = val;
                    curColor = j;
                } else if (val < curMin) {
                    curSecMin = curMin;
                    curMin = val;
                    curColor = j;
                } else if (val < curSecMin) {
                    curSecMin = val;
                }
            }
            min = curMin;
            prevColor = curColor;
            secMin = curSecMin;
        }
        return min;
    }

}