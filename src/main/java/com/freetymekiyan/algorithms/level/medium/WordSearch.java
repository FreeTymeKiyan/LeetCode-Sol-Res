package com.freetymekiyan.algorithms.level.medium;

/**
 * 79. Word Search
 * <p>
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
                if (board[i][j] == word.charAt(0)) { // Match the first character.
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
     * Statement:
     * Given board, starting position, word, and current position in word.
     * Find whether the word is in board.
     * Recursion:
     * Check current character. If diff, return false. If same, recurse 4 neighbors with subset.
     * Base case:
     * If pos is at word's end, return true.
     * If i, j is not within the board, return false.
     * If characters are different, return false.
     * Implementation:
     * Deal with base cases.
     * Mark current position with '#' as visited.
     * Recurse the 4 adjacent grids.
     * Reset the mark.
     * Return true if one of the adjacent grid is true.
     */
    private boolean backtrack(char[][] board, int i, int j, String word, int pos) {
        if (word.length() == pos) {
            return true;
        }
        // Out of board or doesn't match.
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(pos)) {
            return false;
        }
        board[i][j] = '#'; // Instead of using another 2D boolean array, mark board with a special character to set as visited.
        boolean res = backtrack(board, i - 1, j, word, pos + 1)
                || backtrack(board, i + 1, j, word, pos + 1)
                || backtrack(board, i, j - 1, word, pos + 1)
                || backtrack(board, i, j + 1, word, pos + 1);
        board[i][j] = word.charAt(pos);// Reset.
        return res;
    }

    public boolean exist2(char[][] board, String word) {
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
    private boolean backtrack(char[][] board, String word, int pos, int i, int j) {
        if (word.charAt(pos) == board[i][j] && pos == word.length() - 1) {
            return true;
        }
        if (word.charAt(pos) != board[i][j]) {
            return false;
        }
        board[i][j] = '#'; // Mark visited.
        boolean res = false;
        if (!res && i + 1 < board.length && board[i + 1][j] != '#') {
            res = res || backtrack(board, word, pos + 1, i + 1, j);
        }
        if (!res && i - 1 >= 0 && board[i - 1][j] != '#') {
            res = res || backtrack(board, word, pos + 1, i - 1, j);
        }
        if (!res && j + 1 < board[i].length && board[i][j + 1] != '#') {
            res = res || backtrack(board, word, pos + 1, i, j + 1);
        }
        if (!res && j - 1 >= 0 && board[i][j - 1] != '#') {
            res = res || backtrack(board, word, pos + 1, i, j - 1);
        }
        board[i][j] = word.charAt(pos); // Reset.
        return res;
    }
}