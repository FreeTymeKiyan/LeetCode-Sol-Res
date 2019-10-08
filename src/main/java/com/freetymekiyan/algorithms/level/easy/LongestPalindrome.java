package com.freetymekiyan.algorithms.level.easy;

/**
 * 409. Longest Palindrome
 * <p>
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can
 * be built with those letters.
 * <p>
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * <p>
 * Note:
 * Assume the length of given string will not exceed 1,010.
 * <p>
 * Example:
 * <p>
 * Input:
 * "abccccdd"
 * <p>
 * Output:
 * 7
 * <p>
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.A
 * <p>
 * Related Topics: Hash Table
 * <p>
 * Similar Questions: Palindrome Permutation (E)
 * <p>
 * Companies: Microsoft
 */
public class LongestPalindrome {

  /**
   * Hash Table.
   * From input string we can get character counts.
   * Characters with even count can all be used in a palindrome.
   * For characters with odd count, half can be used in a palindrome.
   * If the current length is even, the 1 remaining character can be put in the middle.
   * If the current length is odd, we can't do anything.
   */
  public int longestPalindrome(String s) {
    final int[] counts = new int[128];
    for (char c : s.toCharArray()) {
      counts[c]++;
    }

    int maxLength = 0;
    for (final int c : counts) {
      maxLength += c / 2 * 2;
      if (maxLength % 2 == 0 && c % 2 == 1) {
        maxLength++;
      }
    }
    return maxLength;
  }
}