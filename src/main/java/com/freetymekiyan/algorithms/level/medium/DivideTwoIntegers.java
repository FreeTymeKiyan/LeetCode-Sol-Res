package com.freetymekiyan.algorithms.level.medium;

/**
 * 29. Divide Two Integers
 * <p>
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod
 * operator.
 * <p>
 * Return the quotient after dividing dividend by divisor.
 * <p>
 * The integer division should truncate toward zero.
 * <p>
 * Example 1:
 * <p>
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Example 2:
 * <p>
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Note:
 * <p>
 * Both dividend and divisor will be 32-bit signed integers.
 * The divisor will never be 0.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range:
 * [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result
 * overflows.
 * <p>
 * Related Topics: Math, Binary Search
 */
public class DivideTwoIntegers {

  public int divide(int dividend, int divisor) {
    if (divisor == 0) { // Return infinity when divisor is 0
      return Integer.MAX_VALUE;
    }
    if (dividend == 0) { // Return 0 when dividend is 0
      return 0;
    }
    if (divisor == 1) { // Return dividend directly when divisor is 1
      return dividend;
    }
    if (divisor == -1) { // Handle integer range if divisor is -1
      return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : -dividend; // Out of range
    }
    // Get the sign and do bit division on abstract values
    final boolean isNegative = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
    long longDividend = Math.abs((long) dividend);
    final long longDivisor = Math.abs((long) divisor);
    int res = 0;
    for (int bit = Integer.SIZE - 1; bit >= 0 && longDividend >= longDivisor; bit--) {
      if (longDividend >= (longDivisor << bit)) {
        res |= (1 << bit);
        longDividend -= (longDivisor << bit);
      }
    }
    return isNegative ? -res : res;
  }
}