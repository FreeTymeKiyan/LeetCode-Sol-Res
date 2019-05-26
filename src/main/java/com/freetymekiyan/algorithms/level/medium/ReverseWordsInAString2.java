package com.freetymekiyan.algorithms.level.medium;

/**
 * 186. Reverse Words in a String II
 * <p>
 * Given an input string , reverse the string word by word.
 * <p>
 * Example:
 * <p>
 * Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * Note:
 * <p>
 * A word is defined as a sequence of non-space characters.
 * The input string does not contain leading or trailing spaces.
 * The words are always separated by a single space.
 * Follow up: Could you do it in-place without allocating extra space?
 * <p>
 * Companies: Microsoft, Facebook, Amazon, Tencent, Apple, Uber
 * <p>
 * Related Topics: String
 * <p>
 * Similar Questions: (M) Reverse Words in a String, (E) Rotate Array
 */
public class ReverseWordsInAString2 {

  public void reverseWords(char[] str) {
    reverse(str, 0, str.length - 1); // Reverse the whole string first
    int r = 0;
    while (r < str.length) {
      int l = r;
      while (r < str.length && str[r] != ' ')
        r++;
      reverse(str, l, r - 1); // Reverse words one by one
      r++;
    }
  }

  private void reverse(char[] str, int l, int r) {
    while (l < r) {
      char tmp = str[l];
      str[l++] = str[r];
      str[r--] = tmp;
    }
  }
}