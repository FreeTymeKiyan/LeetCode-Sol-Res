package com.freetymekiyan.algorithms.level.easy;

/**
 * 58. Length of Last Word
 * <p>
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last
 * word in the string.
 * <p>
 * If the last word does not exist, return 0.
 * <p>
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * <p>
 * Example:
 * <p>
 * Input: "Hello World"
 * Output: 5
 * <p>
 * Related Topics: String
 */
public class LengthOfLastWord {

  public int lengthOfLastWord(String s) {
    s = s.trim();
    char space = ' ';
    if (s.indexOf(space) == -1) return s.length(); // No space
    int len = s.length();
    // Find the starting index of the last word
    for (int i = len - 1; i >= 0; i--) {
      if (s.charAt(i) == ' ' && i != len - 1) {
        return len - 1 - i;
      }
    }
    return 0;
  }
}