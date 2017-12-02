package com.freetymekiyan.algorithms.level.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 * <p>
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board =
 * <p>
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * Return ["eat","oath"].
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 * <p>
 * You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?
 * <p>
 * If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. What kind of
 * data structure could answer such query efficiently? Does a hash table work? Why or why not? How about a Trie? If you
 * would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.
 * <p>
 * Tags: Backtracking, Trie
 * Similar Problems: (M) Word Search
 */
public class WordSearch2 {

    /**
     * Build a trie for the words
     * When backtracking, move forward with the trie node
     * Do pruning when the current char is not in the trie
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    /**
     * Build a trie from the words
     * Store word in the ending node
     */
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode node = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (node.next[i] == null) node.next[i] = new TrieNode();
                node = node.next[i];
            }
            node.word = w;
        }
        return root;
    }

    /**
     * Backtrack in the board
     * Set a character to # to mark it as visited
     * Remember to reset it after all 4 adjacent nodes are traversed
     * <p>
     * Get current char in board
     * Make sure it's not already visited and it's in trie
     * Move trie node one step down to process current word
     * Set word in node to null after adding it to the result list
     * Set visited and backtrack adjacent nodes
     * Reset visited mark
     */
    private void dfs(char[][] board, int i, int j, TrieNode node, List<String> res) {
        char c = board[i][j];
        if (c == '#' || node.next[c - 'a'] == null) return;
        node = node.next[c - 'a'];
        if (node.word != null) { // Found one
            res.add(node.word);
            node.word = null; // De-dup
        }

        board[i][j] = '#'; // Mark as visited
        if (i > 0) dfs(board, i - 1, j, node, res);
        if (j > 0) dfs(board, i, j - 1, node, res);
        if (i < board.length - 1) dfs(board, i + 1, j, node, res);
        if (j < board[i].length - 1) dfs(board, i, j + 1, node, res);
        board[i][j] = c; // Reset mark
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }
}
