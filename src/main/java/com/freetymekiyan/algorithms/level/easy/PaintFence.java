package com.freetymekiyan.algorithms.level.easy;

/**
 * 276. Paint Fence
 * <p>
 * There is a fence with n posts, each post can be painted with one of the k colors.
 * <p>
 * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 * <p>
 * Return the total number of ways you can paint the fence.
 * <p>
 * Note:
 * n and k are non-negative integers.
 * <p>
 * Example:
 * <p>
 * Input: n = 3, k = 2
 * Output: 6
 * Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:
 * <p>
 * |             post1  post2  post3
 * |  -----      -----  -----  -----
 * |    1         c1     c1     c2
 * |    2         c1     c2     c1
 * |    3         c1     c2     c2
 * |    4         c2     c1     c1
 * |    5         c2     c1     c2
 * |    6         c2     c2     c1
 * <p>
 * Companies: Google, Yahoo
 * <p>
 * Related Topics: Dynamic Programming
 * <p>
 * Similar Questions: (E) House Robber, (M) House Robber II, (E) Paint House, (H) Paint House II
 */
public class PaintFence {

  public int numWays(int n, int k) {
    if (n == 0) return 0;
    else if (n == 1) return k;
    int diffColorCounts = k * (k - 1);
    int sameColorCounts = k;
    for (int i = 2; i < n; i++) {
      int temp = diffColorCounts;
      diffColorCounts = (diffColorCounts + sameColorCounts) * (k - 1);
      sameColorCounts = temp;
    }
    return diffColorCounts + sameColorCounts;
  }
}