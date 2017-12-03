package com.freetymekiyan.algorithms.level.easy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
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
 * Company Tags: Amazon, Microsoft, Bloomberg, Uber, Facebook
 * Tags: Array, Dynamic Programming
 * Similar Problems: (M) Maximum Subarray, (M) Best Time to Buy and Sell Stock II, (H) Best Time to Buy and Sell Stock
 * III, (H) Best Time to Buy and Sell Stock IV, (M) Best Time to Buy and Sell Stock with Cooldown
 */
public class BestTimeToBuyAndSellStock {

    private BestTimeToBuyAndSellStock b;

    /**
     * DP. Bottom-up. Optimized. O(n) Time, O(1) Space.
     * Maximum profit is the maximum price - minimum price.
     * So loop from second day to last day, track min price and max profit.
     * Suppose max profit of ith day is P[i].
     * If price[i] > price[i-1], then P[i] may be larger than P[i-1]. Update it.
     * If price[i] <= price[i-1], then P[i] cannot be larger. But there may be a new minimum price.
     * Update min price that can be used for later iterations.
     * Base case:
     * Max profit is 0. Min price is the first day's price.
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) { // Need at least 2 days to make profit.
            return 0;
        }
        int max = 0; // Max profit.
        int min = prices[0]; // Min price of previous days.
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) { // Maximum price may update.
                max = Math.max(max, prices[i] - min); // Update max profit.
            } else {
                min = Math.min(min, prices[i]); // Update min.
            }
        }
        return max;
    }

    /**
     * DP. Bottom-up. O(n) Time. O(n) Space.
     * Keep track of minimum price of the stock.
     * Keep track of the maximum profit before today.
     * Get the maximum profit of today, and compare with previous maximum.
     * Update maximum if today's profit is larger.
     * Stop until the array is fully traversed.
     * O(n) Time, O(n) Space.
     */
    public int maxProfitB(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0; // need at least 2 days
        }
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

    @Before
    public void setUp() {
        b = new BestTimeToBuyAndSellStock();
    }

    @Test
    public void testExamples() {
        int[] prices = {1, 4, 2};
        Assert.assertEquals(3, b.maxProfit(prices));
        Assert.assertEquals(3, b.maxProfitB(prices));
    }

    @After
    public void tearDown() {
        b = null;
    }
}
