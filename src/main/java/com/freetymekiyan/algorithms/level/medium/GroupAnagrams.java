package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of strings, group anagrams together.
 * <p>
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Return:
 * <p>
 * | [
 * |   ["ate", "eat","tea"],
 * |   ["nat","tan"],
 * |   ["bat"]
 * | ]
 * Note: All inputs will be in lower-case.
 * <p>
 * Company Tags: Amazon, Bloomberg, Uber, Facebook, Yelp
 * Tags: Hash Table, String
 * Similar Problems: (E) Valid Anagram, (E) Group Shifted Strings
 */
public class GroupAnagrams {

    /**
     * Hash Table.
     * Use sorted word as the key, since all anagrams share the same sorted form.
     * Then same anagrams form a list as the value.
     * For each word, sort its character array and build a string key.
     * If the key is not in map yet, add an empty array list to map.
     * Then add the word to the list.
     * <p>
     * A possible improvement is sorting the word using counting sort. Two pass.
     * Since all inputs are lowercase characters.
     * Reduce sorting time from O(nlogn) to O(n).
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return Collections.emptyList();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] word = strs[i].toCharArray();
            Arrays.sort(word);
            String key = String.valueOf(word);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(strs[i]);
        }
        return new ArrayList<>(map.values());
    }
}
