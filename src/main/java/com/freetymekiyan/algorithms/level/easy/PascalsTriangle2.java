package com.freetymekiyan.algorithms.level.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 119. Pascal's Triangle II
 * <p>
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 * <p>
 * Note that the row index starts from 0.
 * <p>
 * https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif
 * <p>
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 * <p>
 * Example:
 * <p>
 * Input: 3
 * Output: [1,3,3,1]
 * Follow up:
 * <p>
 * Could you optimize your algorithm to use only O(k) extra space?
 * <p>
 * Related Topics: Array
 * <p>
 * Similar Questions: Pascal's Triangle (E)
 */
public class PascalsTriangle2 {

  public List<Integer> getRow(int rowIndex) {
    List<Integer> row = new ArrayList<>(rowIndex + 1);
    row.add(1);
    for (int i = 1; i <= rowIndex; i++) {
      for (int j = i - 1; j >= 1; j--) { // backwards
        row.set(j, row.get(j - 1) + row.get(j));
      }
      row.add(1); // add 1 at the end
    }
    return row;
  }
}