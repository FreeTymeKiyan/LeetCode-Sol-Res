package com.freetymekiyan.algorithms.level.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * N-Queens
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each
 * other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a
 * queen and an empty space respectively.
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 * <p>
 * [
 * [".Q..",  // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * ["..Q.",  // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * Tags:  Backtracking
 * Similar Problems:(H) N-Queens II
 *
 * @auther chenshuna
 */

class NQueens {

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<List<String>>();
        int[] board = new int[n];
        queenDFS(n, 0, board, res);
        return res;
    }

    public static void queenDFS(int nrow, int row, int[] board, List<List<String>> res) {
        if (nrow == row) {
            List<String> s1 = new ArrayList<String>();
            for (int i = 0; i < nrow; i++) {
                StringBuilder s2 = new StringBuilder();
                for (int j = 0; j < nrow; j++) {
                    if (board[i] == j) {
                        s2.append('Q');
                    } else {
                        s2.append('.');
                    }
                }
                s1.add(s2.toString());
            }
            res.add(s1);
            return;
        } else {
            for (int i = 0; i < nrow; i++) {
                board[row] = i;
                if (isValid(row, board)) {
                    queenDFS(nrow, row + 1, board, res);
                }
            }
        }
    }

    public static boolean isValid(int row, int[] board) {
        for (int i = 0; i < row; i++) {
            if (board[row] == board[i] || Math.abs(board[row] - board[i]) == row - i) {
                return false;
            }
        }
        return true;
    }

    /**
     * N-Queens II
     * Follow up for N-Queens problem.
     * Now, instead outputting board configurations, return the total number of distinct solutions.
     */

    public static int totalNQueens(int n) {
        int res[] = {0};
        int[] board = new int[n];
        totalNQueensDFS(n, 0, board, res);
        return res[0];
    }

    public static void totalNQueensDFS(int nrow, int row, int[] board, int[] res) {
        if (row == nrow) {
            res[0]++;
        } else {
            for (int i = 0; i < nrow; i++) {
                board[row] = i;
                if (isValid(row, board)) {
                    totalNQueensDFS(nrow, row + 1, board, res);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.print(solveNQueens(4));
        System.out.print(totalNQueens(4));
    }
}