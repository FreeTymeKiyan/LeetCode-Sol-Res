package com.freetymekiyan.algorithms.level.medium;

/**
 * 397. Integer Replacement
 * <p>
 * Given a positive integer n and you can do operations as follow:
 * <p>
 * If n is even, replace n with n/2.
 * If n is odd, you can replace n with either n + 1 or n - 1.
 * What is the minimum number of replacements needed for n to become 1?
 * <p>
 * Example 1:
 * <p>
 * Input:
 * 8
 * <p>
 * Output:
 * 3
 * <p>
 * Explanation:
 * 8 -> 4 -> 2 -> 1
 * Example 2:
 * <p>
 * Input:
 * 7
 * <p>
 * Output:
 * 4
 * <p>
 * Explanation:
 * 7 -> 8 -> 4 -> 2 -> 1
 * or
 * 7 -> 6 -> 3 -> 2 -> 1
 * <p>
 * Companies: Microsoft, Amazon, Google, Baidu
 * <p>
 * Related Topics: Math, Bit Manipulation
 */
public class IntegerReplacement {

  public int integerReplacement(int n) {
    int c = 0;
    while (n != 1) {
      if ((n & 1) == 0) {
        n >>>= 1;
      } else if (n == 3 || ((n >>> 1) & 1) == 0) {
        --n;
      } else {
        ++n;
      }
      ++c;
    }
    return c;
  }

  public int integerReplacement2(int n) {
    if (n == Integer.MAX_VALUE) return 32; //n = 2^31-1;
    int count = 0;
    while (n > 1) {
      if (n % 2 == 0) n /= 2;
      else {
        if ((n + 1) % 4 == 0 && (n - 1 != 2)) n++;
        else n--;
      }
      count++;
    }
    return count;
  }
}