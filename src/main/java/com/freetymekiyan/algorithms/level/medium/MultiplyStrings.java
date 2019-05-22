package com.freetymekiyan.algorithms.level.medium;

/**
 * 43. Multiply Strings
 * <p>
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 * <p>
 * Note:
 * The numbers can be arbitrarily large and are non-negative.
 * Converting the input string to integer is NOT allowed.
 * You should NOT use internal library such as BigInteger.
 * <p>
 * Company Tags: Facebook, Twitter
 * Tags: Math, String
 * Similar Problems: (M) Add Two Numbers, (E) Plus One, (E) Add Binary, (E) Add Strings
 */
public class MultiplyStrings {

  /**
   * Math. String.
   * How to do multiplication?
   * Start from right to left, multiply each pair of digits, and add them together.
   * Result num1[i] * num2[j] will be placed at i + j and i + j + 1.
   * Mimic this process.
   * Special cases:
   * 1) If one of the strings is null, return empty.
   * 2) If one of the strings is zero, return zero.
   */
public String multiply(String num1, String num2) {
  if (num1 == null || num2 == null) {
    return "";
  }
  if ("0".equals(num1) || "0".equals(num2)) { // If one number is 0
    return "0";
  }
  int m = num1.length(), n = num2.length();
  int[] product = new int[m + n]; // Length is at most m + n.
  // Pick one digit from one number, multiply with each digit in the other number
  for (int i = m - 1; i >= 0; i--) { // From right to left, from lower significant digit to higher
    int x = num1.charAt(i) - '0';
    for (int j = n - 1; j >= 0; j--) {
      int mul = x * (num2.charAt(j) - '0');
      product[i + j + 1] += mul;
      product[i + j] += product[i + j + 1] / 10; // Carry
      product[i + j + 1] %= 10; // i + j + 1 will cover every digit and make them < 10
    }
  }

  final StringBuilder result = new StringBuilder();
  for (int p : product) {
    if (p != 0 || result.length() != 0) {
      // Non-zero should definitely be in result
      // If something is already in result,
      // everything after should be appended
      result.append(p);
    }
  }
  return result.toString();
}
}