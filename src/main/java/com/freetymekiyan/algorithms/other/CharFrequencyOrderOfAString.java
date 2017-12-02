package com.freetymekiyan.algorithms.other;

import java.util.TreeMap;

/**
 * Given a String, output the characters in the String according to its frequency.
 * <p>
 * For example,
 * given "banana", the output should be “a 3, n 2, b 1”
 */
public class CharFrequencyOrderOfAString {

    public static void main(String[] args) {
        CharFrequencyOrderOfAString c = new CharFrequencyOrderOfAString();
        c.output("banana");
    }

    public void output(String s) {
        TreeMap<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            map.put(key, map.containsKey(key)? map.get(key) + 1 : 1);
        }
        while (map.size() > 0)
            System.out.println(map.pollFirstEntry());
    }
}
