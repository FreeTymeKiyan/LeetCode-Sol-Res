package com.freetymekiyan.algorithms.level.hard;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 159. Longest Substring with At Most Two Distinct Characters
 * <p>
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 * <p>
 * Example 1:
 * <p>
 * Input: "eceba"
 * Output: 3
 * Explanation: t is "ece" which its length is 3.
 * Example 2:
 * <p>
 * Input: "ccaabbb"
 * Output: 5
 * Explanation: t is "aabbb" which its length is 5.
 * <p>
 * Companies: Google, Amazon, Facebook, Microsoft
 * <p>
 * Related Topics: Hash Table, Two Pointers, String, Sliding Window
 * <p>
 * Similar Questions: (M) Longest Substring Without Repeating Characters, (H) Sliding Window Maximum, (H) Longest
 * <p>
 * Substring with At Most K Distinct Characters, (H) Subarrays with K Different Integers
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {

  public int lengthOfLongestSubstringTwoDistinct(String s) {
    if (s == null || s.isEmpty()) {
      return 0;
    }
    int maxNumberOfDistincts = 2;
    if (s.length() < maxNumberOfDistincts + 1) {
      return s.length();
    }
    int length = 1;
    Map<Character, Integer> letterToCounts = new HashMap<>();
    for (int start = 0, end = 0; end < s.length(); end++) {
      while (end < s.length()) {
        char letter = s.charAt(end);
        letterToCounts.put(letter, letterToCounts.getOrDefault(letter, 0) + 1);
        if (letterToCounts.size() > maxNumberOfDistincts) {
          break;
        }
        if (length < end - start + 1) {
          length = end - start + 1;
        }
        end++;
      }
      while (start <= end && letterToCounts.size() > maxNumberOfDistincts) {
        char letter = s.charAt(start);
        final int count = letterToCounts.get(letter);
        if (count == 1) {
          letterToCounts.remove(letter);
        } else {
          letterToCounts.put(letter, letterToCounts.get(letter) - 1);
        }
        start++;
      }
    }
    return length;
  }

  public int lengthOfLongestSubstringTwoDistinct2(String s) {
    int n = s.length();
    if (n < 3) return n;

    // sliding window left and right pointers
    int left = 0;
    int right = 0;
    // hashmap character -> its rightmost position
    // in the sliding window
    Map<Character, Integer> hashmap = new HashMap<>();

    int maxLen = 2;

    while (right < n) {
      // window contains less than 3 characters
      if (hashmap.size() < 3)
        hashmap.put(s.charAt(right), right++);

      // window contains 3 characters
      if (hashmap.size() == 3) {
        // delete the leftmost character
        int del_idx = Collections.min(hashmap.values());
        hashmap.remove(s.charAt(del_idx));
        // move left pointer of the slidewindow
        left = del_idx + 1;
      }

      maxLen = Math.max(maxLen, right - left);
    }
    return maxLen;
  }
}