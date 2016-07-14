import java.util.*;

/**
 * Say you have an array for which the ith element is the price of a given 
 * stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete at most k 
 * transactions.
 * 
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again).
 * 
 * Tags: DP
 */
class BestTimeStock4 {
    
    public static void main(String[] args) {
        // 2, [3,2,6,5,0,3]
        // 2, [3,3,5,0,0,3,1,4]
        BestTimeStock4 b = new BestTimeStock4();
        int[] A = new int[]{3,3,5,0,0,3,1,4};
        int[] B = new int[]{3,2,6,5,0,3};
        System.out.println(b.maxProfitOpt(2, A));
        System.out.println(b.maxProfit(2, A));
        System.out.println(b.maxProfitOpt(2, B));
        System.out.println(b.maxProfit(2, B));
    }
    
    /**
     * DP, bottom-up, O(kn) Time, O(n) Space
     * If k >= n/2, we can have transactions any time, O(n).
     * dp[k][i+1] represents the max profit of using [0, i] and k transactions
     * It can be dp[k-1][i+1](add 1 more transaction changes nothing)
     * It can be dp[k][i](prices[i] changes nothing)
     * It can be prices[i] + max(dp[k-1][j] - prices[j]), 0 <= j < i
     * means prices[i] will change the max profit, find the biggest from k-1 
     * transacions and add prices[i]
     * dp[k][i+1] = max(dp[k-1][i+1], dp[k][i], prices[i] + max(dp[k-1][j] - 
     * prices[j])), (0 <= j < i)
     */
    public int maxProfitOpt(int k, int[] prices) {
        if (prices == null || prices.length < 2 || k == 0) return 0;
        int n = prices.length;
        int res = 0;
        if (k >= n / 2) { // as many transactions as possible
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) res += prices[i] - prices[i - 1];
            }
            return res;
        }
        int[] cur = new int[n+1];
        for (int i = 1; i <= k; i++) {
            int curMax = Integer.MIN_VALUE;
            for (int j = 0; j < n; j++) {
                int temp = cur[j+1];
                cur[j+1] = Math.max(Math.max(cur[j+1], cur[j]), prices[j] + curMax);
                System.out.print(curMax + "|");
                curMax = Math.max(curMax, temp - prices[j]);
                System.out.print(curMax + "\n");
            }
            System.out.println(Arrays.toString(cur));
        }
        return cur[n];
    }
    
    /**
     * DP, bottom-up, O(kn) Time, O(kn) Space
     */
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2 || k == 0) return 0;
        int n = prices.length;
        int res = 0;
        if (k >= n / 2) {
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) res += prices[i] - prices[i - 1];
            }
            return res;
        }
        int[][] dp = new int[k+1][n+1];
        for (int i = 1; i <= k; i++) {
            int curMax = Integer.MIN_VALUE;
            for (int j = 0; j < n; j++) {
                dp[i][j+1] = Math.max(Math.max(dp[i-1][j+1], dp[i][j]), prices[j] + curMax);
                curMax = Math.max(curMax, dp[i-1][j] - prices[j]);
            }
        }
        return dp[k][n];
    }
}
