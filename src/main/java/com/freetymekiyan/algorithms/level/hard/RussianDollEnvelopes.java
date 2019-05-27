package com.freetymekiyan.algorithms.level.hard;

import java.util.Arrays;

/**
 * 354. Russian Doll Envelopes
 * <p>
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into
 * another if and only if both the width and height of one envelope is greater than the width and height of the other
 * envelope.
 * <p>
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 * <p>
 * Note:
 * Rotation is not allowed.
 * <p>
 * Example:
 * <p>
 * Input: [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 * <p>
 * Companies: Google, Uber
 * <p>
 * Related Topics: Binary Search, Dynamic Programming
 * <p>
 * Similar Questions: (M) Longest Increasing Subsequence
 */
public class RussianDollEnvelopes {

  public int maxEnvelopes(int[][] envelopes) {
    if (envelopes == null || envelopes.length == 0
        || envelopes[0] == null || envelopes[0].length != 2)
      return 0;
    Arrays.sort(envelopes, (arr1, arr2) -> {
      if (arr1[0] == arr2[0])
        return arr2[1] - arr1[1];
      else
        return arr1[0] - arr2[0];
    });
    int dp[] = new int[envelopes.length];
    int len = 0;
    for (int[] envelope : envelopes) {
      int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
      if (index < 0)
        index = -(index + 1);
      dp[index] = envelope[1];
      if (index == len)
        len++;
    }
    return len;
  }
}