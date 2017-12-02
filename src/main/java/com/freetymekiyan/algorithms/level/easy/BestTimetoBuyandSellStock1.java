package com.freetymekiyan.algorithms.level.easy;

/**
 * Best Time to Buy and Sell Stock
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design
 * an algorithm to find the maximum profit.
 * Tags:  Array Dynamic Programming
 * Similar Problems:  (M) Maximum Subarray (M) Best Time to Buy and Sell Stock II (H) Best Time to Buy and Sell Stock
 * III (H) Best Time to Buy and Sell Stock IV (M) Best Time to Buy and Sell Stock with Cooldown
 * O(n) time, O(1) space
 *
 * @author chenshuna
 */

class BestTimetoBuyandSellStock_shuna {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int max = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            max = Math.max(max, prices[i] - minPrice);
        }
        return max;
    }

    public static void main(String arg[]) {
        int[] prices = {2, 5, 8, 9, 1, 6};
        System.out.print(maxProfit(prices));
    }
}