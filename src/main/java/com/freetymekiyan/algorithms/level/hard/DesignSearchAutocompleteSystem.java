package com.freetymekiyan.algorithms.level.hard;

import java.util.*;

/**
 * 642. Design Search Autocomplete System
 * <p>
 * Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a
 * special character '#'). For each character they type except '#', you need to return the top 3 historical hot
 * sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:
 * <p>
 * The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 * The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences
 * have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
 * If less than 3 hot sentences exist, then just return as many as you can.
 * When the input is a special character, it means the sentence ends, and in this case, you need to return an empty
 * list.
 * Your job is to implement the following functions:
 * <p>
 * The constructor function:
 * <p>
 * AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences
 * is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed.
 * Your system should record these historical data.
 * <p>
 * Now, the user wants to input a new sentence. The following function will provide the next character the user types:
 * <p>
 * List<String> input(char c): The input c is the next character typed by the user. The character will only be
 * lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence
 * should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as
 * the part of sentence already typed.
 * <p>
 * Example:
 * Operation: AutocompleteSystem(["i love you", "island", "ironman", "i love leetcode"], [5,3,2,2])
 * The system have already tracked down the following sentences and their corresponding times:
 * "i love you" : 5 times
 * "island" : 3 times
 * "ironman" : 2 times
 * "i love leetcode" : 2 times
 * Now, the user begins another search:
 * <p>
 * Operation: input('i')
 * Output: ["i love you", "island","i love leetcode"]
 * Explanation:
 * There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree.
 * Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we
 * only need to output top 3 hot sentences, so "ironman" will be ignored.
 * <p>
 * Operation: input(' ')
 * Output: ["i love you","i love leetcode"]
 * Explanation:
 * There are only two sentences that have prefix "i ".
 * <p>
 * Operation: input('a')
 * Output: []
 * Explanation:
 * There are no sentences that have prefix "i a".
 * <p>
 * Operation: input('#')
 * Output: []
 * Explanation:
 * The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following
 * input will be counted as a new search.
 * <p>
 * Note:
 * The input sentence will always start with a letter and end with '#', and only one blank space will exist between two
 * words.
 * The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in
 * the historical data won't exceed 100.
 * Please use double-quote instead of single-quote when you write test cases even for a character input.
 * Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are
 * persisted across multiple test cases. Please see here for more details.
 */
public class DesignSearchAutocompleteSystem {

    /**
     * Your AutocompleteSystem object will be instantiated and called as such:
     * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
     * List<String> param_1 = obj.input(c);
     */
    static class AutocompleteSystem {

        private final TrieNode root;
        private StringBuilder prefix = new StringBuilder();
        private TrieNode prev;

        /**
         * Go through the sentences and times and build a Trie.
         */
        public AutocompleteSystem(String[] sentences, int[] times) {
            root = new TrieNode();
            prev = root;
            for (int i = 0; i < sentences.length; i++) {
                root.insert(sentences[i], times[i]);
            }
        }

        /**
         * If input c is '#':
         * 1. Update trie with full sentence.
         * 2. Reset variables, like previous node and prefix.
         * 3. Return an empty list.
         * Else the sentence is not ending:
         * 1. Update prefix by appending the new char.
         * 2. Check whether previous node exists.
         * | 2.1. If yes, search the node, update node to its child, return search result.
         * | 2.2. If not, return empty list.
         */
        public List<String> input(char c) {
            if (c == '#') {
                root.insert(prefix.toString(), 1);
                prev = root;
                prefix.setLength(0);
                return Collections.emptyList();
            }
            prefix.append(c);
            if (prev != null) {
                List<String> result = prev.search(c);
                prev = prev.getChild(c);
                return result;
            } else {
                return Collections.emptyList();
            }
        }

        /**
         * A trie that indexes all the sentences and the times they have been searched.
         * Optimization:
         * 1. Add a map to each node, to store all possible sentences and times, to avoid generation of possible
         * sentences every time.
         * 2. Search in direct child, to avoid search the full sentence every time.
         */
        class TrieNode {

            private TrieNode[] children = new TrieNode[27]; // 26 letters + 1 blank space
            private Map<String, Integer> sentenceToTimes = new HashMap<>();

            public void insert(String sentence, int times) {
                if (sentence == null) return;
                TrieNode current = root;
                for (int i = 0; i < sentence.length(); i++) {
                    char c = sentence.charAt(i);
                    TrieNode child = current.children[getIndex(c)];
                    if (child == null) {
                        child = new TrieNode();
                        current.children[getIndex(c)] = child;
                    }
                    current = child;
                    current.sentenceToTimes.put(sentence, current.sentenceToTimes.getOrDefault(sentence, 0) + times);
                }
            }

            /**
             * Track previous node so that we only search for one node.
             */
            public List<String> search(char c) {
                if (children[getIndex(c)] == null) return Collections.emptyList();
                return getTop3Hottest(children[getIndex(c)].sentenceToTimes);
            }

            public List<String> search(String s) {
                if (s == null) return Collections.emptyList();
                TrieNode current = root;
                for (char c : s.toCharArray()) {
                    if (current.children[getIndex(c)] == null) {
                        return Collections.emptyList();
                    }
                    current = current.children[getIndex(c)];
                }
                return getTop3Hottest(current.sentenceToTimes);
            }

            /**
             * Keep a window of 3 highest times sentences with a Priority Queue (min heap).
             * Note that the small times go first, then the lexicographically larger ones go first.
             * These are removed first from the queue when the window size of 3 exceeds.
             * Then get all sentences in the priority queue in reverse order 1 by 1 to generate the result.
             */
            private List<String> getTop3Hottest(Map<String, Integer> results) {
                PriorityQueue<Map.Entry<String, Integer>> hottest = new PriorityQueue<>(3,
                        (a, b) -> a.getValue() != b.getValue() ? a.getValue() - b.getValue() : b.getKey().compareTo(a.getKey()));
                for (Map.Entry<String, Integer> entry : results.entrySet()) {
                    hottest.offer(entry);
                    if (hottest.size() > 3) {
                        hottest.poll();
                    }
                }

                int size = hottest.size();
                List<String> result = new ArrayList<>(size);
                for (int i = 0; i < size; i++) {
                    result.add(0, hottest.poll().getKey());
                }
                return result;
            }

            public TrieNode getChild(char c) {
                return children[getIndex(c)];
            }

            private int getIndex(char c) {
                if (c == ' ') return 26;
                return c - 'a';
            }
        }
    }
}