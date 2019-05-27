package com.freetymekiyan.algorithms.level.easy;

/**
 * 400. Nth Digit
 * <p>
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 * <p>
 * Note:
 * n is positive and will fit within the range of a 32-bit signed integer (n < 231).
 * <p>
 * Example 1:
 * <p>
 * Input:
 * 3
 * <p>
 * Output:
 * 3
 * Example 2:
 * <p>
 * Input:
 * 11
 * <p>
 * Output:
 * 0
 * <p>
 * Explanation:
 * The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 * <p>
 * Companies: Adobe, Google, Works Application
 * <p>
 * Related Topics: Math
 */
public class NthDigit {

  public int findNthDigit(int n) {
    int len = 1;
    long count = 9;
    int start = 1;

    while (n > len * count) {
      n -= len * count;
      len += 1;
      count *= 10;
      start *= 10;
    }

    start += (n - 1) / len;
    String s = Integer.toString(start);
    return Character.getNumericValue(s.charAt((n - 1) % len));
  }
}