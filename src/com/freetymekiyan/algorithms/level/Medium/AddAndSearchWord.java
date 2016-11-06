package com.freetymekiyan.algorithms.level.medium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Design a data structure that supports the following two operations:
 * <p>
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or '.'. A '.' means
 * it can represent any one letter.
 * <p>
 * For example:
 * <p>
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * <p>
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 * <p>
 * You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree)
 * first.
 * <p>
 * Company Tags: Facebook
 * Tags: Backtracking, Trie, Design
 * Similar Problems: (M) Implement Trie (Prefix Tree)
 */
public class AddAndSearchWord {

    private WordDictionary d;

    @Before
    public void setUp() {
        d = new WordDictionary();
    }

    @Test
    public void testEdgeCase() {
        d.addWord("a");
        Assert.assertTrue(d.search("."));
        d.addWord("ab");
        Assert.assertTrue(d.search("a"));
        Assert.assertTrue(d.search("a."));
    }

    @After
    public void tearDown() {
        d = null;
    }

    /**
     * Trie.
     * Create a trie in the word dictionary class.
     */
    public class WordDictionary {

        TrieNode root = new TrieNode();

        // Adds a word into the data structure.
        public void addWord(String word) {
            if (word == null || word.length() == 0) {
                return;
            }
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node.links[c - 'a'] == null) {
                    node.links[c - 'a'] = new TrieNode();
                }
                node = node.links[c - 'a'];
            }
            node.isEnd = true;
        }

        // Returns if the word is in the data structure. A word could
        // contain the dot character '.' to represent any one letter.
        public boolean search(String word) {
            if (word == null) {
                return false;
            }
            return searchPrefix(word, 0, root);
        }

        /**
         * Backtracking.
         * How to deal with '.'?
         * '.' can match any character.
         * So as long as current node has non-null link, search the rest of the prefix in trie.
         * If one of them returns true, return true.
         * If current character is not '.', the result is only true if:
         * 1) has next link.
         * 2) the rest of the prefix is also in trie.
         */
        private boolean searchPrefix(String word, int pos, TrieNode node) {
            if (pos == word.length()) {
                return node.isEnd;
            }
            if (word.charAt(pos) == '.') {
                for (int i = 0; i < node.links.length; i++) {
                    if (node.links[i] != null && searchPrefix(word, pos + 1, node.links[i])) {
                        return true;
                    }
                }
            } else {
                TrieNode next = node.links[word.charAt(pos) - 'a'];
                return next != null && searchPrefix(word, pos + 1, next);
            }
            return false;
        }

        class TrieNode {

            private final int R = 26;
            TrieNode[] links;
            boolean isEnd;

            public TrieNode() {
                links = new TrieNode[R];
            }
        }
    }

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
}
