package com.freetymekiyan.algorithms.level.easy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 405. Convert A Number To Hexidecimal
 * <p>
 * Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is
 * used.
 * <p>
 * Note:
 * <p>
 * All letters in hexadecimal (a-f) must be in lowercase.
 * The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero
 * character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
 * The given number is guaranteed to fit within the range of a 32-bit signed integer.
 * You must not use any method provided by the library which converts/formats the number to hex directly.
 * Example 1:
 * <p>
 * Input:
 * 26
 * <p>
 * Output:
 * "1a"
 * Example 2:
 * <p>
 * Input:
 * -1
 * <p>
 * Output:
 * "ffffffff"
 * <p>
 * Related Topics: Bit Manipulation
 * <p>
 * Companies: Microsoft
 */
public class ConvertANumberToHexadecimal {

  private ConvertANumberToHexadecimal c;

  /**
   * Math
   * Given a number and a base, convert the number
   */
  public String convert(int num, int base) {
    // Deal with negative numbers
    boolean isNegative = false;
    if (num < 0 && base == 10) {
      isNegative = true;
      num = -num;
    }

    // Process individual digits
    final StringBuilder sb = new StringBuilder();
    while (num != 0) {
      int rem = num % base;
      char c = (char) ((rem > 9) ? (rem - 10) + 'a' : rem + '0');
      sb.insert(0, c);
      num = num / base;
    }

    // If number is negative, append '-'
    if (isNegative)
      sb.insert(0, '-');
    return sb.toString();
  }


  @Before
  public void setUp() {
    c = new ConvertANumberToHexadecimal();
  }

  @Test
  public void testPositiveNumbers() {
    String res = c.convert(80, 16);
    Assert.assertEquals("50", res);
    res = c.convert(3200, 16);
    Assert.assertEquals("c80", res);
  }

  @After
  public void tearDown() {
    c = null;
  }
}
