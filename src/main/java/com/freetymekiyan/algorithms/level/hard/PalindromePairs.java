package com.freetymekiyan.algorithms.level.hard;

import java.util.*;

/**
 * 336. Palindrome Pairs
 * <p>
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation
 * of the two words, i.e. words[i] + words[j] is a palindrome.
 * <p>
 * Example 1:
 * Given words = ["bat", "tab", "cat"]
 * Return [[0, 1], [1, 0]]
 * The palindromes are ["battab", "tabbat"]
 * Example 2:
 * Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 * Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 * The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 * <p>
 * Related Topics: Hash Table, String, Trie
 * Similar Questions: (M) Longest Palindromic Substring, (H) Shortest Palindrome
 */
public class PalindromePairs {

    /**
     * Hash Table.
     * Several cases:
     * 1. If s1 is a blank string, then for any string s2 that is a palindrome, s1+s2 and s2+s1 are palindrome.
     * 2. If s2 is the reverse of s1, s1+s2 and s2+s1 are palindrome.
     * 3. If s1[0:cut] is palindrome and there exists s2 is the reverse of s1[cut+1:], then s2+s1 is palindrome.
     * 4. If s1[cut+1:] is palindrome and there exists an s2 is the reverse of s1[0:cut], then s1+s2 is palindrome.
     * Case 1 and 2 can be combined into 3 and 4 since:
     * When cut=0, it covers empty strings.
     * When
     * To make search faster, build a HashMap to store the String-idx pairs.
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        if (words == null || words.length < 2) return Collections.emptyList();
        List<List<Integer>> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        if (map.containsKey("")) {
            int blankIdx = map.get("");
            for (int i = 0; i < words.length; i++) {
                if (isPalindrome(words[i])) {
                    if (i == blankIdx) continue;
                    res.add(Arrays.asList(blankIdx, i));
                    res.add(Arrays.asList(i, blankIdx));
                }
            }
        }

        for (int i = 0; i < words.length; i++) {
            String reversed = new StringBuilder(words[i]).reverse().toString();
            if (map.containsKey(reversed)) {
                int found = map.get(reversed);
                if (found != i) res.add(Arrays.asList(i, found));
            }
        }

        for (int i = 0; i < words.length; i++) {
            String cur = words[i];
            for (int j = 1; j < cur.length(); j++) {
                if (isPalindrome(cur.substring(0, j))) {
                    String reversed = new StringBuilder(cur.substring(j)).reverse().toString();
                    if (map.containsKey(reversed)) {
                        int found = map.get(reversed);
                        if (found != i) res.add(Arrays.asList(found, i));
                    }
                }
                if (isPalindrome(cur.substring(j))) {
                    String reversed = new StringBuilder(cur.substring(0, j)).reverse().toString();
                    if (map.containsKey(reversed)) {
                        int found = map.get(reversed);
                        if (found != i) res.add(Arrays.asList(i, found));
                    }
                }
            }
        }

        return res;
    }

    private boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }

    /**
     * Trie.
     * Checking whether 2 words form a palindrome can be optimized.
     * By checking whether the current word's prefix is some others' suffix.
     * Adding the reversed words to a Trie then checking prefix is possible.
     * Must also check the rest of the characters in the middle of the two string.
     * To see if they form a palindrome.
     * A common Trie node has a boolean isWord and an array of next nodes.
     * Add a list to the node that contains all the indices of words.
     * So that the indices can be used to retrieve the word in given array.
     * Now prefix search will return us a list of words with the suffix in the Trie.
     * Check together with each returned words that whether there is a palindrome.
     */
    public List<List<Integer>> palindromePairs2(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            addWord(root, words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            search(words, i, root, res);
        }
        return res;
    }

    /**
     * Add a word reversely to Trie.
     * This makes suffix search possible.
     * In addition, we add the words index to the node if word[0,i] is a palindrome.
     * So during search, if a word matches the suffix, the whole concatenated word will be a palindrome.
     * In the end, add the index to last node and set its index.
     */
    private void addWord(TrieNode node, String word, int index) {
        for (int i = word.length() - 1; i >= 0; i--) { // Reversely add each char in word to Trie to do suffix search.
            if (isPalindrome(word, 0, i)) node.indices.add(index); // Add to current node's list if word[0:i] is a palindrome.
            int j = word.charAt(i) - 'a';
            if (node.next[j] == null) {
                node.next[j] = new TrieNode();
            }
            node = node.next[j];
        }
        node.indices.add(index);
        node.index = index;
    }

    /**
     * Search the prefix of words[i] to see if there is a suffix match in Trie.
     * If the prefix's relative node has valid index, and it's different from i, and
     * the rest of the word is a palindrome.
     * Then word[0:j-1] is matched with some other word and word[j:] is palindrome.
     * So the concatenated string  will be a palindrome.
     * Add {i, node.index} to result.
     * If the whole word[i] is matched in Trie, add all indices pairs to result.
     */
    private void search(String[] words, int index, TrieNode node, List<List<Integer>> res) {
        String w = words[index];
        for (int i = 0; i < w.length(); i++) {
            if (node.index >= 0 && node.index != index && isPalindrome(w, i, w.length() - 1)) {
                res.add(Arrays.asList(index, node.index));
            }
            node = node.next[w.charAt(i) - 'a'];
            if (node == null) return;
        }
        for (int i : node.indices) { // Prefix all matched.
            if (index == i) continue;
            res.add(Arrays.asList(index, i));
        }
    }

    private boolean isPalindrome(String word, int s, int e) {
        while (s < e) {
            if (word.charAt(s) != word.charAt(e)) {
                return false;
            }
            s++;
            e--;
        }
        return true;
    }

    private static class TrieNode {

        TrieNode[] next;
        int index;
        List<Integer> indices;

        TrieNode() {
            next = new TrieNode[26]; // Assuming all lowercase letters.
            index = -1;
            indices = new ArrayList<>();
        }
    }
}