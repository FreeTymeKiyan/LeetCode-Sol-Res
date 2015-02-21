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
        BestTimeStock3 b = new BestTimeStock3();
        int[] prices = { 6, 1, 3, 2, 4, 7, 6, 10, 15 };
        System.out.println(b.maxProfit(prices));
    }
    
    /**
     * Goes forward to build single transaction max profit
     * Then goes backward to build max since day i profit
     * Find the max of the sum of these two
     */
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        if (prices == null || prices.length < 2) return maxProfit;
        int len = prices.length;
        int[] maxBy = new int[len];
        int[] maxSince = new int[len];
        int valley = prices[0];
        int peak = prices[len - 1];
        
        for (int i = 1; i < len; i++) {
            valley = Math.min(valley, prices[i]);
            maxBy[i] = Math.max(maxBy[i - 1], prices[i] - valley);
        }
        /*update maxProfit while build maxSince*/
        for (int i = len - 2; i >= 0; i--) {
            peak = Math.max(peak, prices[i]);
            maxSince[i] = Math.max(maxSince[i + 1], peak - prices[i]);
            maxProfit = Math.max(maxProfit, maxBy[i] + maxSince[i]); // find i such that maxBy[i]+maxSince[i+1] is the max two-transaction profit, no overlap
        }
        return maxProfit;
    }
}
