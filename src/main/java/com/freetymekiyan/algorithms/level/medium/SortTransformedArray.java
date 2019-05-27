package com.freetymekiyan.algorithms.level.medium;

/**
 * 360. Sort Transformed Array
 * <p>
 * Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the form f(x) =
 * ax2 + bx + c to each element x in the array.
 * <p>
 * The returned array must be in sorted order.
 * <p>
 * Expected time complexity: O(n)
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
 * Output: [3,9,15,33]
 * Example 2:
 * <p>
 * Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
 * Output: [-23,-5,1,7]
 * <p>
 * Companies: Google, Facebook
 * <p>
 * Related Topics: Math, Two Pointers
 * <p>
 * Similar Questions: (E) Squares of a Sorted Array
 */
public class SortTransformedArray {

  public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
    int n = nums.length;
    int[] sorted = new int[n];
    int i = 0, j = n - 1;
    int index = a >= 0 ? n - 1 : 0;
    while (i <= j) {
      if (a >= 0) {
        sorted[index--] = quad(nums[i], a, b, c) >= quad(nums[j], a, b, c) ? quad(nums[i++], a, b, c) : quad(nums[j--], a, b, c);
      } else {
        sorted[index++] = quad(nums[i], a, b, c) >= quad(nums[j], a, b, c) ? quad(nums[j--], a, b, c) : quad(nums[i++], a, b, c);
      }
    }
    return sorted;
  }

  private int quad(int x, int a, int b, int c) {
    return a * x * x + b * x + c;
  }
}