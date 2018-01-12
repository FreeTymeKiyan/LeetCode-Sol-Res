package com.freetymekiyan.algorithms.level.medium;

import java.util.*;

/**
 * 49. Group Anagrams
 * <p>
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
        Map<String, List<String>> groups = new HashMap<>();
        for (String s : strs) {
            String key = getSortedKey(s);
//            String key = getMapKey(s);
            if (!groups.containsKey(key)) {
                groups.put(key, new ArrayList<>());
            }
            groups.get(key).add(s);
        }
        return new ArrayList<>(groups.values());
    }

    private String getSortedKey(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }

    private String getMapKey(String word) {
        int[] counts = new int[26]; // 26 lowercase letters.
        for (char c : word.toCharArray()) {
            counts[c]++;
        }
        StringBuilder key = new StringBuilder();
        for (int n : counts) {
            key.append(n);
        }
        return key.toString();
    }
}