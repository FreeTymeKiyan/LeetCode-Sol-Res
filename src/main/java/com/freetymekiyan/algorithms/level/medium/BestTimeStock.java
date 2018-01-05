package com.freetymekiyan.algorithms.level.medium;

/**
 * 121. Best Time to Buy and Sell Stock
 * <p>
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design
 * an algorithm to find the maximum profit.
 * <p>
 * Example 1:
 * Input: [7, 1, 5, 3, 6, 4]
 * Output: 5
 * <p>
 * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 * Example 2:
 * Input: [7, 6, 4, 3, 1]
 * Output: 0
 * <p>
 * In this case, no transaction is done, i.e. max profit = 0.
 * <p>
 * Related Topics: Array, Dynamic Programming
 * Similar Questions: (E) Maximum Subarray, (E) Best Time to Buy and Sell Stock II, (H) Best Time to Buy and Sell Stock
 * III, (H) Best Time to Buy and Sell Stock IV, (M) Best Time to Buy and Sell Stock with Cooldown
 */
class BestTimeStock {

    /**
     * DP. Bottom-up. Space optimized.
     * O(n) Time, O(1) Space.
     * Recurrence Relation:
     * max profit of ith day = a[n] - min > max(i-1) ? a[n] - min : max(i-1).
     * To calculate max profit, we must track the minimum value of the array as well.
     * So maintain min and maxProfit.
     * maxProfit is initialized as 0, which means no profit.
     * min is initialized as first days price, since it cannot be lower.
     * If next price is bigger, it's only possible to update the profit.
     * If next price is smaller or equal, it's only possible to update min.
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0; // need at least 2
        int max = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) max = Math.max(max, prices[i] - min);
            else if (prices[i] < prices[i - 1]) min = Math.min(min, prices[i]);
        }
        return max;
    }

    /**
     * DP. Bottom-up.
     * O(n) Time, O(n) Space.
     * Store max profit of each day using an integer array.
     * Profit of next day can only be same as last day or update because of higher price today.
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) return 0; // need at least 2 days
        int min = prices[0];
        int max = 0;
        int len = prices.length;
        int[] history = new int[len];
        for (int i = 0; i < len - 1; i++) {
            min = min < prices[i] ? min : prices[i];
            if (i > 0) { // skip first day
                history[i] = Math.max(history[i - 1], prices[i] - min);
                max = history[i] > max ? history[i] : max;
            }
        }
        return max;
    }
}