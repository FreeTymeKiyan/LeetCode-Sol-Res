package com.freetymekiyan.algorithms.level.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 412. Fizz Buzz
 * <p>
 * Write a program that outputs the string representation of numbers from 1 to n.
 * <p>
 * But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”.
 * For numbers which are multiples of both three and five output “FizzBuzz”.
 * <p>
 * Example:
 * <p>
 * n = 15,
 * <p>
 * Return:
 * |[
 * |  "1",
 * |  "2",
 * |  "Fizz",
 * |  "4",
 * |  "Buzz",
 * |  "Fizz",
 * |  "7",
 * |  "8",
 * |  "Fizz",
 * |  "Buzz",
 * |  "11",
 * |  "Fizz",
 * |  "13",
 * |  "14",
 * |  "FizzBuzz"
 * |]
 * <p>
 * Companies: Google, Bloomberg, Apple, IBM
 */
public class FizzBuzz {

  private static final String FIZZ = "Fizz";
  private static final String BUZZ = "Buzz";

  /**
   * A simpler version.
   * If a number is both divisible by 3 and 5, both "Fizz" and "Buzz" will be added to the string builder.
   * There's no need for another special case to add "FizzBuzz".
   */
  public List<String> fizzBuzz(int n) {
    final List<String> res = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      StringBuilder builder = new StringBuilder();
      if (i % 3 == 0) {
        builder.append(FIZZ);
      }
      if (i % 5 == 0) {
        builder.append(BUZZ);
      }
      if (builder.length() == 0) {
        builder.append(i);
      }
      res.add(builder.toString());
    }
    return res;
  }

  /**
   * A more verbose version by adding each case strictly.
   */
  public List<String> fizzBuzz2(int n) {
    final List<String> ans = new ArrayList<>();
    for (int num = 1; num <= n; num++) {
      boolean divisibleBy3 = (num % 3 == 0);
      boolean divisibleBy5 = (num % 5 == 0);
      if (divisibleBy3 && divisibleBy5) {
        // Divides by both 3 and 5, add FizzBuzz
        ans.add("FizzBuzz");
      } else if (divisibleBy3) {
        // Divides by 3, add Fizz
        ans.add("Fizz");
      } else if (divisibleBy5) {
        // Divides by 5, add Buzz
        ans.add("Buzz");
      } else {
        // Not divisible by 3 or 5, add the number
        ans.add(Integer.toString(num));
      }
    }
    return ans;
  }
}