package com.freetymekiyan.algorithms.level.hard;

import java.util.HashMap;

/**
 * Minimum Window Substring
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 * Tags: Hash Table Two Pointers String
 * Similar Problems:  (H) Substring with Concatenation of All Words (M) Minimum Size Subarray Sum (H) Sliding Window Maximum
 * 
 * Analysis:
 * 1.Store the char in string t as a key and times appearing as value into a hashmap;
 * 2.If char at s is contained in hashmap, value(times) in map - 1 and count ++;
 * 3.When count = t.length(), minLenght and minStart records the min number
 * 4.Slide window and let the left point to the first char in s ,which is in map  
 *        s                        t        
 * A D O B E C O D E B A N C      A B C
 * A D O B E C 
 * B E C O D E B A
 * C O D E B A
 * B A N C
 * 
 * @author chenshuna
 */

public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {
        if(s.length() == 0 || t.length() ==0 || s.length() < t.length()) {
            return "";
        }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(char c : t.toCharArray()) {
            map.put(c, (map.containsKey(c) ? map.get(c) : 0) + 1);
        }
        int left = 0;
        int count = 0;
        int minStart = 0;
        int minLength = s.length() + 1;
        for(int right = 0; right < s.length(); right++) {
            if(map.containsKey(s.charAt(right))) {
                map.put(s.charAt(right), map.get(s.charAt(right)) - 1);
                if(map.get(s.charAt(right)) >= 0) { 
                    count++;
                }
                while(count == t.length()) {
                    if((right - left + 1) < minLength) {
                        minStart = left;
                        minLength = right - left + 1;
                    }
                    if(map.containsKey(s.charAt(left))) {
                        map.put(s.charAt(left), map.get(s.charAt(left)) + 1 );
                        if(map.get(s.charAt(left)) > 0) {
                            count--;
                        }
                    }
                    left++;
                }
            }
        }
        if(minLength == s.length()+1) {
            return "";
        }
        return s.substring(minStart, minStart + minLength);
    }

    public static void main(String[] args) {
        System.out.print(minWindow("ADOBECODEBANC", "ABC"));
    }
}