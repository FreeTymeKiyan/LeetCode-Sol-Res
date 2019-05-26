package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 249. Group Shifted Strings
 * <p>
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep
 * "shifting" which forms the sequence:
 * <p>
 * "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting
 * sequence.
 * <p>
 * Example:
 * <p>
 * Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 * Output:
 * | [
 * |   ["abc","bcd","xyz"],
 * |   ["az","ba"],
 * |   ["acef"],
 * |   ["a","z"]
 * | ]
 * <p>
 * Companies: Facebook, Google, Apple, Rubrik, Uber
 * <p>
 * Related Topics: Hash Table, String
 * <p>
 * Similar Questions: (M) Group Anagrams
 */
public class GroupShiftedStrings {

  public List<List<String>> groupStrings(String[] strings) {
    List<List<String>> result = new ArrayList<>();
    Map<String, List<String>> map = new HashMap<>();
    for (String str : strings) {
      int offset = str.charAt(0) - 'a';
      StringBuilder key = new StringBuilder();
      for (int i = 0; i < str.length(); i++) {
        char c = (char) (str.charAt(i) - offset);
        if (c < 'a') {
          c += 26;
        }
        key.append(c);
      }
      if (!map.containsKey(key.toString())) {
        List<String> list = new ArrayList<>();
        map.put(key.toString(), list);
      }
      map.get(key.toString()).add(str);
    }
    for (String key : map.keySet()) {
      List<String> list = map.get(key);
      Collections.sort(list);
      result.add(list);
    }
    return result;
  }
}
