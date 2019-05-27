package com.freetymekiyan.algorithms.level.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 291. Word Pattern II
 * <p>
 * Given a pattern and a string str, find if str follows the same pattern.
 * <p>
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring
 * in str.
 * <p>
 * Example 1:
 * <p>
 * Input: pattern = "abab", str = "redblueredblue"
 * Output: true
 * Example 2:
 * <p>
 * Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
 * Output: true
 * Example 3:
 * <p>
 * Input: pattern = "aabb", str = "xyzabcxzyabc"
 * Output: false
 * Notes:
 * You may assume both pattern and str contains only lowercase letters.
 * <p>
 * Companies: Uber, Dropbox, Facebook, Airbnb, Google
 * <p>
 * Related Topics: Backtracking
 * <p>
 * Similar Questions: (E) Word Pattern
 */
public class WordPattern2 {

  Map<Character, String> map = new HashMap<>();
  Set<String> set = new HashSet<>();

  public boolean wordPatternMatch(String pattern, String str) {
    if (pattern.isEmpty()) return str.isEmpty();
    if (map.containsKey(pattern.charAt(0))) {
      String value = map.get(pattern.charAt(0));
      if (str.length() < value.length() || !str.substring(0, value.length()).equals(value)) return false;
      if (wordPatternMatch(pattern.substring(1), str.substring(value.length()))) return true;
    } else {
      for (int i = 1; i <= str.length(); i++) {
        if (set.contains(str.substring(0, i))) continue;
        map.put(pattern.charAt(0), str.substring(0, i));
        set.add(str.substring(0, i));
        if (wordPatternMatch(pattern.substring(1), str.substring(i))) return true;
        set.remove(str.substring(0, i));
        map.remove(pattern.charAt(0));
      }
    }
    return false;
  }

  public boolean wordPatternMatch2(String pattern, String str) {
    String[] map = new String[26]; // mapping of characters 'a' - 'z'
    Set<String> set = new HashSet<>(); // mapped result of 'a' - 'z'
    return wordPatternMatch(pattern, str, map, set, 0, str.length() - 1, 0, pattern.length() - 1);
  }

  private boolean wordPatternMatch(String pattern, String str, String[] map, Set<String> set, int start, int end, int startP, int endP) {
    if (startP == endP + 1 && start == end + 1) return true; // both pattern and str are exhausted
    if ((startP > endP && start <= end) || (startP < endP && start > end))
      return false; // either of pattern or str is exhausted

    char ch = pattern.charAt(startP);
    String matched = map[ch - 'a'];
    if (matched != null) { // ch is already mapped, then continue
      int count = matched.length();
      return start + count <= end + 1 && matched.equals(str.substring(start, start + count)) // substring equals previously mapped string
          && wordPatternMatch(pattern, str, map, set, start + matched.length(), end, startP + 1, endP); // moving forward
    } else {
      int endPoint = end;
      for (int i = endP; i > startP; i--) {
        endPoint -= map[pattern.charAt(i) - 'a'] == null ? 1 : map[pattern.charAt(i) - 'a'].length();
      }
      for (int i = start; i <= endPoint; i++) { // try every possible mapping, from 1 character to the end
        matched = str.substring(start, i + 1);
        if (set.contains(matched)) continue; // different pattern cannot map to same substring

        map[ch - 'a'] = matched; // move forward, add corresponding mapping and set content
        set.add(matched);

        if (wordPatternMatch(pattern, str, map, set, i + 1, end, startP + 1, endP)) return true;

        else { // backtracking, remove corresponding mapping and set content
          map[ch - 'a'] = null;
          set.remove(matched);
        }
      }
    }
    return false; // exhausted
  }


}