/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * 
 * Empty cells are indicated by the character '.'.
 * 
 * You may assume that there will be only one unique solution.
 * 
 * Tags: Backtracking, Hash Table
 */
class SudokuSolver {
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
        SudokuSolver s = new SudokuSolver();
        s.solveSudoku(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    int[] row, col, sqr;
    
    /**
     * Use three integer arrays as mask to check whether a move is valid
     */
    public void solveSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) return;
        // initialize
        row = new int[9];
        col = new int[9];
        sqr = new int[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] -'0';
                    int k = (i - i % 3) + j / 3;
                    row[i] |= 1 << num;
                    col[j] |= 1 << num;
                    sqr[k] |= 1 << num; // kth's square in board, left to right
                }
            }
        }
        for (int i = 1; i <= 9; i++) 
            if (helper(board, 0, 0, 0, i)) return;
    }
    
    private boolean helper(char[][] board, int i, int j, int k, int num) {
        if (j == 9) { // reach rightmost
            j = 0;
            i++; // next row
        }
        if (i == 9) return true; // reach bottom, finished
        while (board[i][j] != '.') { // find next blank
            j = (j + 1) % 9; // move to right
            if (j % 9 == 0) i++; // next row
            if (i == 9) return true; // reach bottom
        }
        k = (i - i % 3) + j / 3; // kth square
        // if valid, set masks and board
        if (isValid(i, j, k, num)) {
            row[i] |= 1 << num;
            col[j] |= 1 << num;
            sqr[k] |= 1 << num;
            board[i][j] = (char)('0' + num);
        } else return false; // not valid, return false
        for (int n = 1; n <= 9; n++) // backtrack the next column
            if (helper(board, i, j + 1, k, n)) return true;
        // all possible combinations from this position generated
        // reset this position
        row[i] ^= 1 << num; 
        col[j] ^= 1 << num;
        sqr[k] ^= 1 << num;
        board[i][j] = '.';
        return false;
    }
    
    private boolean isValid(int i, int j, int k, int num) {
        if ((row[i] & 1 << num) > 0) return false; // both are 1
        if ((col[j] & 1 << num) > 0) return false; // both are 1
        if ((sqr[k] & 1 << num) > 0) return false; // both are 1
        return true;
    }
}