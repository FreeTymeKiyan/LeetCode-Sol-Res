package com.freetymekiyan.algorithms.level.medium;

/**
 * Given an integer, convert it to a roman numeral.
 * <p>
 * Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * Tags: Math, String
 */
class IntToRoman {

  // Create mappings of integers and romans, from high to low.
  public static final int[] INTEGERS = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
  public static final String[] ROMANS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

  /**
   * Recursion.
   * <p>
   * Base case:
   * If the integer is less than 1, which means there's no roman equivalent, then return empty string.
   * <p>
   * Recurrence relation:
   * The final roman numeral = The largest roman character possible + the rest of the integer in roman numeral.
   * <p>
   * Go through the integers from high to low, use as many large number characters as possible.
   * If num >= dict, a roman character can be inserted.
   * Pass rest of the integer to next recursion.
   */
  public String intToRoman(int num) {
    for (int i = 0; i < INTEGERS.length; i++) {
      if (num >= INTEGERS[i]) {
        return ROMANS[i] + intToRoman(num - INTEGERS[i]);
      }
    }
    return ""; // Base case if num < 1 return empty string
  }

  /**
   * While loop.
   * <p>
   * Go through the dict, if num >= INTEGERS[i], append roman integer
   * num -= INTEGERS[i]
   * if num < INTEGERS[i], i++
   */
  public String intToRoman2(int num) {
    final StringBuilder roman = new StringBuilder();
    int i = 0;
    while (num > 0 && i < INTEGERS.length) {
      if (num >= INTEGERS[i]) {
        roman.append(ROMANS[i]);
        num -= INTEGERS[i]; // Update num
      } else {
        i++; // Move to next roman character
      }
    }
    return roman.toString();
  }
}