package com.freetymekiyan.algorithms.level.hard;

/**
 * 402. Trapping Rain Water
 * <p>
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 * <p>
 * https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png
 * <p>
 * For example,
 * Given [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1], return 6.
 * <p>
 * Related Topics: Array, Stack, Two pointers
 * Similar Questions: Container With Most Water (M), Product of Array Except Self (M), Trapping Rain Water II (H), Pour
 * Water (M)
 */
class TrappingRainWater {

  /**
   * Water will need a "container" to be trapped.
   * A "container" will need borders or boundaries.
   * Keep track of the lowest boundary.
   * <p>
   * Calculate the area of:
   * <ol>
   *   <li>total: Most water possible without a bar</li>
   *   <li>blocks: Blocks occupied by a bar</li>
   * </ol>
   * Then return total - blocks to get the trapped water.
   * <p>
   * Use two pointers from the beginning and the end.
   * If min(A[l], A[r]) is larger than current level, more water can be trapped.
   * Update total.
   * Then move lower pointer towards center.
   * And update blocks.
   */
  public int trap(int[] height) {
    if (height == null || height.length == 0) {
      return 0;
    }
    int left = 0;
    int right = height.length - 1;
    int level = 0;
    int total = 0; // Most water possible without any blocks being occupied
    int blocks = 0; // Blocks that are occupied

    while (left <= right) { // Note left <= right to include the last block
      // Update area
      int minBorder = Math.min(height[left], height[right]);
      if (minBorder > level) {
        total += (minBorder - level) * (right - left + 1);
        level = minBorder;
      }
      // Move lower pointer towards the center
      // And update # of blocks occupied
      if (height[left] < height[right]) blocks += height[left++];
      else blocks += height[right--];
    }
    return total - blocks;
  }

  /**
   * Two pointers.
   * <p>
   * Water can be trapped if: The minimum border height is larger than possible water level.
   * Get the current level from the two pointers.
   * Initialize minimum border height as 0. Track it as the two pointers move towards the center.
   * If current level < min border height, water can be trapped. Add trapped water to result.
   * If current level >= min border height, water can't be trapped. Update min border to current level.
   * </p>
   */
  public int trap2(int[] height) {
    final int n = height.length;
    if (n < 3) {
      return 0; // Impossible to form a container
    }

    int left = 0, right = n - 1, minBorder = 0, water = 0;
    while (left < right) {
      final int curLevel = height[left] < height[right] ? height[left++] : height[right--];
      if (curLevel < minBorder) {
        // Add trapped water
        water += minBorder - curLevel;
      } else {
        minBorder = curLevel; // Update highest level
      }
    }

    return water;
  }
}