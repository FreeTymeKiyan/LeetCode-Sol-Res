// 037_Sudoku_Solver.cpp : Defines the entry point for the console application.
/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * 
 * Empty cells are indicated by the character '.'.
 * 
 * You may assume that there will be only one unique solution.
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
 * A sudoku puzzle...
 * 
 * 5 3 4 6 7 8 9 1 2
 * 6 7 2 1 9 5 3 4 8
 * 1 9 8 3 4 2 5 6 7
 * 8 5 9 7 6 1 4 2 3
 * 4 2 6 8 5 3 7 9 1
 * 7 1 3 9 2 4 8 5 6
 * 9 6 1 5 3 7 2 8 4
 * 2 8 7 4 1 9 6 3 5
 * 3 4 5 2 8 6 1 7 9
 * 
 * ...and its solution numbers marked in red.
 *
 * Tags: Backtracking, Hash Table
 *
 * Similar Problems: (E) Valid Sudoku
 * 
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <vector>
#include <iostream>
#include <fstream>

using namespace std;

class Solution {
    int m_row[9], m_col[9], m_sq[9];
    bool isValid(vector<vector<char>>& board, int row, int col, int test) {
        int sq = row / 3 * 3 + col / 3;

        // if the bit flag is not set, this OR tests should all return 0
        if ((m_row[row] & test) || (m_col[col] & test) || (m_sq[sq] & test))
        {
            return false;
        }

        return true;
    }

    // backtracking from top-left to bottom-right
    bool backtrack(vector<vector<char>>& board, int row, int col) {
        if (row == 9)    // end of backtracking
        {
            return true;
        }

        if (col == 9)    // go to next row if at the last column
        {
            return backtrack(board, row + 1, 0);
        }

        if (board[row][col] != '.')    // current cell not empty, go to next cell
        {
            return backtrack(board, row, col + 1);
        }

        for (char ch = '1'; ch <= '9'; ch++)
        {
            int test = 1 << (ch - '0');
            int sq = row / 3 * 3 + col / 3;
            if (isValid(board, row, col, test))    // check if it is valid
            {   
                // set bit flags and current cell
                m_row[row] |= test; 
                m_col[col] |= test; 
                m_sq[sq] |= test; 
                board[row][col] = ch;
                if (backtrack(board, row, col + 1))    // go to the next cell
                {
                    // if the board is solvable return true
                    return true;
                }

                // if the current run is invalid, reset bit flags and current cell
                m_row[row] ^= test; 
                m_col[col] ^= test; 
                m_sq[sq] ^= test; 
                board[row][col] = '.';
            }
        }

        // '1' - '9' not work, return false
        return false;
    }
public:
    void solveSudoku(vector<vector<char>>& board) {
        // initialize 3 bit flag arrays
        for (int i = 0; i < 9; i++)
        {
            m_row[i] = 0;
            m_col[i] = 0;
            m_sq[i] = 0;
        }

        // record existing numbers on the board
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                if (board[i][j] != '.')
                {
                    int test = 1 << (board[i][j] - '0');
                    int k = i / 3 * 3 + j / 3;    // get which square

                    // set the bit
                    m_row[i] |= test;
                    m_col[j] |= test;
                    m_sq[k] |= test;
                }
            }
        }
        
        // start backtracking
        backtrack(board, 0, 0);
        return;
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

    void displayBoard() {
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                cout << board[i][j] << " ";
            }
            cout << endl;
        }
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    Sudoku sdk;
    sdk.initFromFile("Sudoku.txt");
    sdk.displayBoard();
    Solution mySolution;
    mySolution.solveSudoku(sdk.board);
    cout << endl;
    sdk.displayBoard();
    system("pause");
    return 0;
}

