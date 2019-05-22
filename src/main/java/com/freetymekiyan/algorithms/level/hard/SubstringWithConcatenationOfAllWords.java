package com.freetymekiyan.algorithms.level.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 30. Substring with Concatenation of All Words
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of
 * substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * s = "barfoothefoobarman",
 * words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 * Example 2:
 * <p>
 * Input:
 * s = "wordgoodgoodgoodbestword",
 * words = ["word","good","best","word"]
 * Output: []
 * <p>
 * Related Topics: Hash Table, Two Pointers, String
 */
public class SubstringWithConcatenationOfAllWords {

  public List<Integer> findSubstring(String S, String[] L) {
    final List<Integer> res = new ArrayList<>();
    if (S == null || L == null || L.length == 0) {
      return res;
    }
    int len = L[0].length();
    final Map<String, Integer> map = new HashMap<>();
    for (String w : L) {
      map.put(w, map.containsKey(w) ? map.get(w) + 1 : 1);
    }
    for (int i = 0; i <= S.length() - len * L.length; i++) {
      final Map<String, Integer> copy = new HashMap<>(map);
      for (int j = 0; j < L.length; j++) {
        String str = S.substring(i + j * len, i + j * len + len);
        if (copy.containsKey(str)) {
          int count = copy.get(str);
          if (count == 1) copy.remove(str);
          else copy.put(str, count - 1);
          if (copy.isEmpty()) {
            res.add(i);
            break;
          }
        } else break;
      }
    }
    return res;
  }
}