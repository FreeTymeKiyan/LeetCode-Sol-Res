package com.freetymekiyan.algorithms.level.easy;

import java.util.Objects;

/**
 * 13. Roman to Integer
 * <p>
 * Given a roman numeral, convert it to an integer.
 * <p>
 * Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * Company Tags: Microsoft, Bloomberg, Uber, Facebook, Yahoo
 * Tags: Math, String
 * Similar Problems: (M) Integer to Roman
 */
public class RomanToInteger {

  // Create mappings between roman characters and integers
  public static final char[] ROMANS = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
  public static final int[] INTEGERS = {1, 5, 10, 50, 100, 500, 1000};

  /**
   * String, Math.
   * First need to know the letter to value mapping.
   * Then need to clarify whether the input string can mean negative, or there is only uppercase.
   * <p>
   * For each character from the end to front:
   * | Add the value to result according to the character.
   * | Only when for C=100 X=10 I=1, need to compare current number with 500, 50 and 5.
   * | If result is larger or equal, they mean negative values.
   * | Subtract them from result.
   * <p>
   * Why traversing backwards?
   * Because if traverse forwards, we won't be able to figure out whether C and X and I are positive or negative.
   * Will have to check: whether there is a V after I's, or L after X's, or D after C's, which is complicated.
   * Only need to check how large is the current value if traversing backwards.
   */
  public int romanToInt(String s) {
    Objects.requireNonNull(s, "The given roman number string is null");
    int res = 0;
    for (int i = s.length() - 1; i >= 0; i--) {
      switch (s.charAt(i)) {
        case 'M':
          res += 1000;
          break;
        case 'D':
          res += 500;
          break;
        case 'C':
          res += 100 * (res >= 500 ? -1 : 1); // For CD or CM.
          break;
        case 'L':
          res += 50;
          break;
        case 'X':
          res += 10 * (res >= 50 ? -1 : 1); // For XL or XC.
          break;
        case 'V':
          res += 5;
          break;
        case 'I':
          res += (res >= 5 ? -1 : 1); // For IV or IX.
          break;
        default:
          break;
      }
    }
    return res;
  }
}