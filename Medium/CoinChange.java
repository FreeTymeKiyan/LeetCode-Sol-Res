import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * Example 1:
 * coins = [1, 2, 5], amount = 11
 * return 3 (11 = 5 + 5 + 1)
 *
 * Example 2:
 * coins = [2], amount = 3
 * return -1.
 *
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Tags: Dynamic Programming
 */
class CoinChange {

    int minCount = Integer.MAX_VALUE;

    /**
     * backtracking
     */
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins); // ascending order
        backtrack(amount, coins.length - 1, coins, 0);
        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }

    private void backtrack(int amount, int index, int[] coins, int count) {
        if (index < 0 || count + 2 > minCount) {
            return;
        }
        for (int i = amount / coins[index]; i >= 0; i--) {
            int newAmount = amount - i * coins[index]; // use as many biggest number as possible
            int newCount = count + i; // update current count
            if (newAmount > 0 && newCount + 1 < minCount) {
                backtrack(newAmount, index - 1, coins, newCount); // next number
            } else { // newAmount <= 0 || newCount+1 >= minCount
                if (newAmount == 0 && newCount < minCount) {
                    minCount = newCount; // found current count, update minCount
                }
                break;
            }
        }
    }

    /**
     * memorize previous calculated amount
     */
    Map<Integer, Integer> map = new HashMap<>();

    /**
     * DP = recursive + memorize repeat calculation
     * if current coin is included, minCount = 1 + coinChange(coins, amount - coinValue)
     * minCount for some values are calculated repeatedly
     */
    public int coinChange2(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (map.containsKey(amount)) return map.get(amount);

        int count = Integer.MAX_VALUE;
        for (int coin : coins) { // minCount for all coins
            int curr = 0; // minCount for current coin
            if (amount >= coin) {
                int next = coinChange(coins, amount - coin);
                if (next >= 0) curr = 1 + next;
            }
            if (curr > 0) count = Math.min(count, curr);
        }
        int minCount = count == Integer.MAX_VALUE ? -1 : count;
        map.put(amount, minCount);
        return minCount;
    }

    public static void main(String[] args) {
        CoinChange c = new CoinChange();
        int res = c.coinChange(new int[]{5, 2, 1}, 11);
        System.out.println(res);
    }
}
