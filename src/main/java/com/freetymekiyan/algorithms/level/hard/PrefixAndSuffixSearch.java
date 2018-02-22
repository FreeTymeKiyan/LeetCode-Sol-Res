package com.freetymekiyan.algorithms.level.hard;

/**
 * 745. Prefix and Suffix Search
 * <p>
 * Given many words, words[i] has weight i.
 * <p>
 * Design a class WordFilter that supports one function, WordFilter.f(String prefix, String suffix). It will return the
 * word with given prefix and suffix with maximum weight. If no word exists, return -1.
 * <p>
 * Examples:
 * Input:
 * WordFilter(["apple"])
 * WordFilter.f("a", "e") // returns 0
 * WordFilter.f("b", "") // returns -1
 * Note:
 * words has length in range [1, 15000].
 * For each test case, up to words.length queries WordFilter.f may be made.
 * words[i] has length in range [1, 10].
 * prefix, suffix have lengths in range [0, 10].
 * words[i] and prefix, suffix queries consist of lowercase letters only.
 * <p>
 * Related Topics: Trie
 * Similar Questions: (M) Add and Search Word - Data structure design
 */
public class PrefixAndSuffixSearch {

    /**
     * Your WordFilter object will be instantiated and called as such:
     * WordFilter obj = new WordFilter(words);
     * int param_1 = obj.f(prefix,suffix);
     */
    static class WordFilter {

        private final TrieNode root;

        /**
         * Combine suffix and prefix to some format to enable searching.
         * What format?
         * The format should be able to:
         * 1. Return the string in Trie prefixed with this combination.
         * 2. The combination means the word has the prefix and suffix.
         * For example, look at the combination of prefix and suffix of "apple":
         * Prefix: a, ap, app, appl, apple
         * Suffix: e, le, ple, pple, apple
         * If we search for ap and le, ap can be found in the original word, le must be inserted to the front.
         * To distinguish the suffix and prefix, we can add a special character, like #, between them.
         * If we insert "le#apple", it can cover (a, le), (ap, le), (app, le), (appl, le), (apple, le).
         * For apple, we insert "e#apple", "le#apple", "ple#apple", "pple#apple", "apple#apple".
         * These cover all prefix and suffix combinations.
         */
        public WordFilter(String[] words) {
            root = new TrieNode();
            for (int w = 0; w < words.length; w++) {
                String word = words[w];
                int len = word.length();
                for (int i = 0; i <= len; i++) { // i can be equal to length to cover empty suffix case.
                    root.insert(word.substring(i, len) + '#' + word, w);
                }
            }
        }

        public int f(String prefix, String suffix) {
            return root.search(suffix + '#' + prefix);
        }

        class TrieNode {
            private int weight = 0;
            private TrieNode[] nodes = new TrieNode[27]; // 26 letters + 1 #

            public void insert(String s, int w) {
                if (s == null) return;
                TrieNode node = this;
                for (char c : s.toCharArray()) {
                    int i = getIndex(c);
                    if (node.nodes[i] == null) {
                        node.nodes[i] = new TrieNode();
                    }
                    node = node.nodes[i];
                    node.weight = w; // Should always update weight. Later inserted nodes have larger weight.
                }
            }

            public int search(String s) {
                if (s == null) return -1;
                TrieNode node = this;
                for (char c : s.toCharArray()) {
                    int i = getIndex(c);
                    if (node.nodes[i] == null) return -1;
                    node = node.nodes[i];
                }
                return node.weight;
            }

            private int getIndex(char c) {
                if (c == '#') return 26;
                return c - 'a';
            }
        }
    }
}