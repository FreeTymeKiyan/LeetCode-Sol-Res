package com.freetymekiyan.algorithms.level.medium;

/**
 * 151. Reverse Words in a String
 * <p>
 * Given an input string, reverse the string word by word.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: "the sky is blue"
 * Output: "blue is sky the"
 * Example 2:
 * <p>
 * Input: "  hello world!  "
 * Output: "world! hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * Example 3:
 * <p>
 * Input: "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 * <p>
 * <p>
 * Note:
 * <p>
 * A word is defined as a sequence of non-space characters.
 * Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or
 * trailing spaces.
 * You need to reduce multiple spaces between two words to a single space in the reversed string.
 * <p>
 * Follow up:
 * <p>
 * For C programmers, try to solve it in-place in O(1) extra space.
 * <p>
 * Related Topics: String
 * <p>
 * Similar Questions: Reverse Words in a String II (M)
 */
public class ReverseWordsInAString {
  public String reverseWords(String s) {
    if (s == null || s.length() == 0) return "";
    s = s.trim();
    StringBuilder res = new StringBuilder();
    String[] words = s.split(" ");
    for (int i = words.length - 1; i >= 0; i--) {
      if (!words[i].equals("")) {
        res.append(words[i]);
        if (i != 0) res.append(" ");
      }
    }
    return res.toString(); // remove last space
  }
}