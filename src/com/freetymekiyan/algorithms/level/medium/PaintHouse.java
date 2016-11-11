package com.freetymekiyan.algorithms.level.medium;

/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of
 * painting each house with a certain color is different. You have to paint all the houses such that no two adjacent
 * houses have the same color.
 * <p>
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0]
 * is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so
 * on... Find the minimum cost to paint all houses.
 * <p>
 * Note:
 * All costs are positive integers.
 * <p>
 * Company Tags: LinkedIn
 * Tags: Dynamic Programming
 * Similar Problems: (E) House Robber, (M) House Robber II, (H) Paint House II, (E) Paint Fence
 */
public class PaintHouse {

    /**
     * DP.
     * Recurrence Relation:
     * The color of current house i has 3 possibilities.
     * If it's red, the min cost will be cost of red at i + min(cost of blue at i-1, cost of green at i-1).
     * That is: costs[i][0] + min(costs[i - 1][1], costs[i - 1][2]).
     * The same goes for if it's blue or green.
     * So generate all possibilities.
     * Base case:
     * If costs matrix is empty, return 0.
     */
    public int minCost(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        for (int i = 1; i < n; i++) {
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][1], costs[i - 1][0]);
        }
        return Math.min(Math.min(costs[n - 1][0], costs[n - 1][1]), costs[n - 1][2]);
    }
}
