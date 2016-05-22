// 036. Valid Sudoku
/**
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * 
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * 
 * 5 3 . . 7 . . . .
 * 6 . . 1 9 5 . . .
 * . 9 8 . . . . 6 .
 * 8 . . . 6 . . . 3
 * 4 . . 8 . 3 . . 1
 * 7 . . . 2 . . . 6
 * . 6 . . . . 2 8 .
 * . . . 4 1 9 . . 5
 * . . . . 8 . . 7 9
 *
 * A partially filled sudoku which is valid.
 * 
 * Note:
 * A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
 *
 * Tags: Hash Table
 *
 * Similar Problems: (H) Sudoku Solver
 * 
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <vector>
#include <fstream>

using namespace std;

class Solution {
public:
    bool isValidSudoku(vector<vector<char>>& board) {
        // for each row, column and square, use the lowest 9-bit of an integer as number flags
        int *row = new int[9]();
        int *col = new int[9]();
        int *sq  = new int[9]();

        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                if (board[i][j] != '.')
                {
                    int num = board[i][j] - '0';
                    int test = 1 << num;
                    int k = i / 3 * 3 + j / 3;    // get which square
                    if ((row[i] & test) || (col[j] & test) || (sq[k] & test))
                    {
                        // if the corresponding bit is 0, all tests should return 0
                        return false;
                    }
                
                    // set the bit
                    row[i] |= test;
                    col[j] |= test;
                    sq[k] |= test;
                }
            }
        }

        delete[] row;
        delete[] col;
        delete[] sq;
        return true;
    }
};

class Sudoku {
public:    
    vector<vector<char>> board;
    void initFromFile(const char *filename) {
        ifstream fin(filename);
        vector<char> line;

        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                char c;
                fin >> c;
                line.push_back(c);
            }

            board.push_back(line);
            line.clear();
        }

        fin.close();
        return;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    Sudoku sdk;
    sdk.initFromFile("Sudoku.txt");
    Solution mySolution;
    bool res = mySolution.isValidSudoku(sdk.board);
    return 0;
}

