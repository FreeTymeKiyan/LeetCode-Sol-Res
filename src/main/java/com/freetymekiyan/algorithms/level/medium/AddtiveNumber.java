package com.freetymekiyan.algorithms.level.medium;


import java.math.BigInteger;

/**
 * 306. Additive Number
 * <p>
 * Additive number is a string whose digits can form additive sequence.
 * <p>
 * A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent
 * number in the sequence must be the sum of the preceding two.
 * <p>
 * Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
 * <p>
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 * <p>
 * Example 1:
 * <p>
 * Input: "112358"
 * Output: true
 * Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * Example 2:
 * <p>
 * Input: "199100199"
 * Output: true
 * Explanation: The additive sequence is: 1, 99, 100, 199.
 * 1 + 99 = 100, 99 + 100 = 199
 * Follow up:
 * How would you handle overflow for very large input integers?
 * <p>
 * Companies: Epic Systems
 * <p>
 * Related Topics: Backtracking
 * <p>
 * Similar Questions: (M) Split Array into Fibonacci Sequence
 */
public class AddtiveNumber {

  public boolean isAdditiveNumber(String num) {
    int n = num.length();
    for (int i = 1; i <= n / 2; i++) {
      if (num.charAt(0) == '0' && i > 1) {
        return false;
      }
      BigInteger num1 = new BigInteger(num.substring(0, i));
      for (int j = 1; Math.max(i, j) <= n - i - j; j++) {
        if (num.charAt(i) == '0' && j > 1) {
          break;
        }
        BigInteger num2 = new BigInteger(num.substring(i, i + j));
        if (isAdditiveNumber(num1, num2, i + j, num)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean isAdditiveNumber(BigInteger num1, BigInteger num2, int start, String num) {
    if (start == num.length()) {
      return true;
    }
    num2 = num2.add(num1);
    num1 = num2.subtract(num1);
    String sum = num2.toString();
    return num.startsWith(sum, start) && isAdditiveNumber(num1, num2, start + sum.length(), num);
  }
}