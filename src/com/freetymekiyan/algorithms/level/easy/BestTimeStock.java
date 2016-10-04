package com.freetymekiyan.algorithms.level.easy;

/**
 * Say you have an array for which the ith element is the price of a given
 * stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction (ie, buy one
 * and sell one share of the stock), design an algorithm to find the maximum
 * profit.
 * <p>
 * Tags: Array, DP
 */
class BestTimeStock {

    public static void main(String[] args) {
        int[] prices = {1, 4, 2};
        System.out.println(maxProfit(prices));
    }

    /**
     * Optimized bottom-up approach
     * O(n) Time, O(1) Space
     * Just record yesterday's profit
     * Update min, max and profit
     * If next price is bigger, it's only possible to update the profit
     * If next price is smaller or equal, it's only possible to update min
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0; // need at least 2
        }
        int max = 0;
        int min = prices[0]; // track the minimum of profit array before cur ele
        for (int i = 1; i < prices.length; i++) { // note that i starts from 1
            min = Math.min(min, prices[i]); // update min
            if (prices[i] > prices[i - 1]) {
                max = Math.max(max, prices[i] - min);
            }
        }
        return max;
    }

    /**
     * DP, bottom-up.
     * Keep track of minimum price of the stock.
     * Keep track of the maximum profit before today.
     * Get the maximum profit of today, and compare with previous maximum.
     * Update maximum if today's profit is larger.
     * Stop until the array is fully traversed.
     * O(n) Time, O(n) Space.
     */
    public static int maxProfitB(int[] prices) {
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
}
