//130. Surrounded Regions
/*
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

Tag: Breadth-first Search, Union Find

Author: Xinyu Liu
*/
#include <vector>
using namespace std;

class Solution {
public:
    void solve(vector<vector<char>>& board) {
        int i,j;
        int row = board.size();
        if(!row)
            return;
        int col = board[0].size();

        for(i = 0; i < row; i++){
            nearcheck(board, i, 0, row,col);
            if(col > 1)
                nearcheck(board, i, col - 1, row, col);
        }
        for(j = 1; j + 1 < col; j++){
            nearcheck(board, 0, j, row, col);
            if(row > 1)
                nearcheck(board, row - 1, j, row, col);
        }
        for(i = 0; i < row; i++)
            for(j = 0; j < col; j++)
                if(board[i][j] == 'O')
                    board[i][j] = 'X';
        for(i = 0; i < row; i++)
            for(j = 0; j < col; j++)
                if(board[i][j] == '1')
                    board[i][j] = 'O';
    }
    void nearcheck(vector<vector<char> >&vec, int i, int j, int row, int col){
        if(vec[i][j] == 'O'){
            vec[i][j] = '1';
            if(i > 1)
                nearcheck(vec, i - 1, j, row, col);
            if(j > 1)
                nearcheck(vec, i, j - 1, row, col);
            if(i < row - 1)
                nearcheck(vec, i + 1, j, row, col);
            if(j < col - 1)
                nearcheck(vec, i, j + 1, row, col);
        }
    }
};

void main(){
    char c0[] = {'X','X','X','X'};
    vector<char> v0 (begin(c0), end(c0));
    char c1[] = {'X','O','O','X'};
    vector<char> v1 (begin(c1), end(c1));
    char c2[] = {'X','X','O','X'};
    vector<char> v2 (begin(c2), end(c2));
    char c3[] = {'X','X','X','X'};
    vector<char> v3 (begin(c3), end(c3));
    vector<vector<char>> v;
    v.push_back(v0);
    v.push_back(v1);
    v.push_back(v2);
    v.push_back(v3);
    Solution sol;
    sol.solve(v);
}
