package com.freetymekiyan.algorithms.level.medium;

/**
 * 208. Implement Trie (Prefix Tree)
 * <p>
 * Implement a trie with insert, search, and startsWith methods.
 * <p>
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 * <p>
 * Company Tags: Google, Uber, Facebook, Twitter, Microsoft, Bloomberg
 * Tags: Design, Trie
 * Similar Problems: (M) Add and Search Word - Data structure design
 */
public class ImplementTrie {

    public static class Trie {

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            if (word == null) {
                return;
            }
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!node.hasLink(c)) {
                    node.putNode(c);
                }
                node = node.getNode(c);
            }
            node.setEnd();
        }

        // search a prefix or whole key in trie and
        // returns the node where search ends
        private TrieNode searchPrefix(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!node.hasLink(c)) {
                    return null;
                }
                node = node.getNode(c);
            }
            return node;
        }

        // Returns if the word is in the trie.
        public boolean search(String word) {
            if (word == null) {
                return false;
            }
            TrieNode node = searchPrefix(word);
            return node != null && node.isEnd(); // Note to check node.isEnd().
        }

        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            if (prefix == null) {
                return false;
            }
            TrieNode node = searchPrefix(prefix);
            return node != null;
        }
    }

    static class TrieNode {

        private final int R = 26;
        private TrieNode[] links;
        private boolean end;

        // Initialize your data structure here.
        public TrieNode() {
            links = new TrieNode[R];
        }

        public boolean hasLink(char ch) {
            return links[ch - 'a'] != null;
        }

        public TrieNode getNode(char ch) {
            return links[ch - 'a'];
        }

        public void putNode(char ch) {
            links[ch - 'a'] = new TrieNode();
        }

        public void setEnd() {
            end = true;
        }

        public boolean isEnd() {
            return end;
        }
    }

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
}