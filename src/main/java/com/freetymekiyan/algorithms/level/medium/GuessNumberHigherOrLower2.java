package com.freetymekiyan.algorithms.level.medium;

/**
 * We are playing the Guess Game. The game is as follows:
 * <p>
 * I pick a number from 1 to n. You have to guess which number I picked.
 * <p>
 * Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
 * <p>
 * However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the
 * number I picked.
 * <p>
 * Example:
 * <p>
 * n = 10, I pick 8.
 * <p>
 * First round:  You guess 5, I tell you that it's higher. You pay $5.
 * Second round: You guess 7, I tell you that it's higher. You pay $7.
 * Third round:  You guess 9, I tell you that it's lower. You pay $9.
 * <p>
 * Game over. 8 is the number I picked.
 * <p>
 * You end up paying $5 + $7 + $9 = $21.
 * Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
 * <p>
 * Hint:
 * <p>
 * The best strategy to play the game is to minimize the maximum loss you could possibly face. Another strategy is to
 * minimize the expected loss. Here, we are interested in the first scenario.
 * <p>
 * Take a small example (n = 3). What do you end up paying in the worst case?
 * <p>
 * Check out this article if you're still stuck.
 * <p>
 * The purely recursive implementation of minimax would be worthless for even a small n. You MUST use dynamic
 * programming.
 * <p>
 * As a follow-up, how would you modify your code to solve the problem of minimizing the expected loss, instead of the
 * worst-case loss?
 * <p>
 * Tags: Dynamic Programming
 * Similar Problems: (M) Flip Game II, (E) Guess Number Higher or Lower
 */
public class GuessNumberHigherOrLower2 {

    /**
     * DP, top-down.
     * Minimize the maximum of each possible move.
     * The maximum here means the move will always lead to the worst branch.
     * For each move, you can pick randomly from 1 to n, as i.
     * You pay i dollars, and then pick another time from the rest of the two ranges:
     * 1) From 1 to i - 1 2) From i + 1 to end .
     * Recurrence relation:
     * The most money to pay for this move = The money you pay for the current move + The maximum of the most money you
     * pay for left part and right part.
     * The minimum of the maximums is the answer.
     */
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1]; // The amount of money from start to end.
        return helper(dp, 1, n);
    }

    private int helper(int[][] dp, int start, int end) {
        if (start >= end) { // When start == end, the number is found
            return 0;
        }
        if (dp[start][end] != 0) { // Already calculated
            return dp[start][end];
        }
        int res = Integer.MAX_VALUE; // Minimax: minimum of maximums
        for (int i = start; i <= end; i++) {
            int tmp = i + Math.max(helper(dp, start, i - 1), helper(dp, i + 1, end));
            res = Math.min(res, tmp);
        }
        dp[start][end] = res; // Save in matrix
        return res;
    }

    /**
     * DP, bottom-up.
     * Build result of i from 1 to n.
     * For each number, get the global min of j from i - 1 to 1.
     * To get the global min, calculate the local max of k from j + 1 to i.
     * Finally, return dp[1][n] as the result.
     */
    public int getMoneyAmountB(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int j = 2; j <= n; j++) {
            for (int i = j - 1; i > 0; i--) {
                int globalMin = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    int localMax = k + Math.max(dp[i][k - 1], dp[k + 1][j]);
                    globalMin = Math.min(globalMin, localMax);
                }
                dp[i][j] = i + 1 == j ? i : globalMin; // If i + 1 == j means there are only two numbers to choose
            }
        }
        return dp[1][n];
    }

}
