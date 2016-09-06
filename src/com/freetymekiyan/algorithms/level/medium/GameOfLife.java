package com.freetymekiyan.algorithms.level.medium;

/**
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised
 * by the British mathematician John Horton Conway in 1970."
 * <p>
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its
 * eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia
 * article):
 * <p>
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state.
 * <p>
 * Follow up:
 * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some
 * cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause
 * problems when the active area encroaches the border of the array. How would you address these problems?
 * <p>
 * Tags: Array
 * Similar Problems: (M) Set Matrix Zeroes
 */
public class GameOfLife {

    /**
     * Bit-manipulation.
     * Use 2 bit for each cell to represent both current and next states.
     * 00, 01, 10, 11.
     * Then scan the matrix again to generate result.
     */
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = -board[i][j]; // If the current cell is live, it will be deducted from result
                for (int k = Math.max(i - 1, 0); k < Math.min(i + 2, m); k++) {
                    for (int l = Math.max(j - 1, 0); l < Math.min(j + 2, n); l++) {
                        lives += board[k][l] & 1;
                    }
                }
                if ((lives | board[i][j]) == 3) { // lives = 2 or 3, board[i][j] = 1, or lives = 3, board[i][j] = 0
                    board[i][j] |= 2;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;
            }
        }
    }
}