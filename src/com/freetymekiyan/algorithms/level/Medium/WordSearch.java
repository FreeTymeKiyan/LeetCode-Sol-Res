package com.freetymekiyan.algorithms.level.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally
 * or vertically neighboring. The same letter cell may not be used more than once.
 * <p>
 * For example,
 * Given board =
 * <p>
 * | [
 * |   ['A','B','C','E'],
 * |   ['S','F','C','S'],
 * |   ['A','D','E','E']
 * | ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 * <p>
 * Company Tags: Microsoft, Bloomberg, Facebook
 * Tags: Array, Backtracking
 * Similar Problems: (H) Word Search II
 */
public class WordSearch {

    /**
     * Backtracking.
     * For each character in board, start backtracking if the first character matches.
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null) {
            return false;
        }
        if (word.length() == 0) {
            return true;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) { // match the first char
                    if (backtrack(board, i, j, word, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Backtracking.
     * Base case:
     * If we reach the end of the word, return true;
     * If i, j is not within the board, return false;
     * If characters are different, return false;
     * Recursive:
     * Mark current position with '#' as visited.
     * Recursively check 4 adjacent grids.
     * Reset the mark.
     * Return true if one of the adjacent grid is true.
     */
    public boolean backtrack(char[][] board, int i, int j, String word, int start) {
        if (word.length() == start) {
            return true;
        }
        // outside board or doesn't match
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(start)) {
            return false;
        }
        board[i][j] = '#'; // mark 
        // search 4 connectivity
        boolean
            res =
            backtrack(board, i - 1, j, word, start + 1) || backtrack(board, i + 1, j, word, start + 1) || backtrack(
                board, i, j - 1, word, start + 1)
            || backtrack(board, i, j + 1, word, start + 1);
        board[i][j] = word.charAt(start);// reset mark
        return res;
    }

    public boolean existB(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (backtrack(board, word, 0, i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Backtracking.
     * Optimized in terms of:
     * 1) Don't search out of board coordinates;
     * 2) Don't search already visited grid;
     * 3) If already found one solution, skip the rest.
     */
    private boolean backtrack(char[][] board, String word, int start, int i, int j) {
        if (word.charAt(start) == board[i][j] && start == word.length() - 1) {
            return true;
        }
        if (word.charAt(start) != board[i][j]) {
            return false;
        }
        board[i][j] = '#'; // Mark
        boolean res = false;
        if (!res && i + 1 < board.length && board[i + 1][j] != '#') {
            res = res || backtrack(board, word, start + 1, i + 1, j);
        }
        if (!res && i - 1 >= 0 && board[i - 1][j] != '#') {
            res = res || backtrack(board, word, start + 1, i - 1, j);
        }
        if (!res && j + 1 < board[i].length && board[i][j + 1] != '#') {
            res = res || backtrack(board, word, start + 1, i, j + 1);
        }
        if (!res && j - 1 >= 0 && board[i][j - 1] != '#') {
            res = res || backtrack(board, word, start + 1, i, j - 1);
        }
        board[i][j] = word.charAt(start); // Reset
        return res;
    }

    @Test
    public void testExamples() {
        char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        Assert.assertTrue(exist(board, word));
        Assert.assertTrue(existB(board, word));
        word = "SEE";
        Assert.assertTrue(exist(board, word));
        Assert.assertTrue(existB(board, word));
        word = "ABCB";
        Assert.assertFalse(exist(board, word));
        Assert.assertFalse(existB(board, word));
    }
}