package com.freetymekiyan.algorithms.level.medium;

/**
 * 714. Best Time to Buy and Sell Stock with Transaction Fee
 * <p>
 * Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a
 * non-negative integer fee representing a transaction fee.
 * <p>
 * You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You
 * may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
 * <p>
 * Return the maximum profit you can make.
 * <p>
 * Example 1:
 * Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * Buying at prices[0] = 1
 * Selling at prices[3] = 8
 * Buying at prices[4] = 4
 * Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * Note:
 * <p>
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 * <p>
 * Related Topics: Array, Dynamic Programming, Greedy
 * Similar Questions: (E) Best Time to Buy and Sell Stock II
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {

    /**
     * DP.
     * Can either buy or sell on day i, 2 states.
     * Track the maximum of these 2 states.
     * If buy, the maximum is either do nothing or buy today's stock with cash got from yesterday.
     * buy = max(buy, sell - prices[i])
     * If sell, the maximum is either do nothing or sell the share you hold and pay the fee.
     * sell = max(sell, buy + prices[i] - fee)
     * Note that buy state should be after sell since sell can happen and the maximum is larger.
     */
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        if (fee < 0) {
            throw new IllegalArgumentException("fee should be non-negative.");
        }

        int cash = 0;
        int hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            /*
             * Hold updates after cash since we can sell and buy to achieve higher profit.
             * If buy first then sell on one day, we lose by paying transaction fee.
             */
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash; // Return cash in the end.
    }

    /**
     * DP.
     * More compact version.
     * sell is skipped on day 1 since we don't have any share.
     * hold is initialized as MIN since we have negative values, 0 will be wrong.
     */
    public int maxProfit2(int[] prices, int fee) {
        int sell = 0;
        int hold = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (i > 0) sell = Math.max(prices[i] + hold - fee, sell);
            hold = Math.max(sell - prices[i], hold);
        }
        return sell;
    }
}