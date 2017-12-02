package com.freetymekiyan.algorithms.level.medium;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and
 * sell one share of the stock multiple times) with the following restrictions:
 * <p>
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day) Example:
 * <p>
 * prices = [1, 2, 3, 0, 2] maxProfit = 3 transactions = [buy, sell, cooldown, buy, sell]
 * <p>
 * Tags: Dynamic Programming
 * Similar Problems: (M) Best Time to Buy and Sell Stock, (M) Best Time to Buy and Sell Stock II
 */
public class BestTimeToBuySellStockWithCooldown {

    /**
     * https://leetcode.com/discuss/71354/share-my-thinking-process
     * O(n) Time, O(1) Space
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int sell = 0, lastSell = 0, buy = -prices[0], lastBuy;
        for (int price : prices) {
            lastBuy = buy;
            buy = Math.max(lastSell - price, lastBuy);
            lastSell = sell;
            sell = Math.max(lastBuy + price, lastSell);
        }
        return sell;
    }
}
