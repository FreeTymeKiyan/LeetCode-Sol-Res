import java.util.HashSet;

public class SudokuSolver_2 {

    public static void main(String[] args) {
        char[][] board = {
                {'.', '.', '9', '7', '4', '8', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
                {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
                {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
                {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
                {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
                {'.', '.', '.', '2', '7', '5', '9', '.', '.'}
        };
        SudokuSolver_2 s = new SudokuSolver_2();
        s.solveSudoku(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void solveSudoku(char[][] board) {
        HashSet<Integer>[] rows = new HashSet[9];
        for (int i = 0; i < 9; i++) rows[i] = new HashSet<Integer>();

        HashSet<Integer>[] cols = new HashSet[9];
        for (int i = 0; i < 9; i++) cols[i] = new HashSet<Integer>();

        HashSet<Integer>[] squares = new HashSet[9];
        for (int i = 0; i < 9; i++) squares[i] = new HashSet<Integer>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Character.isDigit(board[i][j])) {
                    rows[i].add(board[i][j] - '0');
                    cols[j].add(board[i][j] - '0');
                    squares[(i / 3) * 3 + (j / 3)].add(board[i][j] - '0');
                }
            }
        }

        System.out.println(helper(board, rows, cols, squares, 0, 0));
    }

    public boolean helper(char[][] board, HashSet<Integer>[] rows, HashSet<Integer>[] cols, HashSet<Integer>[] squares, int row, int col) {
        if (row >= 9) {
            return true;
        }
        for (int i = row; i < 9; i++) {
            for (int j = col; j < 9; j++) {
                if (Character.isDigit(board[i][j])) {
                    continue;
                } else {
                    for (int k = 1; k <= 9; k++) {
                        if (rows[i].contains(k) || cols[j].contains(k) || squares[(i / 3) * 3 + (j / 3)].contains(k))
                            continue;
                        else {
                            rows[i].add(k);
                            cols[j].add(k);
                            squares[(i / 3) * 3 + (j / 3)].add(k);
                            board[i][j] = (char) ('0' + k);
                            int nextrow;
                            int nextcol;
                            if (j >= 8) {
                                nextrow = i + 1;
                                nextcol = 0;
                            } else {
                                nextrow = i;
                                nextcol = j + 1;
                            }
                            if (helper(board, rows, cols, squares, nextrow, nextcol)) return true;
                            rows[i].remove(k);
                            cols[j].remove(k);
                            squares[(i / 3) * 3 + (j / 3)].remove(k);
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}