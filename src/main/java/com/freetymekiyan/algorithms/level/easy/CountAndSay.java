package com.freetymekiyan.algorithms.level.easy;

/**
 * 38. Count and Say
 * <p>
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * <p>
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 * <p>
 * Note: The sequence of integers will be represented as a string.
 * <p>
 * Company Tags: Facebook
 * Tags: String
 * Similar Problems: (M) Encode and Decode Strings
 */
class CountAndSay {

  /**
   * String. Simulate the process.
   * Generate the next sequence.
   * Repeat n-1 times.
   */
public String countAndSay(int n) {
  String res = "1";
  while (n > 1) {
    StringBuilder s = new StringBuilder();
    for (int i = 0, j = i; i < res.length(); i = j) {
      while (j < res.length() && res.charAt(i) == res.charAt(j)) {
        j++;
      }
      s.append(j - i).append(res.charAt(i));
    }
    res = s.toString();
    n--;
  }
  return res;
}
}