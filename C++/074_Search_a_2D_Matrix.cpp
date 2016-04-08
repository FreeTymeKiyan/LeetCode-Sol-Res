//74. Search a 2D Matrix
/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.

Tag: Array, Binary Search

Author: Xinyu Liu
*/

#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        int row = matrix.size();
        int col = matrix[0].size();
        int l = 0; int r = row * col - 1;
        while(l != r){
            int mid = (l + r) / 2;
            if (target <= matrix[mid / col][mid % col])
                r = mid;
            else
                l = mid + 1;
        }
        return (target == matrix[l / col][l % col]);
    }
};

void main(){
    vector<vector<int>> mat;
    int i1[] = {1,   3,  5,  7};
    vector<int> v1 (i1, i1 + sizeof(i1)/sizeof(int));
    mat.push_back(v1);
    int i2[] = {10, 11, 16, 20};
    vector<int> v2 (i2, i2 + sizeof(i2)/sizeof(int));
    mat.push_back(v2);
    int i3[] = {23, 30, 34, 50};
    vector<int> v3 (i2, i2 + sizeof(i3)/sizeof(int));
    mat.push_back(v3);
    Solution sol;
    bool b = sol.searchMatrix(mat,10);
}
