/**
 * Say you have an array for which the ith element is the price of a given
 * stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete at most 
 * <strong>two</strong> transactions.
 * 
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again).
 * 
 * Tags: Array, DP
 */
class BestTimeStock3 {
    public static void main(String[] args) {
        int[] prices = { 6, 1, 3, 2, 4, 7, 6, 10, 15 };
        System.out.println(maxProfit(prices));
    }
    
    /**
     * Goes forward to build single transaction max profit
     * Then goes backward to build max since day i profit
     * Find the max of the sum of these two
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int len = prices.length;
        int[] historyProf = new int[len];
        int[] futureProf = new int[len];
        int maxProfit = 0;
        int valley = prices[0];
        int peak = prices[len - 1];
        
        for (int i = 1; i < len; i++) {
            valley = Math.min(valley, prices[i]);
            historyProf[i] = Math.max(historyProf[i - 1], prices[i] - valley);
        }
        /*update maxProfit while build futureProf*/
        for (int i = len - 2; i >= 0; i--) {
            peak = Math.max(peak, prices[i]);
            futureProf[i] = Math.max(futureProf[i + 1], peak - prices[i]);
            maxProfit = Math.max(maxProfit, historyProf[i] + futureProf[i]); // find i such that maxBy[i]+maxSince[i+1] is the max two-transaction profit, no overlap
        }
        return maxProfit;
    }
}
