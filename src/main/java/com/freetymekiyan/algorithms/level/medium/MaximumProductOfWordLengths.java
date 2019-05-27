package com.freetymekiyan.algorithms.level.medium;

import java.util.Arrays;

/**
 * 318. Maximum Product of Word Lengths
 * <p>
 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not
 * share common letters. You may assume that each word will contain only lower case letters. If no such two words exist,
 * return 0.
 * <p>
 * Example 1:
 * <p>
 * Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * Output: 16
 * Explanation: The two words can be "abcw", "xtfn".
 * Example 2:
 * <p>
 * Input: ["a","ab","abc","d","cd","bcd","abcd"]
 * Output: 4
 * Explanation: The two words can be "ab", "cd".
 * Example 3:
 * <p>
 * Input: ["a","aa","aaa","aaaa"]
 * Output: 0
 * Explanation: No such pair of words.
 * <p>
 * Companies: Google, Work Applications
 * <p>
 * Related Topics: Bit Manipulation
 */
public class MaximumProductOfWordLengths {
  public int maxProduct(String[] words) {
    Arrays.sort(words, (s1, s2) -> s2.length() - s1.length());

    int[] masks = new int[words.length];
    for (int i = 0; i < words.length; i++) {
      masks[i] = getBitMask(words[i]);
    }

    int max = 0;
    for (int i = 0; i < words.length - 1; i++) {
      if (words[i].length() * words[i].length() <= max) break;
      for (int j = i + 1; j < words.length; j++) {
        if ((masks[i] & masks[j]) == 0) {
          max = Math.max(max, words[i].length() * words[j].length());
          break;
        }
      }
    }
    return max;
  }

  private int getBitMask(String s) {
    int mask = 0;
    for (int i = 0; i < s.length(); i++) {
      int index = s.charAt(i) - 'a';
      mask |= 1 << index;
    }
    return mask;
  }
}