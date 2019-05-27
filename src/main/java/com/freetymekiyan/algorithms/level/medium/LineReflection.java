package com.freetymekiyan.algorithms.level.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 356. Line Reflection
 * <p>
 * Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,1],[-1,1]]
 * Output: true
 * Example 2:
 * <p>
 * Input: [[1,1],[-1,-1]]
 * Output: false
 * Follow up:
 * Could you do better than O(n^2) ?
 * <p>
 * Companies: Microsoft, Apple, Google
 * <p>
 * Related Topics: Hash Table, Math
 * <p>
 * Similar Questions: (H) Max Points on a Line, (E) Number of Boomerangs
 */
public class LineReflection {

  public boolean isReflected(int[][] points) {
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    Set<String> set = new HashSet<>();
    for (int[] p : points) {
      max = Math.max(max, p[0]);
      min = Math.min(min, p[0]);
      String str = p[0] + "a" + p[1];
      set.add(str);
    }
    int sum = max + min;
    for (int[] p : points) {
      //int[] arr = {sum-p[0],p[1]};
      String str = (sum - p[0]) + "a" + p[1];
      if (!set.contains(str))
        return false;

    }
    return true;
  }
}