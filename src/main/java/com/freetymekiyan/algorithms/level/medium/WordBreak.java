package com.freetymekiyan.algorithms.level.medium;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139. Word Break
 * <p>
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of
 * one or more dictionary words.
 * <p>
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 * <p>
 * Return true because "leetcode" can be segmented as "leet code".
 * <p>
 * Company Tags: Google, Uber, Facebook, Amazon, Yahoo, Bloomberg, Pocket, Gems, Square, Coupang
 * Related Topics: Dynamic Programming
 * Similar Questions: (H) Word Break 2
 */
public class WordBreak {

    /**
     * DP. Bottom-up.
     * Build a boolean array of size n+1 for break results at different lengths.
     * Recurrence:
     * dp[i] = dp[j] && dict.contains(s.substring(j, i)), 0 <= j <= i
     * Base case:
     * String with 0 length is true, since dp[i] contains the whole word, s.substring(0, i).
     * If dp[0] is false, we won't be able to get true in this case, which is wrong.
     * Implementation:
     * For each length i, check from 0 to i whether there are both:
     * 1. an already divided previous string.
     * 2. a substring in dictionary.
     * If yes, this substring is breakable.
     * Return whether the whole string is breakable in the end.
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        boolean[] breakable = new boolean[s.length() + 1];
        breakable[0] = true;
        for (int i = 1; i < breakable.length; i++) {
            for (int j = i; j >= 0; j--) {
                if (breakable[j] && words.contains(s.substring(j, i))) {
                    breakable[i] = true;
                    break; // Pruning. Found one is enough.
                }
            }
        }
        return breakable[s.length()];
    }

    /**
     * DP. Top-down.
     * If a String s is breakable, it consists of one word from the wordDict.
     * And the rest of the String is also breakable.
     * When a String is breakable, store it in a set to avoid function calling again.
     * When a String is unbreakable, store it in another set to avoid calling again.
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        return wordBreak(s, new HashSet<>(wordDict), new HashSet<String>());
    }

    private boolean wordBreak(String s, Set<String> words, Set<String> unbreakable) {
        if (words.contains(s)) return true;
        if (unbreakable.contains(s)) return false;
        for (int i = 1; i <= s.length(); i++) {
            String pre = s.substring(0, i);
            if (words.contains(pre)) {
                if (i == s.length()) {
                    words.add(s);
                    return true;
                }
                if (wordBreak(s.substring(i), words, unbreakable)) {
                    words.add(s.substring(i));
                    return true;
                }
            } else {
                unbreakable.add(pre);
            }
        }
        return false;
    }
}