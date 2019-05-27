package com.freetymekiyan.algorithms.level.hard;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 340. Longest Substring with At Most K Distinct Characters
 * <p>
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: T is "ece" which its length is 3.
 * Example 2:
 * <p>
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: T is "aa" which its length is 2.
 * <p>
 * Companies: Facebook, Amazon, Google, Bloomberg, AppDynamics, Coupang
 * <p>
 * Related Topics: Hash Table, String, Sliding Window
 * <p>
 * Similar Questions: (M) Longest Substring Without Repeating Characters, (H) Longest Substring with At Most Two
 * Distinct Characters, (M) Longest Repeating Character Replacement, (H) Subarrays with K Different Integers, (M) Max
 * Consecutive Ones III
 */
public class LongestSubstringWithAtMostKDistinctCharacters {

  public int lengthOfLongestSubstringKDistinct(String s, int k) {
    int n = s.length();
    if (n * k == 0) return 0;

    // sliding window left and right pointers
    int left = 0;
    int right = 0;
    // hashmap character -> its rightmost position
    // in the sliding window
    LinkedHashMap<Character, Integer> hashmap = new LinkedHashMap<>(k + 1);

    int maxLength = 1;

    while (right < n) {
      Character character = s.charAt(right);
      // if character is already in the hashmap -
      // delete it, so that after insert it becomes
      // the rightmost element in the hashmap
      if (hashmap.containsKey(character))
        hashmap.remove(character);
      hashmap.put(character, right++);

      // slidewindow contains k + 1 characters
      if (hashmap.size() == k + 1) {
        // delete the leftmost character
        Map.Entry<Character, Integer> leftmost = hashmap.entrySet().iterator().next();
        hashmap.remove(leftmost.getKey());
        // move left pointer of the slidewindow
        left = leftmost.getValue() + 1;
      }

      maxLength = Math.max(maxLength, right - left);
    }
    return maxLength;
  }

  public int lengthOfLongestSubstringKDistinct2(String s, int k) {
    int n = s.length();
    if (n * k == 0) return 0;

    // sliding window left and right pointers
    int left = 0;
    int right = 0;
    // hashmap character -> its rightmost position
    // in the sliding window
    Map<Character, Integer> hashmap = new HashMap<Character, Integer>();

    int maxLength = 1;

    while (right < n) {
      // add new character and move right pointer
      hashmap.put(s.charAt(right), right++);

      // slidewindow contains 3 characters
      if (hashmap.size() == k + 1) {
        // delete the leftmost character
        int del_idx = Collections.min(hashmap.values());
        hashmap.remove(s.charAt(del_idx));
        // move left pointer of the slidewindow
        left = del_idx + 1;
      }

      maxLength = Math.max(maxLength, right - left);
    }
    return maxLength;
  }
}