package com.freetymekiyan.algorithms.level.medium;

import java.util.*;

/**
 * Given an array of strings, return all groups of strings that are anagrams.
 * <p>
 * Note: All inputs will be in lower-case.
 * <p>
 * Tags: Hash table, String
 */
class Anagrams {
    public static void main(String[] args) {
        String[] strs = {"dog", "dot", "cog", "log", "god", "tod"};
        List<String> res = anagrams(strs);
        System.out.println(res.toString());
    }

    /**
     * Use map<String, Integer>
     * Integer is initialized as the index, updated to -1 when the word is
     * added to map to make sure that no duplicate situation happens
     */
    public static List<String> anagrams(String[] strs) {
        List<String> res = new ArrayList<>();
        if (strs == null || strs.length == 0) return res;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) { // traverse the array
            /*generate key*/
            char[] word = strs[i].toCharArray();
            Arrays.sort(word);
            String key = new String(word);
            if (map.containsKey(key)) {
                res.add(strs[i]); // add this string
                if (map.get(key) >= 0) { // key string not added
                    res.add(strs[map.get(key)]);
                    map.put(key, -1); // mark already added as -1
                }
            } else map.put(key, i); // first put sorted string and index
        }
        return res;
    }
}
